package io.hosuaby.nd4j.backend.jvm;

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
        throw new UnsupportedOperationException();
    }

    @Override
    public void createLevel1() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createLevel2() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createLevel3() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createLapack() {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(int[] shape, DataBuffer buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray toFlattened(char order, Collection<INDArray> matrices) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[][] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[][] data, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray specialConcat(int dimension, INDArray... toConcat) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray pullRows(INDArray source, int sourceDimension, long[] indexes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray pullRows(INDArray source, INDArray destination, int sourceDimension, int[] indexes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shuffle(INDArray array, Random rnd, int... dimension) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shuffle(Collection<INDArray> array, Random rnd, int... dimension) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shuffle(List<INDArray> array, Random rnd, List<int[]> dimensions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray average(INDArray target, INDArray[] arrays) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray average(INDArray[] arrays) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray average(Collection<INDArray> arrays) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray accumulate(INDArray target, INDArray... arrays) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray average(INDArray target, Collection<INDArray> arrays) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, long rows, long columns, int[] stride, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, int[] shape, int[] stride, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, char order, DataType dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, int[] shape, int[] stride, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(long[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(int[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(short[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(byte[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(boolean[] data, long[] shape, long[] stride, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(List<INDArray> list, int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(List<INDArray> list, long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(long rows, long columns, long[] stride, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray empty(DataType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[][] floats) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[][] data, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, int[] shape, int[] stride, long offset, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer buffer, int[] shape, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(int[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(long[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataType dataType, long[] shape, char ordering, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataType dataType, long[] shape, long[] strides, char ordering, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray createUninitialized(int[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray createUninitialized(long[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray createUninitialized(DataType dataType, long[] shape, char ordering, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray createUninitializedDetached(DataType dataType, char ordering, long... shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, int[] newShape, int[] newStride, long offset, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, long[] newShape, long[] newStride, long offset, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, long[] newShape, long[] newStride, long offset, long ews, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, long[] newShape, long[] newStride, long offset, char ordering, DataType dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, int[] shape, long offset, Character order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long rows, long columns, int[] stride, long offset, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, int[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(List<INDArray> list, int[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(List<INDArray> list, long[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, int[] shape, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, int[] shape, int[] stride, long offset, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, char order, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray convertDataEx(DataTypeEx typeSrc, INDArray source, DataTypeEx typeDst) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer convertDataEx(DataTypeEx typeSrc, DataBuffer source, DataTypeEx typeDst) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertDataEx(DataTypeEx typeSrc, DataBuffer source, DataTypeEx typeDst, DataBuffer target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertDataEx(DataTypeEx typeSrc, Pointer source, DataTypeEx typeDst, Pointer target, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertDataEx(DataTypeEx typeSrc, Pointer source, DataTypeEx typeDst, DataBuffer buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray createFromNpyPointer(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray createFromNpyHeaderPointer(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray createFromNpyFile(File file) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, INDArray> createFromNpzFile(File file) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer convertToNumpy(INDArray array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, long offset, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, long[] shape, long[] stride, long offset, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray[] tear(INDArray tensor, int... dimensions) {
        return new INDArray[0];
    }

    @Override
    public INDArray sort(INDArray x, boolean descending) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray sort(INDArray x, boolean descending, int... dimensions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray sortCooIndices(INDArray x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, long offset, Character order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, long[] shape, long offset, Character order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(double[] data, long[] shape, char ordering) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(Collection<String> strings, long[] shape, char order) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(float[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(long[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(int[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(short[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(byte[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(boolean[] data, long[] shape, long[] stride, char order, DataType dataType, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, int[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, long[] shape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, int[] shape, int[] stride, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INDArray create(DataBuffer data, long[] shape, long[] stride, long offset) {
        throw new UnsupportedOperationException();
    }
}
