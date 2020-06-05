package io.hosuaby.nd4j.backend.jvm.rng;

import org.bytedeco.javacpp.Pointer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.rng.Random;

public final class JavaRandom implements Random {

    @Override
    public void setSeed(int seed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSeed(int[] seed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSeed(long seed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getSeed() {
        return 0;
    }

    @Override
    public void nextBytes(byte[] bytes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int nextInt() {
        return 0;
    }

    @Override
    public int nextInt(int n) {
        return 0;
    }

    @Override
    public int nextInt(int a, int n) {
        return 0;
    }

    @Override
    public long nextLong() {
        return 0;
    }

    @Override
    public boolean nextBoolean() {
        return false;
    }

    @Override
    public float nextFloat() {
        return 0;
    }

    @Override
    public double nextDouble() {
        return 0;
    }

    @Override
    public double nextGaussian() {
        return 0;
    }

    @Override
    public INDArray nextGaussian(int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextGaussian(long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextGaussian(char order, int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextGaussian(char order, long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextDouble(int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextDouble(long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextDouble(char order, int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextDouble(char order, long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextFloat(int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextFloat(long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextFloat(char order, int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextFloat(char order, long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextInt(int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextInt(long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextInt(int n, int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray nextInt(int n, long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getStatePointer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getPosition() {
        return 0;
    }

    @Override
    public void reSeed() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reSeed(long seed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long rootState() {
        return 0;
    }

    @Override
    public long nodeState() {
        return 0;
    }

    @Override
    public void setStates(long rootState, long nodeState) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException();
    }
}
