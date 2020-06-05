package com.adelean.elasticsearch.word2vec.nd4j.rng;

import org.bytedeco.javacpp.Pointer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.rng.Random;

public final class JavaRandom implements Random {

    @Override
    public void setSeed(int seed) {

    }

    @Override
    public void setSeed(int[] seed) {

    }

    @Override
    public void setSeed(long seed) {

    }

    @Override
    public long getSeed() {
        return 0;
    }

    @Override
    public void nextBytes(byte[] bytes) {

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
        return null;
    }

    @Override
    public INDArray nextGaussian(long[] shape) {
        return null;
    }

    @Override
    public INDArray nextGaussian(char order, int[] shape) {
        return null;
    }

    @Override
    public INDArray nextGaussian(char order, long[] shape) {
        return null;
    }

    @Override
    public INDArray nextDouble(int[] shape) {
        return null;
    }

    @Override
    public INDArray nextDouble(long[] shape) {
        return null;
    }

    @Override
    public INDArray nextDouble(char order, int[] shape) {
        return null;
    }

    @Override
    public INDArray nextDouble(char order, long[] shape) {
        return null;
    }

    @Override
    public INDArray nextFloat(int[] shape) {
        return null;
    }

    @Override
    public INDArray nextFloat(long[] shape) {
        return null;
    }

    @Override
    public INDArray nextFloat(char order, int[] shape) {
        return null;
    }

    @Override
    public INDArray nextFloat(char order, long[] shape) {
        return null;
    }

    @Override
    public INDArray nextInt(int[] shape) {
        return null;
    }

    @Override
    public INDArray nextInt(long[] shape) {
        return null;
    }

    @Override
    public INDArray nextInt(int n, int[] shape) {
        return null;
    }

    @Override
    public INDArray nextInt(int n, long[] shape) {
        return null;
    }

    @Override
    public Pointer getStatePointer() {
        return null;
    }

    @Override
    public long getPosition() {
        return 0;
    }

    @Override
    public void reSeed() {

    }

    @Override
    public void reSeed(long seed) {

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

    }

    @Override
    public void close() throws Exception {

    }
}
