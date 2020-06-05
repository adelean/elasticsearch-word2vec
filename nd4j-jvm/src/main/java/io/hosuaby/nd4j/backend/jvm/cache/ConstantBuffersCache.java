package io.hosuaby.nd4j.backend.jvm.cache;

import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.cache.BasicConstantHandler;

public final class ConstantBuffersCache extends BasicConstantHandler {

    @Override
    public DataBuffer getConstantBuffer(boolean[] array, DataType dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer getConstantBuffer(int[] array, DataType dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer getConstantBuffer(long[] array, DataType dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer getConstantBuffer(double[] array, DataType dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer getConstantBuffer(float[] array, DataType dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void purgeConstants() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getCachedBytes() {
        return 0;
    }
}
