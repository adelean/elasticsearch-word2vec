/*******************************************************************************
 * Copyright (c) 2015-2018 Skymind, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

package org.deeplearning4j.models.embeddings.loader;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.deeplearning4j.exception.DL4JInvalidInputException;
import org.deeplearning4j.models.embeddings.WeightLookupTable;
import org.deeplearning4j.models.embeddings.inmemory.InMemoryLookupTable;
import org.deeplearning4j.models.embeddings.learning.impl.elements.SkipGram;
import org.deeplearning4j.models.embeddings.reader.impl.BasicModelUtils;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectorsImpl;
import org.deeplearning4j.models.paragraphvectors.ParagraphVectors;
import org.deeplearning4j.models.sequencevectors.SequenceVectors;
import org.deeplearning4j.models.sequencevectors.interfaces.SequenceElementFactory;
import org.deeplearning4j.models.sequencevectors.sequence.SequenceElement;
import org.deeplearning4j.models.sequencevectors.serialization.VocabWordFactory;
import org.deeplearning4j.models.word2vec.StaticWord2Vec;
import org.deeplearning4j.models.word2vec.VocabWord;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.deeplearning4j.models.word2vec.wordstore.inmemory.AbstractCache;
import org.deeplearning4j.text.documentiterator.LabelsSource;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.util.DL4JFileUtils;
import org.nd4j.compression.impl.NoOp;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.exception.ND4JIllegalStateException;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.nd4j.linalg.primitives.Pair;
import org.nd4j.shade.jackson.databind.ObjectMapper;
import org.nd4j.storage.CompressedRamStorage;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * This is utility class, providing various methods for WordVectors serialization
 *
 * List of available serialization methods (please keep this list consistent with source code):
 *
 * <ul>
 *
 * <li>Deserializers for Word2Vec:</li>
 * {@link #readAsBinaryNoLineBreaks(InputStream)}
 * {@link #readAsBinary(InputStream)}
 * {@link #readAsCsv(InputStream)}
 * {@link #readBinaryModel(InputStream, boolean, boolean)}
 * {@link #readWord2Vec(InputStream, boolean)}
 *
 * <li>Adapters</li>
 * {@link #fromTableAndVocab(WeightLookupTable, VocabCache)}
 * {@link #fromPair(Pair)}
 * {@link #loadTxt(InputStream)}
 *
 * <li>SequenceVectors deserializers:</li>
 * {@link #readSequenceVectors(SequenceElementFactory, File)}
 * {@link #readSequenceVectors(InputStream, boolean)}
 * {@link #readSequenceVectors(SequenceElementFactory, InputStream)}
 * {@link #readLookupTable(InputStream)}
 *
 * </ul>
 *
 * @author Adam Gibson
 * @author raver119
 * @author alexander@skymind.io
 */
public class WordVectorSerializerPluginImpl {
    private static final Logger log = LogManager.getLogger(WordVectorSerializerPluginImpl.class);

    private static final int MAX_SIZE = 50;
    private static final String WHITESPACE_REPLACEMENT = "_Az92_";

    private WordVectorSerializerPluginImpl() {
    }

