package io.hosuaby.nd4j.backend.jvm;

import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.ndarray.BaseNDArray;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.shape.LongShapeDescriptor;

import com.google.flatbuffers.FlatBufferBuilder;

public final class NDArray extends BaseNDArray {

    @Override
    protected int stringBuffer(FlatBufferBuilder builder, DataBuffer buffer) {
        return 0;
    }

    @Override
    public String getString(long index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongShapeDescriptor shapeDescriptor() {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray unsafeDuplication() {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray unsafeDuplication(boolean blocking) {
        throw new UnsupportedOperationException();
    }
}
