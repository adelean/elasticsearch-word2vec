# elasticsearch-word2vec
[![Build Status](https://travis-ci.org/adelean/elasticsearch-word2vec.svg?branch=master)](https://travis-ci.org/adelean/elasticsearch-word2vec)

Word2vec plugin for Elasticsearch

```bash
unzip -q model-uploader-0.1.0-SNAPSHOT.zip
```

## Example

```js
GET /_analyze
{
  "tokenizer": "whitespace",
  "filter": [
    "lowercase",
    {
      "type": "stop",
      "stopwords": "_english_"
    },
    {
      "type": "synonym_word2vec",
      "model": "GoogleNews-vectors-negative300-SLIM",
      "nearest_words": 3
    }
  ],
  "text": "The quick brown fox jumps over the lazy dog"
}

// Found synonyms:
//   [quick -> swift speedy fast]
//   [brown -> brownish reddish white]
//   [fox -> foxes squirrel rabbit]
//   [jumps -> jump climbs jumping]
//   [over -> past Over within]
//   [lazy -> laziness slothful layabouts]
//   [dog -> dogs puppy pooch]
```

Another example:

```js
GET /_analyze
{
  "tokenizer": "standard",
  "filter": [
    "lowercase",
    {
      "type": "stop",
      "stopwords": "_english_"
    },
    {
      "type": "synonym_word2vec",
      "model": "GoogleNews-vectors-negative300-SLIM",
      "threshold": 0.7
    }
  ],
  "text": "Very soon the Rabbit noticed Alice, as she went hunting about, and called out to her
           in an angry tone"
}

// Produces tokens: [ very extremely incredibly quite pretty extraordinarily 
// exceedingly unbelievably soon rabbit rabbits noticed noticing alice
// she her She went came hunting hunters hunts Hunting hunt about called out
// her she herself Her angry irate enraged indignant tone ]
```

### Relative cosine similarity

Plugin provides synonym extraction using Relative Consine Similarity from paper [A Minimally Supervised Approach for 
Synonym Extraction with Word Embeddings by Artuur Leeuwenbergtuur, Mihaela Vela, Jon Dehdari and
Josef van Genabith](https://ufal.mff.cuni.cz/pbml/105/art-leeuwenberg-et-al.pdf). To use it set flag `rcs` to `true`.

Example:

```js
GET /_analyze
{
  "tokenizer": "standard",
  "filter": [
    "lowercase",
    {
      "type": "stop",
      "stopwords": "_english_"
    },
    {
      "type": "synonym_word2vec",
      "model": "GoogleNews-vectors-negative300-SLIM",
      "rcs": true,              // activates relative cosine similarity
      "nearest_words": 20,      // default: 20
      "threshold": 0.05         // default: (1.0 / nearest_words)
    }
  ],
  "text": "The quick brown fox jumps over the lazy dog"
}
``` 