    /**
     * Read a binary word2vec from input stream.
     *
     * @param inputStream  input stream to read
     * @param linebreaks  if true, the reader expects each word/vector to be in a separate line, terminated
     *      by a line break
     * @param normalize
     *
     * @return a {@link Word2Vec model}
     * @throws NumberFormatException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Word2Vec readBinaryModel(
            InputStream inputStream,
            boolean linebreaks,
            boolean normalize) throws NumberFormatException, IOException {
        InMemoryLookupTable<VocabWord> lookupTable;
        VocabCache<VocabWord> cache;
        INDArray syn0;
        int words, size;

        int originalFreq = Nd4j.getMemoryManager().getOccasionalGcFrequency();
        boolean originalPeriodic = Nd4j.getMemoryManager().isPeriodicGcActive();

        if (originalPeriodic)
            Nd4j.getMemoryManager().togglePeriodicGc(false);

        Nd4j.getMemoryManager().setOccasionalGcFrequency(50000);

        try (DataInputStream dis = new DataInputStream(inputStream)) {
            words = Integer.parseInt(ReadHelper.readString(dis));
            size = Integer.parseInt(ReadHelper.readString(dis));
            syn0 = Nd4j.create(words, size);
            cache = new AbstractCache<>();

            printOutProjectedMemoryUse(words, size, 1);

            lookupTable = new InMemoryLookupTable.Builder<VocabWord>()
                    .cache(cache)
                    .useHierarchicSoftmax(false)
                    .vectorLength(size)
                    .build();

            String word;
            float[] vector = new float[size];
            for (int i = 0; i < words; i++) {
                word = ReadHelper.readString(dis);
                log.trace("Loading " + word + " with word " + i);

                for (int j = 0; j < size; j++) {
                    vector[j] = ReadHelper.readFloat(dis);
                }

                if (cache.containsWord(word)) {
                    throw new ND4JIllegalStateException(
                            "Tried to add existing word. Probably time to switch linebreaks mode?");
                }

                syn0.putRow(i, normalize ? Transforms.unitVec(Nd4j.create(vector)) : Nd4j.create(vector));

                VocabWord vw = new VocabWord(1.0, word);
                vw.setIndex(cache.numWords());

                cache.addToken(vw);
                cache.addWordToIndex(vw.getIndex(), vw.getLabel());

                cache.putVocabWord(word);

                if (linebreaks) {
                    dis.readByte(); // line break
                }

                Nd4j.getMemoryManager().invokeGcOccasionally();
            }
        } finally {
            if (originalPeriodic) {
                Nd4j.getMemoryManager().togglePeriodicGc(true);
            }

            Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);
        }

        lookupTable.setSyn0(syn0);

        Word2Vec ret = new Word2Vec
                .Builder()
                .useHierarchicSoftmax(false)
                .resetModel(false)
                .layerSize(syn0.columns())
                .allowParallelTokenization(true)
                .elementsLearningAlgorithm(new SkipGram<>())
                .learningRate(0.025)
                .windowSize(5)
                .workers(1)
                .build();

        ret.setVocab(cache);
        ret.setLookupTable(lookupTable);

        return ret;
    }

    /**
     * Restores previously serialized ParagraphVectors model
     * <p>
     * Deprecation note: Please, consider using readParagraphVectors() method instead
     *
     * @param stream InputStream that contains previously serialized model
     */
    @Deprecated
    public static ParagraphVectors readParagraphVectorsFromText(InputStream stream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            ArrayList<String> labels = new ArrayList<>();
            ArrayList<INDArray> arrays = new ArrayList<>();
            VocabCache<VocabWord> vocabCache = new AbstractCache.Builder<VocabWord>().build();
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                split[1] = split[1].replaceAll(WHITESPACE_REPLACEMENT, " ");
                VocabWord word = new VocabWord(1.0, split[1]);
                if (split[0].equals("L")) {
                    // we have label element here
                    word.setSpecial(true);
                    word.markAsLabel(true);
                    labels.add(word.getLabel());
                } else if (split[0].equals("E")) {
                    // we have usual element, aka word here
                    word.setSpecial(false);
                    word.markAsLabel(false);
                } else
                    throw new IllegalStateException(
                            "Source stream doesn't looks like ParagraphVectors serialized model");

                // this particular line is just for backward compatibility with InMemoryLookupCache
                word.setIndex(vocabCache.numWords());

                vocabCache.addToken(word);
                vocabCache.addWordToIndex(word.getIndex(), word.getLabel());

                // backward compatibility code
                vocabCache.putVocabWord(word.getLabel());

                float[] vector = new float[split.length - 2];

                for (int i = 2; i < split.length; i++) {
                    vector[i - 2] = Float.parseFloat(split[i]);
                }

                INDArray row = Nd4j.create(vector);

                arrays.add(row);
            }

            // now we create syn0 matrix, using previously fetched rows
            /*INDArray syn = Nd4j.create(new int[]{arrays.size(), arrays.get(0).columns()});
            for (int i = 0; i < syn.rows(); i++) {
                syn.putRow(i, arrays.get(i));
            }*/
            INDArray syn = Nd4j.vstack(arrays);


            InMemoryLookupTable<VocabWord> lookupTable =
                    (InMemoryLookupTable<VocabWord>) new InMemoryLookupTable.Builder<VocabWord>()
                            .vectorLength(arrays.get(0).columns()).useAdaGrad(false).cache(vocabCache)
                            .build();
            Nd4j.clearNans(syn);
            lookupTable.setSyn0(syn);

            LabelsSource source = new LabelsSource(labels);
            ParagraphVectors vectors = new ParagraphVectors.Builder().labelsSource(source).vocabCache(vocabCache)
                    .lookupTable(lookupTable).modelUtils(new BasicModelUtils<VocabWord>()).build();

            try {
                reader.close();
            } catch (Exception e) {
            }

            vectors.extractLabels();

            return vectors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load word vectors for the given vocab and table
     *
     * @param table the weights to use
     * @param vocab the vocab to use
     * @return wordvectors based on the given parameters
     */
    public static WordVectors fromTableAndVocab(WeightLookupTable table, VocabCache vocab) {
        WordVectorsImpl vectors = new WordVectorsImpl();
        vectors.setLookupTable(table);
        vectors.setVocab(vocab);
        vectors.setModelUtils(new BasicModelUtils());
        return vectors;
    }

    /**
     * Load word vectors from the given pair
     *
     * @param pair the given pair
     * @return a read only word vectors impl based on the given lookup table and vocab
     */
    public static Word2Vec fromPair(Pair<InMemoryLookupTable, VocabCache> pair) {
        Word2Vec vectors = new Word2Vec();
        vectors.setLookupTable(pair.getFirst());
        vectors.setVocab(pair.getSecond());
        vectors.setModelUtils(new BasicModelUtils());
        return vectors;
    }

