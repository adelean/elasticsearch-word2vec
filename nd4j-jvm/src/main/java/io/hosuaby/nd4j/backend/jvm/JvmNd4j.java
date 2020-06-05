package io.hosuaby.nd4j.backend.jvm;

import org.bytedeco.javacpp.BooleanPointer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.nd4j.nativeblas.NativeOps;
import org.nd4j.nativeblas.OpaqueConstantDataBuffer;
import org.nd4j.nativeblas.OpaqueContext;
import org.nd4j.nativeblas.OpaqueDataBuffer;
import org.nd4j.nativeblas.OpaqueLaunchContext;
import org.nd4j.nativeblas.OpaqueRandomGenerator;
import org.nd4j.nativeblas.OpaqueResultWrapper;
import org.nd4j.nativeblas.OpaqueShapeList;
import org.nd4j.nativeblas.OpaqueTadPack;
import org.nd4j.nativeblas.OpaqueVariable;
import org.nd4j.nativeblas.OpaqueVariablesSet;

public final class JvmNd4j implements NativeOps {

    @Override
    public void setElementThreshold(int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTADThreshold(int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execIndexReduceScalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dXShapeInfo, Pointer extraParams, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dZShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execIndexReduce(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dXShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dResultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execBroadcast(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execBroadcastBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execPairwiseTransform(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execPairwiseTransformBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceFloat(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceSame(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceLong(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceFloat2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceSame2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceBool2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduceLong2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduce3(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduce3Scalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduce3Tad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dresultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer tadOnlyShapeInfo, LongPointer tadOffsets, LongPointer yTadOnlyShapeInfo, LongPointer yTadOffsets) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execReduce3All(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dresultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer xTadShape, LongPointer xOffsets, LongPointer yTadShape, LongPointer yOffsets) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execScalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer scalar, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execScalarBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer scalar, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execSummaryStatsScalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, boolean biasCorrected) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execSummaryStats(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, boolean biasCorrected) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execSummaryStatsTad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dresultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, boolean biasCorrected, LongPointer tadShapeInfo, LongPointer tadOffsets) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execTransformFloat(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execTransformSame(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execTransformStrict(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execTransformBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execTransformAny(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execScalarTad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, OpaqueDataBuffer scalars, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer tadShapeInfo, LongPointer tadOffsets, LongPointer tadShapeInfoZ, LongPointer tadOffsetsZ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execScalarBoolTad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, OpaqueDataBuffer scalars, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer tadShapeInfo, LongPointer tadOffsets, LongPointer tadShapeInfoZ, LongPointer tadOffsetsZ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void specialConcat(PointerPointer extraPointers, int dimension, int numArrays, PointerPointer data, PointerPointer inputShapeInfo, Pointer results, LongPointer resultShapeInfo, PointerPointer tadPointers, PointerPointer tadOffsets) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int ompGetMaxThreads() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int ompGetNumThreads() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOmpNumThreads(int threads) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOmpMinThreads(int threads) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void initializeDevicesAndFunctions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void initializeFunctions(PointerPointer functions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer mallocHost(long memorySize, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer mallocDevice(long memorySize, int ptrToDeviceId, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int freeHost(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int freeDevice(Pointer pointer, int deviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer createContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer createStream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer createEvent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int registerEvent(Pointer event, Pointer stream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int destroyEvent(Pointer event) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int setDevice(int ptrToDeviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDevice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int streamSynchronize(Pointer stream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int eventSynchronize(Pointer event) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getDeviceFreeMemory(int ptrToDeviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getDeviceFreeMemoryDefault() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getDeviceTotalMemory(int ptrToDeviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDeviceMajor(int ptrToDeviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getDeviceMinor(int ptrToDeviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDeviceName(int ptrToDeviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int memcpySync(Pointer dst, Pointer src, long size, int flags, Pointer reserved) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int memcpyAsync(Pointer dst, Pointer src, long size, int flags, Pointer reserved) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int memcpyConstantAsync(long dst, Pointer src, long size, int flags, Pointer reserved) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int memsetSync(Pointer dst, int value, long size, int flags, Pointer reserved) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int memsetAsync(Pointer dst, int value, long size, int flags, Pointer reserved) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getConstantSpace() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAvailableDevices() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void enableDebugMode(boolean reallyEnable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void enableVerboseMode(boolean reallyEnable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGridLimit(int gridSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueTadPack tadOnlyShapeInfo(LongPointer shapeInfo, IntPointer dimension, int dimensionLength) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer getPrimaryShapeInfo(OpaqueTadPack pack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer getPrimaryOffsets(OpaqueTadPack pack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer getSpecialShapeInfo(OpaqueTadPack pack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer getSpecialOffsets(OpaqueTadPack pack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getNumberOfTads(OpaqueTadPack pack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getShapeInfoLength(OpaqueTadPack pack) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteTadPack(OpaqueTadPack pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void pullRows(PointerPointer extraPointers, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, long n, LongPointer indexes, LongPointer tadShapeInfo, LongPointer tadOffsets, LongPointer zTadShapeInfo, LongPointer zTadOffsets) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void average(PointerPointer extraPointers, PointerPointer x, LongPointer xShapeInfo, PointerPointer dx, LongPointer dxShapeInfo, Pointer z, LongPointer zShapeInfo, Pointer dz, LongPointer dzShapeInfo, int n, long length, boolean propagate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accumulate(PointerPointer extraPointers, PointerPointer x, LongPointer xShapeInfo, PointerPointer dx, LongPointer dxShapeInfo, Pointer z, LongPointer zShapeInfo, Pointer dz, LongPointer dzShapeInfo, int n, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void enableP2P(boolean reallyEnable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkP2P() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isP2PAvailable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shuffle(PointerPointer extraPointers, PointerPointer x, PointerPointer xShapeInfo, PointerPointer dx, PointerPointer dxShapeInfo, PointerPointer z, PointerPointer zShapeInfo, PointerPointer dz, PointerPointer dzShapeInfo, int N, IntPointer shuffleMap, PointerPointer tadShapeInfo, PointerPointer tadOffsets) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void convertTypes(PointerPointer extras, int srcType, Pointer x, long N, int dstType, Pointer z) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isExperimentalEnabled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execAggregate(PointerPointer extras, int opNum, PointerPointer arguments, int numArguments, PointerPointer shapes, int numShapes, IntPointer indexArguments, int numIndexArguments, PointerPointer intArrays, int numIntArrays, Pointer realArguments, int numRealArguments, int dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execAggregateBatch(PointerPointer extras, int numAggregates, int opNum, int maxArgs, int maxShapes, int maxIntArrays, int maxIntArraySize, int maxIdx, int maxReals, Pointer ptrToArguments, int dataType) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execRandom(PointerPointer extraPointers, int opNum, Pointer state, OpaqueDataBuffer z, LongPointer zShapeBuffer, LongPointer dzShapeBuffer, Pointer extraArguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execRandom3(PointerPointer extraPointers, int opNum, Pointer state, OpaqueDataBuffer x, LongPointer xShapeBuffer, LongPointer dxShapeBuffer, OpaqueDataBuffer y, LongPointer yShapeBuffer, LongPointer dyShapeBuffer, OpaqueDataBuffer z, LongPointer zShapeBuffer, LongPointer dzShapeBuffer, Pointer extraArguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execRandom2(PointerPointer extraPointers, int opNum, Pointer state, OpaqueDataBuffer x, LongPointer xShapeBuffer, LongPointer dxShapeBuffer, OpaqueDataBuffer z, LongPointer zShapeBuffer, LongPointer dzShapeBuffer, Pointer extraArguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer initRandom(PointerPointer extraPointers, long seed, long numberOfElements, Pointer pointerToBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void refreshBuffer(PointerPointer extraPointers, long seed, Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reSeedBuffer(PointerPointer extraPointers, long seed, Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void destroyRandom(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer numpyFromNd4j(Pointer data, Pointer shapeBuffer, long wordSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int elementSizeForNpyArrayHeader(Pointer npyArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer dataPointForNumpyStruct(Pointer npyArrayStruct) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer numpyHeaderForNd4j(Pointer data, Pointer shapeBuffer, long wordSize, LongPointer length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer loadNpyFromHeader(Pointer data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer dataPointForNumpyHeader(Pointer npyArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer shapeBufferForNumpyHeader(Pointer npyArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer dataPointForNumpy(Pointer npyArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer shapeBufferForNumpy(Pointer npyArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void releaseNumpy(Pointer npyArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer numpyFromFile(BytePointer path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lengthForShapeBufferPointer(Pointer buffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int elementSizeForNpyArray(Pointer npyArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer pointerForAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer mapFromNpzFile(BytePointer path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNumNpyArraysInMap(Pointer map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getNpyArrayNameFromMap(Pointer map, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getNpyArrayFromMap(Pointer map, int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getNpyArrayData(Pointer npArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer getNpyArrayShape(Pointer npArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNpyArrayRank(Pointer npArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getNpyArrayOrder(Pointer npArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNpyArrayElemSize(Pointer npArray) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void tear(PointerPointer extras, OpaqueDataBuffer tensor, LongPointer xShapeInfo, LongPointer dxShapeInfo, PointerPointer targets, LongPointer zShapeInfo, LongPointer tadShapeInfo, LongPointer tadOffsets) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(PointerPointer extraPointers, Pointer x, LongPointer xShapeInfo, Pointer dx, LongPointer dxShapeInfo, boolean descending) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sortTad(PointerPointer extraPointers, Pointer x, LongPointer xShapeInfo, Pointer dx, LongPointer dxShapeInfo, IntPointer dimension, int dimensionLength, LongPointer tadShapeInfo, LongPointer tadOffsets, boolean descending) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sortCooIndices(PointerPointer extraPointers, LongPointer indices, Pointer values, long length, int rank) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer mmapFile(PointerPointer extraPointers, String fileName, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void munmapFile(PointerPointer extraPointers, LongPointer ptrMap, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueResultWrapper executeFlatGraph(PointerPointer extraPointers, Pointer flatBufferPointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getResultWrapperSize(OpaqueResultWrapper ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getResultWrapperPointer(OpaqueResultWrapper ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAllCustomOps() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAllOperations() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int execCustomOp2(PointerPointer extraPointers, long opHashCode, Pointer context) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int execCustomOp(PointerPointer extraPointers, long opHashCode, PointerPointer inputBuffers, PointerPointer inputShapes, int numInput, PointerPointer outputBuffers, PointerPointer outputShapes, int numOutputs, DoublePointer tArgs, int numTArgs, LongPointer iArgs, int numIArgs, BooleanPointer bArgs, int numBArgs, boolean isInplace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueShapeList calculateOutputShapes(PointerPointer extraPointers, long hash, PointerPointer inputShapes, int numInputShapes, DoublePointer tArgs, int numTArgs, LongPointer iArgs, int numIArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueShapeList calculateOutputShapes2(PointerPointer extraPointers, long hash, PointerPointer inputBunffers, PointerPointer inputShapes, int numInputShapes, DoublePointer tArgs, int numTArgs, LongPointer iArgs, int numIArgs, BooleanPointer bArgs, int numBArgs, IntPointer dArgs, int numDArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getShapeListSize(OpaqueShapeList list) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer getShape(OpaqueShapeList list, long i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int registerGraph(PointerPointer extraPointers, long graphId, Pointer flatBufferPointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueVariablesSet executeStoredGraph(PointerPointer extraPointers, long graphId, PointerPointer inputBuffers, PointerPointer inputShapes, IntPointer inputIndices, int numInputs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getVariablesSetSize(OpaqueVariablesSet set) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getVariablesSetStatus(OpaqueVariablesSet set) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueVariable getVariable(OpaqueVariablesSet set, long i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getVariableId(OpaqueVariable variable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getVariableIndex(OpaqueVariable variable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getVariableName(OpaqueVariable variable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongPointer getVariableShape(OpaqueVariable variable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getVariableBuffer(OpaqueVariable variable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteResultWrapper(Pointer ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteShapeList(Pointer ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int unregisterGraph(PointerPointer extraPointers, long graphId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteIntArray(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteLongArray(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deletePointerArray(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteNPArrayStruct(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteNPArrayMap(Pointer pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteVariablesSet(OpaqueVariablesSet pointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getGraphState(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteGraphState(Pointer state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int estimateThreshold(PointerPointer extraPointers, Pointer x, LongPointer xShapeInfo, int N, float threshold) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int execCustomOpWithScope(PointerPointer extraPointers, Pointer state, long opHash, long[] scopes, int numScopes, PointerPointer inputBuffers, PointerPointer inputShapes, int numInputs, PointerPointer outputBuffers, PointerPointer outputShapes, int numOutputs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void scatterUpdate(PointerPointer extraPointers, int opCode, int numOfUpdates, Pointer hX, LongPointer hXShapeInfo, LongPointer hxOffsets, Pointer dX, LongPointer dXShapeInfo, LongPointer dxOffsets, Pointer hY, LongPointer hYShapeInfo, LongPointer hyOffsets, Pointer dY, LongPointer dYShapeInfo, LongPointer dyOffsets, Pointer hIndices, LongPointer hIndicesShapeInfo, Pointer dIndices, LongPointer dIndicesShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer createUtf8String(PointerPointer extraPointers, String string, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getUtf8StringLength(PointerPointer extraPointers, Pointer ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BytePointer getUtf8StringBuffer(PointerPointer extraPointers, Pointer ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUtf8String(PointerPointer extraPointers, Pointer ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void inspectArray(PointerPointer extraPointers, Pointer buffer, LongPointer shapeInfo, Pointer specialBuffer, LongPointer specialShapeInfo, Pointer debugInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void tryPointer(Pointer extras, Pointer buffer, int numBytesToRead) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int dataTypeFromNpyHeader(Pointer numpyHeader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueConstantDataBuffer shapeBuffer(int rank, LongPointer shape, LongPointer strides, int dtype, char order, long ews, boolean empty) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueConstantDataBuffer constantBufferDouble(int dtype, DoublePointer data, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueConstantDataBuffer constantBufferLong(int dtype, LongPointer data, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getConstantDataBufferPrimary(OpaqueConstantDataBuffer dbf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer getConstantDataBufferSpecial(OpaqueConstantDataBuffer dbf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getConstantDataBufferLength(OpaqueConstantDataBuffer dbf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getConstantDataBufferSizeOf(OpaqueConstantDataBuffer dbf) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteShapeBuffer(OpaqueConstantDataBuffer state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueContext createGraphContext(int nodeId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueRandomGenerator getGraphContextRandomGenerator(OpaqueContext ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void markGraphContextInplace(OpaqueContext ptr, boolean reallyInplace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextCudaContext(OpaqueContext ptr, Pointer stream, Pointer reductionPointer, Pointer allocationPointer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextInputArray(OpaqueContext ptr, int index, Pointer buffer, Pointer shapeInfo, Pointer specialBuffer, Pointer specialShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextOutputArray(OpaqueContext ptr, int index, Pointer buffer, Pointer shapeInfo, Pointer specialBuffer, Pointer specialShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextInputBuffer(OpaqueContext ptr, int index, OpaqueDataBuffer databuffer, Pointer shapeInfo, Pointer specialShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextOutputBuffer(OpaqueContext ptr, int index, OpaqueDataBuffer databuffer, Pointer shapeInfo, Pointer specialShapeInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextTArguments(OpaqueContext ptr, DoublePointer arguments, int numberOfArguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextIArguments(OpaqueContext ptr, LongPointer arguments, int numberOfArguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextDArguments(OpaqueContext ptr, IntPointer arguments, int numberOfArguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setGraphContextBArguments(OpaqueContext ptr, BooleanPointer arguments, int numberOfArguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ctxAllowHelpers(OpaqueContext ptr, boolean reallyAllow) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ctxSetExecutionMode(OpaqueContext ptr, int execMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ctxShapeFunctionOverride(OpaqueContext ptr, boolean reallyOverride) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ctxPurge(OpaqueContext ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteGraphContext(OpaqueContext ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueRandomGenerator createRandomGenerator(long rootSeed, long nodeSeed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getRandomGeneratorRootState(OpaqueRandomGenerator ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getRandomGeneratorNodeState(OpaqueRandomGenerator ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRandomGeneratorStates(OpaqueRandomGenerator ptr, long rootSeed, long nodeSeed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getRandomGeneratorRelativeInt(OpaqueRandomGenerator ptr, long index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getRandomGeneratorRelativeLong(OpaqueRandomGenerator ptr, long index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteRandomGenerator(OpaqueRandomGenerator ptr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String runLightBenchmarkSuit(boolean printOut) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String runFullBenchmarkSuit(boolean printOut) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getCachedMemory(int deviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueLaunchContext defaultLaunchContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer lcScalarPointer(OpaqueLaunchContext lc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer lcReductionPointer(OpaqueLaunchContext lc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer lcAllocationPointer(OpaqueLaunchContext lc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer lcExecutionStream(OpaqueLaunchContext lc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer lcCopyStream(OpaqueLaunchContext lc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer lcBlasHandle(OpaqueLaunchContext lc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer lcSolverHandle(OpaqueLaunchContext lc) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastErrorCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String lastErrorMessage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBlasVersionMatches(int major, int minor, int build) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int binaryLevel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int optimalLevel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMinimalRequirementsMet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOptimalRequirementsMet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueDataBuffer allocateDataBuffer(long elements, int dataType, boolean allocateBoth) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueDataBuffer dbAllocateDataBuffer(long elements, int dataType, boolean allocateBoth) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueDataBuffer dbCreateExternalDataBuffer(long elements, int dataType, Pointer primary, Pointer special) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OpaqueDataBuffer dbCreateView(OpaqueDataBuffer dataBuffer, long length, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer dbPrimaryBuffer(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pointer dbSpecialBuffer(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbExpandBuffer(OpaqueDataBuffer dataBuffer, long elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbAllocatePrimaryBuffer(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbAllocateSpecialBuffer(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbSetPrimaryBuffer(OpaqueDataBuffer dataBuffer, Pointer primaryBuffer, long numBytes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbSetSpecialBuffer(OpaqueDataBuffer dataBuffer, Pointer specialBuffer, long numBytes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbSyncToSpecial(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbSyncToPrimary(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbTickHostRead(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbTickHostWrite(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbTickDeviceRead(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbTickDeviceWrite(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteDataBuffer(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbClose(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int dbLocality(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int dbDeviceId(OpaqueDataBuffer dataBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbSetDeviceId(OpaqueDataBuffer dataBuffer, int deviceId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dbExpand(OpaqueDataBuffer dataBuffer, long newLength) {
        throw new UnsupportedOperationException();
    }
}
