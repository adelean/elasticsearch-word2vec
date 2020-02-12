FROM elasticsearch:6.5.4

COPY build/distributions/word2vec-0.1.0-alpha.zip /word2vec.zip

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch "file:///word2vec.zip"

VOLUME /usr/share/elasticsearch/data