    private static BufferedReader createReader(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    /**
     * Loads an in memory cache from the given input stream (sets syn0 and the vocab).
     *
     * @param inputStream  input stream
     * @return a {@link Pair} holding the lookup table and the vocab cache.
     */
    public static Pair<InMemoryLookupTable, VocabCache> loadTxt(InputStream inputStream) {
        AbstractCache<VocabWord> cache = new AbstractCache<>();
        LineIterator lines = null;

        try (BufferedReader reader = createReader(inputStream)) {
            lines = IOUtils.lineIterator(reader);

            String line = null;
            boolean hasHeader = false;

            /* Check if first line is a header */
            if (lines.hasNext()) {
                line = lines.nextLine();
                hasHeader = isHeader(line, cache);
            }

            if (hasHeader) {
                log.debug("First line is a header");
                line = lines.nextLine();
            }

            List<INDArray> arrays = new ArrayList<>();
            long[] vShape = new long[]{ 1, -1 };

            do {
                String[] tokens = line.split(" ");
                String word = ReadHelper.decodeB64(tokens[0]);
                VocabWord vocabWord = new VocabWord(1.0, word);
                vocabWord.setIndex(cache.numWords());

                cache.addToken(vocabWord);
                cache.addWordToIndex(vocabWord.getIndex(), word);
                cache.putVocabWord(word);

                float[] vector = new float[tokens.length - 1];
                for (int i = 1; i < tokens.length; i++) {
                    vector[i - 1] = Float.parseFloat(tokens[i]);
                }

                vShape[1] = vector.length;
                INDArray row = Nd4j.create(vector, vShape);

                arrays.add(row);

                line = lines.hasNext() ? lines.next() : null;
            } while (line != null);

            INDArray syn = Nd4j.vstack(arrays);

            InMemoryLookupTable<VocabWord> lookupTable = new InMemoryLookupTable
                    .Builder<VocabWord>()
                    .vectorLength(arrays.get(0).columns())
                    .useAdaGrad(false)
                    .cache(cache)
                    .useHierarchicSoftmax(false)
                    .build();

            lookupTable.setSyn0(syn);

            return new Pair<>((InMemoryLookupTable) lookupTable, (VocabCache) cache);
        } catch (IOException readeTextStreamException) {
            throw new RuntimeException(readeTextStreamException);
        } finally {
            if (lines != null) {
                lines.close();
            }
        }
    }

    static boolean isHeader(String line, AbstractCache cache) {
        if (!line.contains(" ")) {
            return true;
        } else {

            /* We should check for something that looks like proper word vectors here. i.e: 1 word at the 0
             * position, and bunch of floats further */
            String[] headers = line.split(" ");

            try {
                long[] header = new long[headers.length];
                for (int x = 0; x < headers.length; x++) {
                    header[x] = Long.parseLong(headers[x]);
                }

                /* Now we know, if that's all ints - it's just a header
                 * [0] - number of words
                 * [1] - vectorLength
                 * [2] - number of documents <-- DL4j-only value
                 */
                if (headers.length == 3) {
                    long numberOfDocuments = header[2];
                    cache.incrementTotalDocCount(numberOfDocuments);
                }

                long numWords = header[0];
                int vectorLength = (int) header[1];
                printOutProjectedMemoryUse(numWords, vectorLength, 1);

                return true;
            } catch (Exception notHeaderException) {
                // if any conversion exception hits - that'll be considered header
                return false;
            }
        }
    }

    /**
     * This method can be used to load previously saved model from InputStream (like a HDFS-stream)
     * <p>
     * Deprecation note: Please, consider using readWord2VecModel() or loadStaticModel() method instead
     *
     * @param stream        InputStream that contains previously serialized model
     * @param skipFirstLine Set this TRUE if first line contains csv header, FALSE otherwise
     * @return
     * @throws IOException
     * @deprecated Use readWord2VecModel() or loadStaticModel() method instead
     */
    @Deprecated
    public static WordVectors loadTxtVectors(InputStream stream, boolean skipFirstLine) throws IOException {
        AbstractCache<VocabWord> cache = new AbstractCache.Builder<VocabWord>().build();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        List<INDArray> arrays = new ArrayList<>();

        if (skipFirstLine)
            reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            String word = split[0].replaceAll(WHITESPACE_REPLACEMENT, " ");
            VocabWord word1 = new VocabWord(1.0, word);

            word1.setIndex(cache.numWords());

            cache.addToken(word1);

            cache.addWordToIndex(word1.getIndex(), word);

            cache.putVocabWord(word);

            float[] vector = new float[split.length - 1];

            for (int i = 1; i < split.length; i++) {
                vector[i - 1] = Float.parseFloat(split[i]);
            }

            INDArray row = Nd4j.create(vector);

            arrays.add(row);
        }

        InMemoryLookupTable<VocabWord> lookupTable =
                (InMemoryLookupTable<VocabWord>) new InMemoryLookupTable.Builder<VocabWord>()
                        .vectorLength(arrays.get(0).columns()).cache(cache).build();

        INDArray syn = Nd4j.vstack(arrays);

        Nd4j.clearNans(syn);
        lookupTable.setSyn0(syn);

        return fromPair(Pair.makePair((InMemoryLookupTable) lookupTable, (VocabCache) cache));
    }

    /**
     * This method is used only for VocabCache compatibility purposes
     *
     * @param array
     * @param codeLen
     * @return
     */
    private static List<Byte> arrayToList(byte[] array, int codeLen) {
        List<Byte> result = new ArrayList<>();
        for (int x = 0; x < codeLen; x++) {
            result.add(array[x]);
        }
        return result;
    }

