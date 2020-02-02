package org.deeplearning4j.models.embeddings.reader.impl;

import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;
import org.nd4j.common.util.SetUtils;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.nd4j.shade.guava.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public final class PluginModelUtils<T extends SequenceElement> extends BasicModelUtils<T> {
    public PluginModelUtils(BasicModelUtils<T> basicModelUtils) {
        this.vocabCache = basicModelUtils.vocabCache;
        this.lookupTable = basicModelUtils.lookupTable;
        this.normalized = basicModelUtils.normalized;
    }

    public Collection<String> wordsNearest(Collection<String> positive, Collection<String> negative, double threshold) {
        // Check every word is in the model
        for (String p : SetUtils.union(new HashSet<>(positive), new HashSet<>(negative))) {
            if (!vocabCache.containsWord(p)) {
                return new ArrayList<>();
            }
        }

        INDArray words = Nd4j.create(positive.size() + negative.size(), lookupTable.layerSize());
        int row = 0;
        //Set<String> union = SetUtils.union(new HashSet<>(positive), new HashSet<>(negative));
        for (String s : positive) {
            words.putRow(row++, lookupTable.vector(s));
        }

        for (String s : negative) {
            words.putRow(row++, lookupTable.vector(s).mul(-1));
        }

        INDArray mean = words.isMatrix() ? words.mean(0).reshape(1, words.size(1)) : words;
        Collection<String> tempRes = wordsNearest(mean, threshold);
        List<String> realResults = new ArrayList<>();

        for (String word : tempRes) {
            if (!positive.contains(word) && !negative.contains(word)) {
                realResults.add(word);
            }
        }

        return realResults;
    }

    public Collection<String> wordsNearest(INDArray words, double threshold) {
        words = adjustRank(words);

        InMemoryLookupTable l = (InMemoryLookupTable) lookupTable;

        INDArray syn0 = l.getSyn0();

        if (!normalized) {
            synchronized (this) {
                if (!normalized) {
                    syn0.diviColumnVector(syn0.norm2(1));
                    normalized = true;
                }
            }
        }

        INDArray similarity = Transforms.unitVec(words).mmul(syn0.transpose());

        List<Double> highToLowSimList = getTopN(similarity, 20);

        List<WordSimilarity> result = new ArrayList<>();

        for (int i = 0; i < highToLowSimList.size(); i++) {
            String word = vocabCache.wordAtIndex(highToLowSimList.get(i).intValue());
            if (word != null && !word.equals("UNK") && !word.equals("STOP")) {
                INDArray otherVec = lookupTable.vector(word);
                double sim = Transforms.cosineSim(words, otherVec);

                result.add(new WordSimilarity(word, sim));
            }
        }

        Collections.sort(result, new SimilarityComparator());

        return result
                .stream()
                .filter(wordSimilarity -> wordSimilarity.getSimilarity() >= threshold)
                .map(WordSimilarity::getWord)
                .collect(Collectors.toList());
    }

    /**
     * Get top N elements
     *
     * @param vec the vec to extract the top elements from
     * @param N the number of elements to extract
     * @return the indices and the sorted top N elements
     */
    private List<Double> getTopN(INDArray vec, int N) {
        ArrayComparator comparator = new ArrayComparator();
        PriorityQueue<Double[]> queue = new PriorityQueue<>(vec.rows(), comparator);

        for (int j = 0; j < vec.length(); j++) {
            final Double[] pair = new Double[] {vec.getDouble(j), (double) j};
            if (queue.size() < N) {
                queue.add(pair);
            } else {
                Double[] head = queue.peek();
                if (comparator.compare(pair, head) > 0) {
                    queue.poll();
                    queue.add(pair);
                }
            }
        }

        List<Double> lowToHighSimLst = new ArrayList<>();

        while (!queue.isEmpty()) {
            double ind = queue.poll()[1];
            lowToHighSimLst.add(ind);
        }
        return Lists.reverse(lowToHighSimLst);
    }
}
