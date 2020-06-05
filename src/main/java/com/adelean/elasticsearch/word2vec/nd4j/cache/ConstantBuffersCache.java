package com.adelean.elasticsearch.word2vec.nd4j.cache;

import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.cache.BasicConstantHandler;

public final class ConstantBuffersCache extends BasicConstantHandler {

    @Override
    public DataBuffer getConstantBuffer(boolean[] array, DataType dataType) {
        return null;
    }

    @Override
    public DataBuffer getConstantBuffer(int[] array, DataType dataType) {
        return null;
    }

    @Override
    public DataBuffer getConstantBuffer(long[] array, DataType dataType) {
        return null;
    }

    @Override
    public DataBuffer getConstantBuffer(double[] array, DataType dataType) {
        return null;
    }

    @Override
    public DataBuffer getConstantBuffer(float[] array, DataType dataType) {
        return null;
    }

    @Override
    public void purgeConstants() {

    }

    @Override
    public long getCachedBytes() {
        return 0;
    }
}
