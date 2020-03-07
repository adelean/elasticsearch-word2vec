FROM elasticsearch:6.5.4

COPY build/distributions/elasticsearch-word2vec-plugin-*.zip /elasticsearch-word2vec-plugin.zip

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch "file:///elasticsearch-word2vec-plugin.zip"

VOLUME /usr/share/elasticsearch/data
