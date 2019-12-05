package com.adelean.elasticsearch.word2vec.utils;

import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public abstract class ScrollInputStream extends InputStream {
    private static final TimeValue KEEP_ALIVE_SCROLL = new TimeValue(1, TimeUnit.MINUTES);
    private static final int END_OF_STREAM = -1;

    private final SearchRequestBuilder searchRequest;
    private final Client client;

    private String scrollId = null;
    private SearchHit[] hits = null;
    private int hitsPos = 0;
    private byte[] bytes = null;
    private int bytesPos = 0;

    public ScrollInputStream(SearchRequestBuilder searchRequest, Client client) {
        this.searchRequest = searchRequest;
        this.client = client;
    }

    @Override
    public int read() throws IOException {
        if (bytes != null && bytesPos < bytes.length) {
            byte b = bytes[bytesPos++];
            return b < 0 ? 256 + b : b;
        } else if (hits != null && hitsPos < hits.length) {
            SearchHit nextHit = hits[hitsPos++];
            bytes = extract(nextHit);
            bytesPos = 0;
            return read();
        } else {
            SearchResponse searchResponse = fetchNextDocuments();

            if (searchResponse.getHits().totalHits > 0) {
                hits = searchResponse.getHits().getHits();
                hitsPos = 0;
                bytes = null;
                bytesPos = 0;
                return read();
            } else {
                return END_OF_STREAM;
            }
        }
    }

    private SearchResponse fetchNextDocuments() {
        SearchResponse searchResponse;

        if (scrollId != null) {
            searchResponse = client
                    .prepareSearchScroll(scrollId)
                    .setScroll(KEEP_ALIVE_SCROLL)
                    .execute()
                    .actionGet();
        } else {
            searchResponse = searchRequest
                    .setScroll(KEEP_ALIVE_SCROLL)
                    .execute()
                    .actionGet();
            scrollId = searchResponse.getScrollId();
        }

        return searchResponse;
    }

    @Override
    public void close() {
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        client.clearScroll(clearScrollRequest);
    }

    protected abstract byte[] extract(SearchHit searchHit);
}
