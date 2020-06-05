package io.hosuaby.nd4j.backend.jvm.ops;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BroadcastOp;
import org.nd4j.linalg.api.ops.IndexAccumulation;
import org.nd4j.linalg.api.ops.Op;
import org.nd4j.linalg.api.ops.OpContext;
import org.nd4j.linalg.api.ops.RandomOp;
import org.nd4j.linalg.api.ops.ReduceOp;
import org.nd4j.linalg.api.ops.ScalarOp;
import org.nd4j.linalg.api.ops.executioner.DefaultOpExecutioner;
import org.nd4j.linalg.api.ops.impl.summarystats.Variance;
import org.nd4j.linalg.api.rng.Random;

public final class JvmOpExecutioner extends DefaultOpExecutioner {

    @Override
    public INDArray exec(Op op) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray exec(Op op, OpContext opContext) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray exec(ReduceOp op) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray exec(Variance accumulation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray exec(IndexAccumulation op) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray exec(BroadcastOp broadcast) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray exec(ScalarOp op) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray exec(RandomOp op, Random rng) {
        throw new UnsupportedOperationException();
    }
}
