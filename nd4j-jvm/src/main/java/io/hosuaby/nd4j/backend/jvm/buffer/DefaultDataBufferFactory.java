package io.hosuaby.nd4j.backend.jvm.buffer;

import java.nio.ByteBuffer;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.Indexer;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.buffer.factory.DataBufferFactory;
import org.nd4j.linalg.api.memory.MemoryWorkspace;

public final class DefaultDataBufferFactory implements DataBufferFactory {

    @Override
    public void setAllocationMode(DataBuffer.AllocationMode allocationMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer.AllocationMode allocationMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(DataBuffer underlyingBuffer, long offset, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(ByteBuffer underlyingBuffer, DataType type, long length, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createSame(DataBuffer buffer, boolean init) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createSame(DataBuffer buffer, boolean init, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long offset, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long offset, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, double[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, byte[] data, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, byte[] data, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long offset, double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, float[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long offset, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long offset, int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long offset, double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long offset, float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long offset, float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long offset, float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(DataType dataType, long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(DataType dataType, long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createBFloat16(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createByte(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createShort(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createBool(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUShort(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUInt(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUByte(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createULong(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createBFloat16(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createByte(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createShort(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createBool(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUShort(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUInt(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUByte(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createULong(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createBFloat16(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createByte(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createShort(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createBool(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUShort(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUInt(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUByte(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createULong(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createLong(long[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createLong(long[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createLong(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createLong(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createLong(long[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createLong(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(int[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(int[] data, boolean copy, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(float[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createLong(int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(long[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(long[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(long[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(double[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(double[] data, boolean copy, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createDouble(float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createFloat(float[] data, boolean copy, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createInt(float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(Pointer pointer, DataType type, long length, Indexer indexer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(Pointer pointer, Pointer specialPointer, DataType type, long length, Indexer indexer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(DoublePointer doublePointer, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(IntPointer intPointer, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer create(FloatPointer floatPointer, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long length, boolean initialize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long length, boolean initialize, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(float[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(float[] data, boolean copy, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, double[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, float[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, float[] data, MemoryWorkspace workspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, byte[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(int[] data, boolean copy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(float[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(double[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(int[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createHalf(long offset, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends DataBuffer> intBufferClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends DataBuffer> longBufferClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends DataBuffer> halfBufferClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends DataBuffer> floatBufferClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<? extends DataBuffer> doubleBufferClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataBuffer createUtf8Buffer(byte[] data, long product) {
        throw new UnsupportedOperationException();
    }
}
