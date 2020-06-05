package com.adelean.elasticsearch.word2vec.nd4j;

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
        return null;
    }

    @Override
    public LongShapeDescriptor shapeDescriptor() {
        return null;
    }

    @Override
    public INDArray unsafeDuplication() {
        return null;
    }

    @Override
    public INDArray unsafeDuplication(boolean blocking) {
        return null;
    }
}
