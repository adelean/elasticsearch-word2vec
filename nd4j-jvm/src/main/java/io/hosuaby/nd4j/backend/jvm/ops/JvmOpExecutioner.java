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
        return null;
    }

    @Override
    public INDArray exec(Op op, OpContext opContext) {
        return null;
    }

    @Override
    public INDArray exec(ReduceOp op) {
        return null;
    }

    @Override
    public INDArray exec(Variance accumulation) {
        return null;
    }

    @Override
    public INDArray exec(IndexAccumulation op) {
        return null;
    }

    @Override
    public INDArray exec(BroadcastOp broadcast) {
        return null;
    }

    @Override
    public INDArray exec(ScalarOp op) {
        return null;
    }

    @Override
    public INDArray exec(RandomOp op, Random rng) {
        return null;
    }
}