    private static byte[] listToArray(List<Byte> code) {
        byte[] array = new byte[40];
        for (int x = 0; x < code.size(); x++) {
            array[x] = code.get(x).byteValue();
        }
        return array;
    }

    private static int[] listToArray(List<Integer> points, int codeLen) {
        int[] array = new int[points.size()];
        for (int x = 0; x < points.size(); x++) {
            array[x] = points.get(x).intValue();
        }
        return array;
    }

    /**
     * This method is used only for VocabCache compatibility purposes
     *
     * @param array
     * @param codeLen
     * @return
     */
    private static List<Integer> arrayToList(int[] array, int codeLen) {
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < codeLen; x++) {
            result.add(array[x]);
        }
        return result;
    }

    private static final String CONFIG_ENTRY = "config.json";
    private static final String VOCAB_ENTRY = "vocabulary.json";
    private static final String SYN0_ENTRY = "syn0.bin";
    private static final String SYN1_ENTRY = "syn1.bin";
    private static final String SYN1_NEG_ENTRY = "syn1neg.bin";

    /**
     * This method loads SequenceVectors from specified input stream
     *
     * @param stream InputStream
     * @param readExtendedTables boolean
     * @param <T>
     */
    public static <T extends SequenceElement> SequenceVectors<T> readSequenceVectors(
            InputStream stream, boolean readExtendedTables) throws IOException {
        SequenceVectors<T> vectors = null;
        AbstractCache<T> vocabCache = null;
        VectorsConfiguration configuration = null;

        INDArray syn0 = null, syn1 = null, syn1neg = null;

        try (ZipInputStream zipfile = new ZipInputStream(new BufferedInputStream(stream))) {

            ZipEntry entry = null;
            while ((entry = zipfile.getNextEntry()) != null) {

                String name = entry.getName();
                byte[] bytes = IOUtils.toByteArray(zipfile);

                if (name.equals(CONFIG_ENTRY)) {
                    String content = new String(bytes, "UTF-8");
                    configuration = VectorsConfiguration.fromJson(content);
                    continue;
                } else if (name.equals(VOCAB_ENTRY)) {
                    String content = new String(bytes, "UTF-8");
                    vocabCache = AbstractCache.fromJson(content);
                    continue;
                }
                if (readExtendedTables) {
                    if (name.equals(SYN0_ENTRY)) {
                        syn0 = Nd4j.read(new ByteArrayInputStream(bytes));

                    } else if (name.equals(SYN1_ENTRY)) {
                        syn1 = Nd4j.read(new ByteArrayInputStream(bytes));
                    } else if (name.equals(SYN1_NEG_ENTRY)) {
                        syn1neg = Nd4j.read(new ByteArrayInputStream(bytes));
                    }
                }
            }

        }
        InMemoryLookupTable<T> lookupTable = new InMemoryLookupTable<>();
        lookupTable.setSyn0(syn0);
        lookupTable.setSyn1(syn1);
        lookupTable.setSyn1Neg(syn1neg);
        vectors = new SequenceVectors.Builder<T>(configuration).
                lookupTable(lookupTable).
                vocabCache(vocabCache).
                build();
        return vectors;
    }

    /**
     * This method loads previously saved SequenceVectors model from File
     *
     * @param factory
     * @param file
     * @param <T>
     * @return
     */
    public static <T extends SequenceElement> SequenceVectors<T> readSequenceVectors(
            SequenceElementFactory<T> factory, File file) throws IOException {
        return readSequenceVectors(factory, new FileInputStream(file));
    }

    /**
     * This method loads previously saved SequenceVectors model from InputStream
     *
     * @param factory
     * @param stream
     * @param <T>
     * @return
     */
    public static <T extends SequenceElement> SequenceVectors<T> readSequenceVectors(
            SequenceElementFactory<T> factory, InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        // at first we load vectors configuration
        String line = reader.readLine();
        VectorsConfiguration configuration =
                VectorsConfiguration.fromJson(new String(Base64.decodeBase64(line), "UTF-8"));

        AbstractCache<T> vocabCache = new AbstractCache.Builder<T>().build();


        List<INDArray> rows = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) // skip empty line
                continue;
            ElementPair pair = ElementPair.fromEncodedJson(line);
            T element = factory.deserialize(pair.getObject());
            rows.add(Nd4j.create(pair.getVector()));
            vocabCache.addToken(element);
            vocabCache.addWordToIndex(element.getIndex(), element.getLabel());
        }

        reader.close();

        InMemoryLookupTable<T> lookupTable = (InMemoryLookupTable<T>) new InMemoryLookupTable.Builder<T>()
                .vectorLength(rows.get(0).columns()).cache(vocabCache).build(); // fix: add vocab cache

        /*
         * INDArray syn0 = Nd4j.create(rows.size(), rows.get(0).columns()); for (int x = 0; x < rows.size(); x++) {
         * syn0.putRow(x, rows.get(x)); }
         */
        INDArray syn0 = Nd4j.vstack(rows);

        lookupTable.setSyn0(syn0);

        SequenceVectors<T> vectors = new SequenceVectors.Builder<T>(configuration).vocabCache(vocabCache)
                .lookupTable(lookupTable).resetModel(false).build();

        return vectors;
    }

    /**
     * This method reads vocab cache from provided InputStream.
     * Please note: it reads only vocab content, so it's suitable mostly for BagOfWords/TF-IDF vectorizers
     *
     * @param stream
     * @return
     * @throws IOException
     */
    public static VocabCache<VocabWord> readVocabCache(InputStream stream) throws IOException {
        AbstractCache<VocabWord> vocabCache = new AbstractCache.Builder<VocabWord>().build();
        VocabWordFactory factory = new VocabWordFactory();
        boolean firstLine = true;
        long totalWordOcc = -1L;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // try to treat first line as header with 3 digits
                if (firstLine) {
                    firstLine = false;
                    String[] split = line.split("\\ ");

                    if (split.length != 3)
                        continue;

                    try {
                        vocabCache.setTotalDocCount(Long.valueOf(split[1]));
                        totalWordOcc = Long.valueOf(split[2]);
                        continue;
                    } catch (NumberFormatException e) {
                        // no-op
                    }
                }

                VocabWord word = factory.deserialize(line);

                vocabCache.addToken(word);
                vocabCache.addWordToIndex(word.getIndex(), word.getLabel());
            }
        }

        if (totalWordOcc >= 0)
            vocabCache.setTotalWordOccurences(totalWordOcc);

        return vocabCache;
    }

    /**
     * This is utility holder class
     */
    private static class ElementPair {
        private String object;
        private double[] vector;

        public ElementPair() {
        }

        public ElementPair(String object, double[] vector) {
            this.object = object;
            this.vector = vector;
        }

        /**
         * This utility method serializes ElementPair into JSON + packs it into Base64-encoded string
         *
         * @return
         */
        protected String toEncodedJson() {
            ObjectMapper mapper = SequenceElement.mapper();
            Base64 base64 = new Base64(Integer.MAX_VALUE);
            try {
                String json = mapper.writeValueAsString(this);
                String output = base64.encodeAsString(json.getBytes("UTF-8"));
                return output;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * This utility method returns ElementPair from Base64-encoded string
         *
         * @param encoded
         * @return
         */
        protected static ElementPair fromEncodedJson(String encoded) {
            ObjectMapper mapper = SequenceElement.mapper();
            try {
                String decoded = new String(Base64.decodeBase64(encoded), "UTF-8");
                return mapper.readValue(decoded, ElementPair.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public double[] getVector() {
            return vector;
        }

        public void setVector(double[] vector) {
            this.vector = vector;
        }
    }

    public static Word2Vec readAsBinaryNoLineBreaks(InputStream inputStream) {
        boolean originalPeriodic = Nd4j.getMemoryManager().isPeriodicGcActive();
        int originalFreq = Nd4j.getMemoryManager().getOccasionalGcFrequency();

        // try to load without linebreaks
        try {
            if (originalPeriodic) {
                Nd4j.getMemoryManager().togglePeriodicGc(true);
            }

            Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);

            return readBinaryModel(inputStream, false, false);
        } catch (Exception readModelException) {
            log.error("Cannot read binary model", readModelException);
            throw new RuntimeException("Unable to guess input file format. Please use corresponding loader directly");
        }
    }

    /**
     * This method loads Word2Vec model from binary input stream.
     *
     * @param inputStream  binary input stream
     * @return Word2Vec
     */
    public static Word2Vec readAsBinary(InputStream inputStream) {
        boolean originalPeriodic = Nd4j.getMemoryManager().isPeriodicGcActive();
        int originalFreq = Nd4j.getMemoryManager().getOccasionalGcFrequency();

        // we fallback to trying binary model instead
        try {
            log.debug("Trying binary model restoration...");

            if (originalPeriodic) {
                Nd4j.getMemoryManager().togglePeriodicGc(true);
            }

            Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);

            return readBinaryModel(inputStream, true, false);
        } catch (Exception readModelException) {
            throw new RuntimeException(readModelException);
        }
    }

    /**
     * This method loads Word2Vec model from csv file
     *
     * @param inputStream  input stream
     * @return Word2Vec model
     */
    public static Word2Vec readAsCsv(InputStream inputStream) {
        VectorsConfiguration configuration = new VectorsConfiguration();

        // let's try to load this file as csv file
        try {
            log.debug("Trying CSV model restoration...");

            Pair<InMemoryLookupTable, VocabCache> pair = loadTxt(inputStream);
            Word2Vec.Builder builder = new Word2Vec
                    .Builder()
                    .lookupTable(pair.getFirst())
                    .useAdaGrad(false)
                    .vocabCache(pair.getSecond())
                    .layerSize(pair.getFirst().layerSize())
                    // we don't use hs here, because model is incomplete
                    .useHierarchicSoftmax(false)
                    .resetModel(false);

            TokenizerFactory factory = getTokenizerFactory(configuration);
            if (factory != null) {
                builder.tokenizerFactory(factory);
            }

            return builder.build();
        } catch (Exception ex) {
            throw new RuntimeException("Unable to load model in CSV format");
        }
    }

    protected static TokenizerFactory getTokenizerFactory(VectorsConfiguration configuration) {
        if (configuration == null)
            return null;

        if (configuration.getTokenizerFactory() != null && !configuration.getTokenizerFactory().isEmpty()) {
            try {
                TokenizerFactory factory =
                                (TokenizerFactory) Class.forName(configuration.getTokenizerFactory()).newInstance();

                if (configuration.getTokenPreProcessor() != null && !configuration.getTokenPreProcessor().isEmpty()) {
                    TokenPreProcess preProcessor =
                                    (TokenPreProcess) Class.forName(configuration.getTokenPreProcessor()).newInstance();
                    factory.setTokenPreProcessor(preProcessor);
                }

                return factory;

            } catch (Exception e) {
                log.error("Can't instantiate saved TokenizerFactory: {}", configuration.getTokenizerFactory());
            }
        }
        return null;
    }

    /**
     * This method restores previously saved w2v model. File can be in one of the following formats:
     * 1) Binary model, either compressed or not. Like well-known Google Model
     * 2) Popular CSV word2vec text format
     * 3) DL4j compressed format
     *
     * In return you get StaticWord2Vec model, which might be used as lookup table only in multi-gpu environment.
     *
     * @param inputStream InputStream should point to previously saved w2v model
     * @return
     */
    public static WordVectors loadStaticModel(InputStream inputStream) throws IOException {

        File tmpFile = DL4JFileUtils.createTempFile("word2vec"+System.currentTimeMillis(), ".tmp");
        FileUtils.copyInputStreamToFile(inputStream, tmpFile);
        try {
            return loadStaticModel(tmpFile);
        } finally {
            tmpFile.delete();
        }

    }

    // TODO: this method needs better name :)
    /**
     * This method restores previously saved w2v model. File can be in one of the following formats:
     * 1) Binary model, either compressed or not. Like well-known Google Model
     * 2) Popular CSV word2vec text format
     * 3) DL4j compressed format
     *
     * In return you get StaticWord2Vec model, which might be used as lookup table only in multi-gpu environment.
     *
     * @param file File
     * @return
     */
    public static WordVectors loadStaticModel(File file) {
        if (!file.exists() || file.isDirectory())
            throw new RuntimeException(
                    new FileNotFoundException("File [" + file.getAbsolutePath() + "] was not found"));

        int originalFreq = Nd4j.getMemoryManager().getOccasionalGcFrequency();
        boolean originalPeriodic = Nd4j.getMemoryManager().isPeriodicGcActive();

        if (originalPeriodic)
            Nd4j.getMemoryManager().togglePeriodicGc(false);

        Nd4j.getMemoryManager().setOccasionalGcFrequency(50000);

        CompressedRamStorage<Integer> storage = new CompressedRamStorage.Builder<Integer>().useInplaceCompression(false)
                        .setCompressor(new NoOp()).emulateIsAbsent(false).build();

        VocabCache<VocabWord> vocabCache = new AbstractCache.Builder<VocabWord>().build();


        // now we need to define which file format we have here
        // if zip - that's dl4j format
        try {
            log.debug("Trying DL4j format...");
            File tmpFileSyn0 = DL4JFileUtils.createTempFile("word2vec", "syn");
            tmpFileSyn0.deleteOnExit();

            ZipFile zipFile = new ZipFile(file);
            ZipEntry syn0 = zipFile.getEntry("syn0.txt");
            InputStream stream = zipFile.getInputStream(syn0);

            FileUtils.copyInputStreamToFile(stream, tmpFileSyn0);
            storage.clear();

            try (Reader reader = new CSVReader(tmpFileSyn0)) {
                while (reader.hasNext()) {
                    Pair<VocabWord, float[]> pair = reader.next();
                    VocabWord word = pair.getFirst();
                    storage.store(word.getIndex(), pair.getSecond());

                    vocabCache.addToken(word);
                    vocabCache.addWordToIndex(word.getIndex(), word.getLabel());

                    Nd4j.getMemoryManager().invokeGcOccasionally();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (originalPeriodic)
                    Nd4j.getMemoryManager().togglePeriodicGc(true);

                Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);

                try {
                    tmpFileSyn0.delete();
                } catch (Exception e) {
                    //
                }
            }
        } catch (Exception e) {
            //
            try {
                // try to load file as text csv
                vocabCache = new AbstractCache.Builder<VocabWord>().build();
                storage.clear();
                log.debug("Trying CSVReader...");
                try (Reader reader = new CSVReader(file)) {
                    while (reader.hasNext()) {
                        Pair<VocabWord, float[]> pair = reader.next();
                        VocabWord word = pair.getFirst();
                        storage.store(word.getIndex(), pair.getSecond());

                        vocabCache.addToken(word);
                        vocabCache.addWordToIndex(word.getIndex(), word.getLabel());

                        Nd4j.getMemoryManager().invokeGcOccasionally();
                    }
                } catch (Exception ef) {
                    // we throw away this exception, and trying to load data as binary model
                    throw new RuntimeException(ef);
                } finally {
                    if (originalPeriodic)
                        Nd4j.getMemoryManager().togglePeriodicGc(true);

                    Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);
                }
            } catch (Exception ex) {
                // otherwise it's probably google model. which might be compressed or not
                log.debug("Trying BinaryReader...");
                vocabCache = new AbstractCache.Builder<VocabWord>().build();
                storage.clear();
                try (Reader reader = new BinaryReader(file)) {
                    while (reader.hasNext()) {
                        Pair<VocabWord, float[]> pair = reader.next();
                        VocabWord word = pair.getFirst();

                        storage.store(word.getIndex(), pair.getSecond());

                        vocabCache.addToken(word);
                        vocabCache.addWordToIndex(word.getIndex(), word.getLabel());

                        Nd4j.getMemoryManager().invokeGcOccasionally();
                    }
                } catch (Exception ez) {
                    throw new RuntimeException("Unable to guess input file format");
                } finally {
                    if (originalPeriodic)
                        Nd4j.getMemoryManager().togglePeriodicGc(true);

                    Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);
                }
            } finally {
                if (originalPeriodic)
                    Nd4j.getMemoryManager().togglePeriodicGc(true);

                Nd4j.getMemoryManager().setOccasionalGcFrequency(originalFreq);
            }
        }


        StaticWord2Vec word2Vec = new StaticWord2Vec.Builder(storage, vocabCache).build();

        return word2Vec;
    }


    protected interface Reader extends AutoCloseable {
        boolean hasNext();

        Pair<VocabWord, float[]> next();
    }


    protected static class BinaryReader implements Reader {

        protected DataInputStream stream;
        protected String nextWord;
        protected int numWords;
        protected int vectorLength;
        protected AtomicInteger idxCounter = new AtomicInteger(0);


        protected BinaryReader(File file) {
            try {
                // Try to read as GZip
                stream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
            }
            catch (IOException e) {
                try {
                    // Failed to read as Gzip, assuming it's not compressed binary format
                    stream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                } catch (Exception e1) {
                    throw new RuntimeException(e1);
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                numWords = Integer.parseInt(ReadHelper.readString(stream));
                vectorLength = Integer.parseInt(ReadHelper.readString(stream));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean hasNext() {
            return idxCounter.get() < numWords;
        }

        @Override
        public Pair<VocabWord, float[]> next() {
            try {
                String word = ReadHelper.readString(stream);
                VocabWord element = new VocabWord(1.0, word);
                element.setIndex(idxCounter.getAndIncrement());

                float[] vector = new float[vectorLength];
                for (int i = 0; i < vectorLength; i++) {
                    vector[i] = ReadHelper.readFloat(stream);
                }

                return Pair.makePair(element, vector);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void close() throws Exception {
            if (stream != null)
                stream.close();
        }
    }

    protected static class CSVReader implements Reader {
        private BufferedReader reader;
        private AtomicInteger idxCounter = new AtomicInteger(0);
        private String nextLine;

        protected CSVReader(File file) {
            try {
                reader = new BufferedReader(new FileReader(file));
                nextLine = reader.readLine();

                // checking if there's header inside
                String[] split = nextLine.split(" ");
                try {
                    if (Integer.parseInt(split[0]) > 0 && split.length <= 5) {
                        // this is header. skip it.
                        nextLine = reader.readLine();
                    }
                } catch (Exception e) {
                    // this is proper string, do nothing
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public boolean hasNext() {
            return nextLine != null;
        }

        public Pair<VocabWord, float[]> next() {

            String[] split = nextLine.split(" ");

            VocabWord word = new VocabWord(1.0, ReadHelper.decodeB64(split[0]));
            word.setIndex(idxCounter.getAndIncrement());

            float[] vector = new float[split.length - 1];
            for (int i = 1; i < split.length; i++) {
                vector[i - 1] = Float.parseFloat(split[i]);
            }

            try {
                nextLine = reader.readLine();
            } catch (Exception e) {
                nextLine = null;
            }

            return Pair.makePair(word, vector);
        }

        @Override
        public void close() throws Exception {
            if (reader != null)
                reader.close();
        }
    }

    public static <T extends SequenceElement> WeightLookupTable<T> readLookupTable(InputStream stream)
            throws IOException {
        WeightLookupTable<T> weightLookupTable = null;
        AbstractCache<VocabWord> vocabCache = new AbstractCache<>();
        final int startSyn0 = 4;
        boolean headerRead = false;
        int numWords = -1, layerSize = -1, totalNumberOfDocs = -1;
        try {
            INDArray syn0 = null;
            int index = 0;
            for (String line : IOUtils.readLines(stream)) {
                String[] tokens = line.split(" ");
                if (!headerRead) {
                    // reading header as "NUM_WORDS VECTOR_SIZE NUM_DOCS"
                    numWords = Integer.parseInt(tokens[0]);
                    layerSize = Integer.parseInt(tokens[1]);
                    totalNumberOfDocs = Integer.parseInt(tokens[2]);
                    log.debug("Reading header - words: {}, layerSize: {}, totalNumberOfDocs: {}",
                            numWords, layerSize, totalNumberOfDocs);
                    headerRead = true;
                    weightLookupTable = new InMemoryLookupTable.Builder().cache(vocabCache).vectorLength(layerSize).build();
                } else {
                    String label = ReadHelper.decodeB64(tokens[0]);
                    int freq = Integer.parseInt(tokens[1]);
                    int rows = Integer.parseInt(tokens[2]);
                    int cols = Integer.parseInt(tokens[3]);

                    if (syn0 == null)
                        syn0  = Nd4j.createUninitialized(rows, cols);

                    int i = startSyn0;
                    for (int r = 0; r < rows; ++r) {
                        double[] vector = new double[cols];
                        for (int c = 0;  c < cols; ++c) {
                            vector[c] = Double.parseDouble(tokens[i]);
                            ++i;
                        }
                        syn0.putRow(r, Nd4j.create(vector));
                    }

                    VocabWord vw = new VocabWord(freq, label);
                    vw.setIndex(index);
                    weightLookupTable.getVocabCache().addToken((T)vw);
                    weightLookupTable.getVocabCache().addWordToIndex(index, label);
                    ++index;
                }
            }
            ((InMemoryLookupTable<T>) weightLookupTable).setSyn0(syn0);
        }
        finally {
            stream.close();
        }
        return weightLookupTable;
    }

    /**
     * This method loads Word2Vec model from input stream
     *
     * @param stream InputStream
     * @param readExtendedTable boolean
     * @return Word2Vec
     */
    public static Word2Vec readWord2Vec(InputStream stream, boolean readExtendedTable) throws IOException {
        SequenceVectors<VocabWord> vectors = readSequenceVectors(stream, readExtendedTable);
        Word2Vec word2Vec = new Word2Vec.Builder(vectors.getConfiguration()).layerSize(vectors.getLayerSize()).build();
        word2Vec.setVocab(vectors.getVocab());
        word2Vec.setLookupTable(vectors.lookupTable());
        word2Vec.setModelUtils(vectors.getModelUtils());
        return word2Vec;
    }

    /**
     * This method prints memory usage to log
     *
     * @param numWords
     * @param vectorLength
     * @param numTables
     */
    public static void printOutProjectedMemoryUse(long numWords, int vectorLength, int numTables) {
        double memSize = numWords * vectorLength * Nd4j.sizeOfDataType() * numTables;

        String sfx;
        double value;
        if (memSize < 1024 * 1024L) {
            sfx = "KB";
            value = memSize / 1024;
        }
        if (memSize < 1024 * 1024L * 1024L) {
            sfx = "MB";
            value = memSize / 1024 / 1024;
        } else {
            sfx = "GB";
            value = memSize / 1024 / 1024 / 1024;
        }

        log.info("Projected memory use for model: [{} {}]", String.format("%.2f", value), sfx);
    }

    /**
    *   Helper static methods to read data from input stream.
    */
    public static class ReadHelper {
        /**
         * Read a float from a data input stream Credit to:
         * https://github.com/NLPchina/Word2VEC_java/blob/master/src/com/ansj/vec/Word2VEC.java
         *
         * @param is
         * @return
         * @throws IOException
         */
        private static float readFloat(InputStream is) throws IOException {
            byte[] bytes = new byte[4];
            is.read(bytes);
            return getFloat(bytes);
        }

        /**
         * Read a string from a data input stream Credit to:
         * https://github.com/NLPchina/Word2VEC_java/blob/master/src/com/ansj/vec/Word2VEC.java
         *
         * @param b
         * @return
         */
        private static float getFloat(byte[] b) {
            int accum = 0;
            accum = accum | (b[0] & 0xff) << 0;
            accum = accum | (b[1] & 0xff) << 8;
            accum = accum | (b[2] & 0xff) << 16;
            accum = accum | (b[3] & 0xff) << 24;
            return Float.intBitsToFloat(accum);
        }

        /**
         * Read a string from a data input stream Credit to:
         * https://github.com/NLPchina/Word2VEC_java/blob/master/src/com/ansj/vec/Word2VEC.java
         *
         * @param dis
         * @return
         * @throws IOException
         */
        private static String readString(DataInputStream dis) throws IOException {
            byte[] bytes = new byte[MAX_SIZE];
            byte b = dis.readByte();
            int i = -1;
            StringBuilder sb = new StringBuilder();
            while (b != 32 && b != 10) {
                i++;
                bytes[i] = b;
                b = dis.readByte();
                if (i == 49) {
                    sb.append(new String(bytes, "UTF-8"));
                    i = -1;
                    bytes = new byte[MAX_SIZE];
                }
            }
            sb.append(new String(bytes, 0, i + 1, "UTF-8"));
            return sb.toString();
        }

        private static final String B64 = "B64:";

        /**
         * Encode input string
         *
         * @param word String
         * @return String
         */
        public static String encodeB64(String word) {
            try {
                return B64 + Base64.encodeBase64String(word.getBytes("UTF-8")).replaceAll("(\r|\n)", "");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Encode input string
         *
         * @param word String
         * @return String
         */

        public static String decodeB64(String word) {
            if (word.startsWith(B64)) {
                String arp = word.replaceFirst(B64, "");
                try {
                    return new String(Base64.decodeBase64(arp), "UTF-8");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else
                return word;
        }
    }
}
