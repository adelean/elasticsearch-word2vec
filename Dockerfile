FROM elasticsearch:6.5.4

COPY build/distributions/word2vec-0.1.0-SNAPSHOT.zip /word2vec-0.1.0-SNAPSHOT.zip

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch "file:///word2vec-0.1.0-SNAPSHOT.zip"

VOLUME /usr/share/elasticsearch/data
