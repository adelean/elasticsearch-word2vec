package com.adelean.elasticsearch.word2vec.nd4j;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bytedeco.javacpp.Pointer;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.buffer.DataTypeEx;
import org.nd4j.linalg.api.memory.MemoryWorkspace;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.BaseNDArrayFactory;

public final class JvmNDArrayFactory extends BaseNDArrayFactory {

    public JvmNDArrayFactory() {
    }

    public JvmNDArrayFactory(DataType dtype, Character order) {
        super(dtype, order);
    }

    public JvmNDArrayFactory(DataType dtype, char order) {
        super(dtype, order);
    }

    @Override
    public void createBlas() {

    }

    @Override
    public void createLevel1() {

    }

    @Override
    public void createLevel2() {

    }

    @Override
    public void createLevel3() {

    }

    @Override
    public void createLapack() {

    }

    @Override
    public INDArray create(int[] shape, DataBuffer buffer) {
        return null;
    }

    @Override
    public INDArray toFlattened(char order, Collection<INDArray> matrices) {
        return null;
    }

    @Override
    public INDArray create(double[][] data) {
        return null;
    }

    @Override
    public INDArray create(double[][] data, char ordering) {
        return null;
    }

    @Override
    public INDArray specialConcat(int dimension, INDArray... toConcat) {
        return null;
    }

    @Override
    public INDArray pullRows(INDArray source, int sourceDimension, long[] indexes) {
        return null;
    }

    @Override
    public INDArray pullRows(INDArray source, INDArray destination, int sourceDimension, int[] indexes) {
        return null;
    }

    @Override
    public void shuffle(INDArray array, Random rnd, int... dimension) {

    }

    @Override
    public void shuffle(Collection<INDArray> array, Random rnd, int... dimension) {

    }

    @Override
    public void shuffle(List<INDArray> array, Random rnd, List<int[]> dimensions) {

    }

    @Override
    public INDArray average(INDArray target, INDArray[] arrays) {
        return null;
    }

    @Override
    public INDArray average(INDArray[] arrays) {
        return null;
    }

    @Override
    public INDArray average(Collection<INDArray> arrays) {
        return null;
    }

    @Override
    public INDArray accumulate(INDArray target, INDArray... arrays) {
        return null;
    }

    @Override
    public INDArray average(INDArray target, Collection<INDArray> arrays) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, long rows, long columns, int[] stride, long offset) {
        return null;
    }

    @Override
    public INDArray create(float[] data, int[] shape, int[] stride, long offset) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, long offset) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, char order, DataType dataType) {
        return null;
    }

    @Override
    public INDArray create(double[] data, int[] shape, int[] stride, long offset) {
        return null;
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, long offset) {
        return null;
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(long[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(int[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(short[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(byte[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(boolean[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(List<INDArray> list, int[] shape) {
        return null;
    }

    @Override
    public INDArray create(List<INDArray> list, long[] shape) {
        return null;
    }

    @Override
    public INDArray create(long rows, long columns, long[] stride, long offset) {
        return null;
    }

    @Override
    public INDArray empty(DataType type) {
        return null;
    }

    @Override
    public INDArray create(float[][] floats) {
        return null;
    }

    @Override
    public INDArray create(float[][] data, char ordering) {
        return null;
    }

    @Override
    public INDArray create(float[] data, int[] shape, int[] stride, long offset, char ordering) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer buffer, int[] shape, long offset) {
        return null;
    }

    @Override
    public INDArray create(int[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray create(long[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray create(DataType dataType, long[] shape, char ordering, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(DataType dataType, long[] shape, long[] strides, char ordering, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray createUninitialized(int[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray createUninitialized(long[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray createUninitialized(DataType dataType, long[] shape, char ordering, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray createUninitializedDetached(DataType dataType, char ordering, long... shape) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, int[] newShape, int[] newStride, long offset, char ordering) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, long[] newShape, long[] newStride, long offset, char ordering) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, long[] newShape, long[] newStride, long offset, long ews, char ordering) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, long[] newShape, long[] newStride, long offset, char ordering, DataType dataType) {
        return null;
    }

    @Override
    public INDArray create(float[] data, int[] shape, long offset, Character order) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long rows, long columns, int[] stride, long offset, char ordering) {
        return null;
    }

    @Override
    public INDArray create(double[] data, int[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray create(List<INDArray> list, int[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray create(List<INDArray> list, long[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray create(double[] data, int[] shape, long offset) {
        return null;
    }

    @Override
    public INDArray create(double[] data, int[] shape, int[] stride, long offset, char ordering) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, char order, long offset) {
        return null;
    }

    @Override
    public INDArray convertDataEx(DataTypeEx typeSrc, INDArray source, DataTypeEx typeDst) {
        return null;
    }

    @Override
    public DataBuffer convertDataEx(DataTypeEx typeSrc, DataBuffer source, DataTypeEx typeDst) {
        return null;
    }

    @Override
    public void convertDataEx(DataTypeEx typeSrc, DataBuffer source, DataTypeEx typeDst, DataBuffer target) {

    }

    @Override
    public void convertDataEx(DataTypeEx typeSrc, Pointer source, DataTypeEx typeDst, Pointer target, long length) {

    }

    @Override
    public void convertDataEx(DataTypeEx typeSrc, Pointer source, DataTypeEx typeDst, DataBuffer buffer) {

    }

    @Override
    public INDArray createFromNpyPointer(Pointer pointer) {
        return null;
    }

    @Override
    public INDArray createFromNpyHeaderPointer(Pointer pointer) {
        return null;
    }

    @Override
    public INDArray createFromNpyFile(File file) {
        return null;
    }

    @Override
    public Map<String, INDArray> createFromNpzFile(File file) throws Exception {
        return null;
    }

    @Override
    public Pointer convertToNumpy(INDArray array) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, long offset, char ordering) {
        return null;
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, long offset, char ordering) {
        return null;
    }

    @Override
    public INDArray[] tear(INDArray tensor, int... dimensions) {
        return new INDArray[0];
    }

    @Override
    public INDArray sort(INDArray x, boolean descending) {
        return null;
    }

    @Override
    public INDArray sort(INDArray x, boolean descending, int... dimensions) {
        return null;
    }

    @Override
    public INDArray sortCooIndices(INDArray x) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, long offset, Character order) {
        return null;
    }

    @Override
    public INDArray create(double[] data, long[] shape, long offset, Character order) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray create(double[] data, long[] shape, char ordering) {
        return null;
    }

    @Override
    public INDArray create(Collection<String> strings, long[] shape, char order) {
        return null;
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(long[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(int[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(short[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(byte[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(boolean[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, int[] shape) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, long[] shape) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, int[] shape, int[] stride, long offset) {
        return null;
    }

    @Override
    public INDArray create(DataBuffer data, long[] shape, long[] stride, long offset) {
        return null;
    }
}
