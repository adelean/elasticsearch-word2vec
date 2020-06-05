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

    }

    @Override
    public void setTADThreshold(int value) {

    }

    @Override
    public void execIndexReduceScalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dXShapeInfo, Pointer extraParams, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dZShapeInfo) {

    }

    @Override
    public void execIndexReduce(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dXShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dResultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {

    }

    @Override
    public void execBroadcast(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {

    }

    @Override
    public void execBroadcastBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {

    }

    @Override
    public void execPairwiseTransform(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execPairwiseTransformBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execReduceFloat(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {

    }

    @Override
    public void execReduceSame(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {

    }

    @Override
    public void execReduceBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {

    }

    @Override
    public void execReduceLong(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {

    }

    @Override
    public void execReduceFloat2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {

    }

    @Override
    public void execReduceSame2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {

    }

    @Override
    public void execReduceBool2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {

    }

    @Override
    public void execReduceLong2(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape) {

    }

    @Override
    public void execReduce3(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo) {

    }

    @Override
    public void execReduce3Scalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo) {

    }

    @Override
    public void execReduce3Tad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dresultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer tadOnlyShapeInfo, LongPointer tadOffsets, LongPointer yTadOnlyShapeInfo, LongPointer yTadOffsets) {

    }

    @Override
    public void execReduce3All(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParamsVals, OpaqueDataBuffer y, LongPointer yShapeInfo, LongPointer dyShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dresultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer xTadShape, LongPointer xOffsets, LongPointer yTadShape, LongPointer yOffsets) {

    }

    @Override
    public void execScalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer scalar, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execScalarBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, OpaqueDataBuffer scalar, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execSummaryStatsScalar(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, boolean biasCorrected) {

    }

    @Override
    public void execSummaryStats(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, boolean biasCorrected) {

    }

    @Override
    public void execSummaryStatsTad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, Pointer extraParams, OpaqueDataBuffer result, LongPointer resultShapeInfoBuffer, LongPointer dresultShapeInfoBuffer, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, boolean biasCorrected, LongPointer tadShapeInfo, LongPointer tadOffsets) {

    }

    @Override
    public void execTransformFloat(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execTransformSame(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execTransformStrict(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execTransformBool(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execTransformAny(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer result, LongPointer resultShapeInfo, LongPointer dresultShapeInfo, Pointer extraParams) {

    }

    @Override
    public void execScalarTad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, OpaqueDataBuffer scalars, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer tadShapeInfo, LongPointer tadOffsets, LongPointer tadShapeInfoZ, LongPointer tadOffsetsZ) {

    }

    @Override
    public void execScalarBoolTad(PointerPointer extraPointers, int opNum, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, OpaqueDataBuffer scalars, LongPointer scalarShapeInfo, LongPointer dscalarShapeInfo, Pointer extraParams, OpaqueDataBuffer hDimension, LongPointer hDimensionShape, LongPointer dDimensionShape, LongPointer tadShapeInfo, LongPointer tadOffsets, LongPointer tadShapeInfoZ, LongPointer tadOffsetsZ) {

    }

    @Override
    public void specialConcat(PointerPointer extraPointers, int dimension, int numArrays, PointerPointer data, PointerPointer inputShapeInfo, Pointer results, LongPointer resultShapeInfo, PointerPointer tadPointers, PointerPointer tadOffsets) {

    }

    @Override
    public int ompGetMaxThreads() {
        return 0;
    }

    @Override
    public int ompGetNumThreads() {
        return 0;
    }

    @Override
    public void setOmpNumThreads(int threads) {

    }

    @Override
    public void setOmpMinThreads(int threads) {

    }

    @Override
    public void initializeDevicesAndFunctions() {

    }

    @Override
    public void initializeFunctions(PointerPointer functions) {

    }

    @Override
    public Pointer mallocHost(long memorySize, int flags) {
        return null;
    }

    @Override
    public Pointer mallocDevice(long memorySize, int ptrToDeviceId, int flags) {
        return null;
    }

    @Override
    public int freeHost(Pointer pointer) {
        return 0;
    }

    @Override
    public int freeDevice(Pointer pointer, int deviceId) {
        return 0;
    }

    @Override
    public Pointer createContext() {
        return null;
    }

    @Override
    public Pointer createStream() {
        return null;
    }

    @Override
    public Pointer createEvent() {
        return null;
    }

    @Override
    public int registerEvent(Pointer event, Pointer stream) {
        return 0;
    }

    @Override
    public int destroyEvent(Pointer event) {
        return 0;
    }

    @Override
    public int setDevice(int ptrToDeviceId) {
        return 0;
    }

    @Override
    public int getDevice() {
        return 0;
    }

    @Override
    public int streamSynchronize(Pointer stream) {
        return 0;
    }

    @Override
    public int eventSynchronize(Pointer event) {
        return 0;
    }

    @Override
    public long getDeviceFreeMemory(int ptrToDeviceId) {
        return 0;
    }

    @Override
    public long getDeviceFreeMemoryDefault() {
        return 0;
    }

    @Override
    public long getDeviceTotalMemory(int ptrToDeviceId) {
        return 0;
    }

    @Override
    public int getDeviceMajor(int ptrToDeviceId) {
        return 0;
    }

    @Override
    public int getDeviceMinor(int ptrToDeviceId) {
        return 0;
    }

    @Override
    public String getDeviceName(int ptrToDeviceId) {
        return null;
    }

    @Override
    public int memcpySync(Pointer dst, Pointer src, long size, int flags, Pointer reserved) {
        return 0;
    }

    @Override
    public int memcpyAsync(Pointer dst, Pointer src, long size, int flags, Pointer reserved) {
        return 0;
    }

    @Override
    public int memcpyConstantAsync(long dst, Pointer src, long size, int flags, Pointer reserved) {
        return 0;
    }

    @Override
    public int memsetSync(Pointer dst, int value, long size, int flags, Pointer reserved) {
        return 0;
    }

    @Override
    public int memsetAsync(Pointer dst, int value, long size, int flags, Pointer reserved) {
        return 0;
    }

    @Override
    public Pointer getConstantSpace() {
        return null;
    }

    @Override
    public int getAvailableDevices() {
        return 0;
    }

    @Override
    public void enableDebugMode(boolean reallyEnable) {

    }

    @Override
    public void enableVerboseMode(boolean reallyEnable) {

    }

    @Override
    public void setGridLimit(int gridSize) {

    }

    @Override
    public OpaqueTadPack tadOnlyShapeInfo(LongPointer shapeInfo, IntPointer dimension, int dimensionLength) {
        return null;
    }

    @Override
    public LongPointer getPrimaryShapeInfo(OpaqueTadPack pack) {
        return null;
    }

    @Override
    public LongPointer getPrimaryOffsets(OpaqueTadPack pack) {
        return null;
    }

    @Override
    public LongPointer getSpecialShapeInfo(OpaqueTadPack pack) {
        return null;
    }

    @Override
    public LongPointer getSpecialOffsets(OpaqueTadPack pack) {
        return null;
    }

    @Override
    public long getNumberOfTads(OpaqueTadPack pack) {
        return 0;
    }

    @Override
    public int getShapeInfoLength(OpaqueTadPack pack) {
        return 0;
    }

    @Override
    public void deleteTadPack(OpaqueTadPack pointer) {

    }

    @Override
    public void pullRows(PointerPointer extraPointers, OpaqueDataBuffer x, LongPointer xShapeInfo, LongPointer dxShapeInfo, OpaqueDataBuffer z, LongPointer zShapeInfo, LongPointer dzShapeInfo, long n, LongPointer indexes, LongPointer tadShapeInfo, LongPointer tadOffsets, LongPointer zTadShapeInfo, LongPointer zTadOffsets) {

    }

    @Override
    public void average(PointerPointer extraPointers, PointerPointer x, LongPointer xShapeInfo, PointerPointer dx, LongPointer dxShapeInfo, Pointer z, LongPointer zShapeInfo, Pointer dz, LongPointer dzShapeInfo, int n, long length, boolean propagate) {

    }

    @Override
    public void accumulate(PointerPointer extraPointers, PointerPointer x, LongPointer xShapeInfo, PointerPointer dx, LongPointer dxShapeInfo, Pointer z, LongPointer zShapeInfo, Pointer dz, LongPointer dzShapeInfo, int n, long length) {

    }

    @Override
    public void enableP2P(boolean reallyEnable) {

    }

    @Override
    public void checkP2P() {

    }

    @Override
    public boolean isP2PAvailable() {
        return false;
    }

    @Override
    public void shuffle(PointerPointer extraPointers, PointerPointer x, PointerPointer xShapeInfo, PointerPointer dx, PointerPointer dxShapeInfo, PointerPointer z, PointerPointer zShapeInfo, PointerPointer dz, PointerPointer dzShapeInfo, int N, IntPointer shuffleMap, PointerPointer tadShapeInfo, PointerPointer tadOffsets) {

    }

    @Override
    public void convertTypes(PointerPointer extras, int srcType, Pointer x, long N, int dstType, Pointer z) {

    }

    @Override
    public boolean isExperimentalEnabled() {
        return false;
    }

    @Override
    public void execAggregate(PointerPointer extras, int opNum, PointerPointer arguments, int numArguments, PointerPointer shapes, int numShapes, IntPointer indexArguments, int numIndexArguments, PointerPointer intArrays, int numIntArrays, Pointer realArguments, int numRealArguments, int dataType) {

    }

    @Override
    public void execAggregateBatch(PointerPointer extras, int numAggregates, int opNum, int maxArgs, int maxShapes, int maxIntArrays, int maxIntArraySize, int maxIdx, int maxReals, Pointer ptrToArguments, int dataType) {

    }

    @Override
    public void execRandom(PointerPointer extraPointers, int opNum, Pointer state, OpaqueDataBuffer z, LongPointer zShapeBuffer, LongPointer dzShapeBuffer, Pointer extraArguments) {

    }

    @Override
    public void execRandom3(PointerPointer extraPointers, int opNum, Pointer state, OpaqueDataBuffer x, LongPointer xShapeBuffer, LongPointer dxShapeBuffer, OpaqueDataBuffer y, LongPointer yShapeBuffer, LongPointer dyShapeBuffer, OpaqueDataBuffer z, LongPointer zShapeBuffer, LongPointer dzShapeBuffer, Pointer extraArguments) {

    }

    @Override
    public void execRandom2(PointerPointer extraPointers, int opNum, Pointer state, OpaqueDataBuffer x, LongPointer xShapeBuffer, LongPointer dxShapeBuffer, OpaqueDataBuffer z, LongPointer zShapeBuffer, LongPointer dzShapeBuffer, Pointer extraArguments) {

    }

    @Override
    public Pointer initRandom(PointerPointer extraPointers, long seed, long numberOfElements, Pointer pointerToBuffer) {
        return null;
    }

    @Override
    public void refreshBuffer(PointerPointer extraPointers, long seed, Pointer pointer) {

    }

    @Override
    public void reSeedBuffer(PointerPointer extraPointers, long seed, Pointer pointer) {

    }

    @Override
    public void destroyRandom(Pointer pointer) {

    }

    @Override
    public Pointer numpyFromNd4j(Pointer data, Pointer shapeBuffer, long wordSize) {
        return null;
    }

    @Override
    public int elementSizeForNpyArrayHeader(Pointer npyArray) {
        return 0;
    }

    @Override
    public Pointer dataPointForNumpyStruct(Pointer npyArrayStruct) {
        return null;
    }

    @Override
    public Pointer numpyHeaderForNd4j(Pointer data, Pointer shapeBuffer, long wordSize, LongPointer length) {
        return null;
    }

    @Override
    public Pointer loadNpyFromHeader(Pointer data) {
        return null;
    }

    @Override
    public Pointer dataPointForNumpyHeader(Pointer npyArray) {
        return null;
    }

    @Override
    public Pointer shapeBufferForNumpyHeader(Pointer npyArray) {
        return null;
    }

    @Override
    public Pointer dataPointForNumpy(Pointer npyArray) {
        return null;
    }

    @Override
    public Pointer shapeBufferForNumpy(Pointer npyArray) {
        return null;
    }

    @Override
    public void releaseNumpy(Pointer npyArray) {

    }

    @Override
    public Pointer numpyFromFile(BytePointer path) {
        return null;
    }

    @Override
    public int lengthForShapeBufferPointer(Pointer buffer) {
        return 0;
    }

    @Override
    public int elementSizeForNpyArray(Pointer npyArray) {
        return 0;
    }

    @Override
    public Pointer pointerForAddress(long address) {
        return null;
    }

    @Override
    public Pointer mapFromNpzFile(BytePointer path) {
        return null;
    }

    @Override
    public int getNumNpyArraysInMap(Pointer map) {
        return 0;
    }

    @Override
    public String getNpyArrayNameFromMap(Pointer map, int index) {
        return null;
    }

    @Override
    public Pointer getNpyArrayFromMap(Pointer map, int index) {
        return null;
    }

    @Override
    public Pointer getNpyArrayData(Pointer npArray) {
        return null;
    }

    @Override
    public LongPointer getNpyArrayShape(Pointer npArray) {
        return null;
    }

    @Override
    public int getNpyArrayRank(Pointer npArray) {
        return 0;
    }

    @Override
    public char getNpyArrayOrder(Pointer npArray) {
        return 0;
    }

    @Override
    public int getNpyArrayElemSize(Pointer npArray) {
        return 0;
    }

    @Override
    public void tear(PointerPointer extras, OpaqueDataBuffer tensor, LongPointer xShapeInfo, LongPointer dxShapeInfo, PointerPointer targets, LongPointer zShapeInfo, LongPointer tadShapeInfo, LongPointer tadOffsets) {

    }

    @Override
    public void sort(PointerPointer extraPointers, Pointer x, LongPointer xShapeInfo, Pointer dx, LongPointer dxShapeInfo, boolean descending) {

    }

    @Override
    public void sortTad(PointerPointer extraPointers, Pointer x, LongPointer xShapeInfo, Pointer dx, LongPointer dxShapeInfo, IntPointer dimension, int dimensionLength, LongPointer tadShapeInfo, LongPointer tadOffsets, boolean descending) {

    }

    @Override
    public void sortCooIndices(PointerPointer extraPointers, LongPointer indices, Pointer values, long length, int rank) {

    }

    @Override
    public LongPointer mmapFile(PointerPointer extraPointers, String fileName, long length) {
        return null;
    }

    @Override
    public void munmapFile(PointerPointer extraPointers, LongPointer ptrMap, long length) {

    }

    @Override
    public OpaqueResultWrapper executeFlatGraph(PointerPointer extraPointers, Pointer flatBufferPointer) {
        return null;
    }

    @Override
    public long getResultWrapperSize(OpaqueResultWrapper ptr) {
        return 0;
    }

    @Override
    public Pointer getResultWrapperPointer(OpaqueResultWrapper ptr) {
        return null;
    }

    @Override
    public String getAllCustomOps() {
        return null;
    }

    @Override
    public String getAllOperations() {
        return null;
    }

    @Override
    public int execCustomOp2(PointerPointer extraPointers, long opHashCode, Pointer context) {
        return 0;
    }

    @Override
    public int execCustomOp(PointerPointer extraPointers, long opHashCode, PointerPointer inputBuffers, PointerPointer inputShapes, int numInput, PointerPointer outputBuffers, PointerPointer outputShapes, int numOutputs, DoublePointer tArgs, int numTArgs, LongPointer iArgs, int numIArgs, BooleanPointer bArgs, int numBArgs, boolean isInplace) {
        return 0;
    }

    @Override
    public OpaqueShapeList calculateOutputShapes(PointerPointer extraPointers, long hash, PointerPointer inputShapes, int numInputShapes, DoublePointer tArgs, int numTArgs, LongPointer iArgs, int numIArgs) {
        return null;
    }

    @Override
    public OpaqueShapeList calculateOutputShapes2(PointerPointer extraPointers, long hash, PointerPointer inputBunffers, PointerPointer inputShapes, int numInputShapes, DoublePointer tArgs, int numTArgs, LongPointer iArgs, int numIArgs, BooleanPointer bArgs, int numBArgs, IntPointer dArgs, int numDArgs) {
        return null;
    }

    @Override
    public long getShapeListSize(OpaqueShapeList list) {
        return 0;
    }

    @Override
    public LongPointer getShape(OpaqueShapeList list, long i) {
        return null;
    }

    @Override
    public int registerGraph(PointerPointer extraPointers, long graphId, Pointer flatBufferPointer) {
        return 0;
    }

    @Override
    public OpaqueVariablesSet executeStoredGraph(PointerPointer extraPointers, long graphId, PointerPointer inputBuffers, PointerPointer inputShapes, IntPointer inputIndices, int numInputs) {
        return null;
    }

    @Override
    public long getVariablesSetSize(OpaqueVariablesSet set) {
        return 0;
    }

    @Override
    public int getVariablesSetStatus(OpaqueVariablesSet set) {
        return 0;
    }

    @Override
    public OpaqueVariable getVariable(OpaqueVariablesSet set, long i) {
        return null;
    }

    @Override
    public int getVariableId(OpaqueVariable variable) {
        return 0;
    }

    @Override
    public int getVariableIndex(OpaqueVariable variable) {
        return 0;
    }

    @Override
    public String getVariableName(OpaqueVariable variable) {
        return null;
    }

    @Override
    public LongPointer getVariableShape(OpaqueVariable variable) {
        return null;
    }

    @Override
    public Pointer getVariableBuffer(OpaqueVariable variable) {
        return null;
    }

    @Override
    public void deleteResultWrapper(Pointer ptr) {

    }

    @Override
    public void deleteShapeList(Pointer ptr) {

    }

    @Override
    public int unregisterGraph(PointerPointer extraPointers, long graphId) {
        return 0;
    }

    @Override
    public void deleteIntArray(Pointer pointer) {

    }

    @Override
    public void deleteLongArray(Pointer pointer) {

    }

    @Override
    public void deletePointerArray(Pointer pointer) {

    }

    @Override
    public void deleteNPArrayStruct(Pointer pointer) {

    }

    @Override
    public void deleteNPArrayMap(Pointer pointer) {

    }

    @Override
    public void deleteVariablesSet(OpaqueVariablesSet pointer) {

    }

    @Override
    public Pointer getGraphState(long id) {
        return null;
    }

    @Override
    public void deleteGraphState(Pointer state) {

    }

    @Override
    public int estimateThreshold(PointerPointer extraPointers, Pointer x, LongPointer xShapeInfo, int N, float threshold) {
        return 0;
    }

    @Override
    public int execCustomOpWithScope(PointerPointer extraPointers, Pointer state, long opHash, long[] scopes, int numScopes, PointerPointer inputBuffers, PointerPointer inputShapes, int numInputs, PointerPointer outputBuffers, PointerPointer outputShapes, int numOutputs) {
        return 0;
    }

    @Override
    public void scatterUpdate(PointerPointer extraPointers, int opCode, int numOfUpdates, Pointer hX, LongPointer hXShapeInfo, LongPointer hxOffsets, Pointer dX, LongPointer dXShapeInfo, LongPointer dxOffsets, Pointer hY, LongPointer hYShapeInfo, LongPointer hyOffsets, Pointer dY, LongPointer dYShapeInfo, LongPointer dyOffsets, Pointer hIndices, LongPointer hIndicesShapeInfo, Pointer dIndices, LongPointer dIndicesShapeInfo) {

    }

    @Override
    public Pointer createUtf8String(PointerPointer extraPointers, String string, int length) {
        return null;
    }

    @Override
    public long getUtf8StringLength(PointerPointer extraPointers, Pointer ptr) {
        return 0;
    }

    @Override
    public BytePointer getUtf8StringBuffer(PointerPointer extraPointers, Pointer ptr) {
        return null;
    }

    @Override
    public void deleteUtf8String(PointerPointer extraPointers, Pointer ptr) {

    }

    @Override
    public void inspectArray(PointerPointer extraPointers, Pointer buffer, LongPointer shapeInfo, Pointer specialBuffer, LongPointer specialShapeInfo, Pointer debugInfo) {

    }

    @Override
    public void tryPointer(Pointer extras, Pointer buffer, int numBytesToRead) {

    }

    @Override
    public int dataTypeFromNpyHeader(Pointer numpyHeader) {
        return 0;
    }

    @Override
    public OpaqueConstantDataBuffer shapeBuffer(int rank, LongPointer shape, LongPointer strides, int dtype, char order, long ews, boolean empty) {
        return null;
    }

    @Override
    public OpaqueConstantDataBuffer constantBufferDouble(int dtype, DoublePointer data, int length) {
        return null;
    }

    @Override
    public OpaqueConstantDataBuffer constantBufferLong(int dtype, LongPointer data, int length) {
        return null;
    }

    @Override
    public Pointer getConstantDataBufferPrimary(OpaqueConstantDataBuffer dbf) {
        return null;
    }

    @Override
    public Pointer getConstantDataBufferSpecial(OpaqueConstantDataBuffer dbf) {
        return null;
    }

    @Override
    public long getConstantDataBufferLength(OpaqueConstantDataBuffer dbf) {
        return 0;
    }

    @Override
    public long getConstantDataBufferSizeOf(OpaqueConstantDataBuffer dbf) {
        return 0;
    }

    @Override
    public void deleteShapeBuffer(OpaqueConstantDataBuffer state) {

    }

    @Override
    public OpaqueContext createGraphContext(int nodeId) {
        return null;
    }

    @Override
    public OpaqueRandomGenerator getGraphContextRandomGenerator(OpaqueContext ptr) {
        return null;
    }

    @Override
    public void markGraphContextInplace(OpaqueContext ptr, boolean reallyInplace) {

    }

    @Override
    public void setGraphContextCudaContext(OpaqueContext ptr, Pointer stream, Pointer reductionPointer, Pointer allocationPointer) {

    }

    @Override
    public void setGraphContextInputArray(OpaqueContext ptr, int index, Pointer buffer, Pointer shapeInfo, Pointer specialBuffer, Pointer specialShapeInfo) {

    }

    @Override
    public void setGraphContextOutputArray(OpaqueContext ptr, int index, Pointer buffer, Pointer shapeInfo, Pointer specialBuffer, Pointer specialShapeInfo) {

    }

    @Override
    public void setGraphContextInputBuffer(OpaqueContext ptr, int index, OpaqueDataBuffer databuffer, Pointer shapeInfo, Pointer specialShapeInfo) {

    }

    @Override
    public void setGraphContextOutputBuffer(OpaqueContext ptr, int index, OpaqueDataBuffer databuffer, Pointer shapeInfo, Pointer specialShapeInfo) {

    }

    @Override
    public void setGraphContextTArguments(OpaqueContext ptr, DoublePointer arguments, int numberOfArguments) {

    }

    @Override
    public void setGraphContextIArguments(OpaqueContext ptr, LongPointer arguments, int numberOfArguments) {

    }

    @Override
    public void setGraphContextDArguments(OpaqueContext ptr, IntPointer arguments, int numberOfArguments) {

    }

    @Override
    public void setGraphContextBArguments(OpaqueContext ptr, BooleanPointer arguments, int numberOfArguments) {

    }

    @Override
    public void ctxAllowHelpers(OpaqueContext ptr, boolean reallyAllow) {

    }

    @Override
    public void ctxSetExecutionMode(OpaqueContext ptr, int execMode) {

    }

    @Override
    public void ctxShapeFunctionOverride(OpaqueContext ptr, boolean reallyOverride) {

    }

    @Override
    public void ctxPurge(OpaqueContext ptr) {

    }

    @Override
    public void deleteGraphContext(OpaqueContext ptr) {

    }

    @Override
    public OpaqueRandomGenerator createRandomGenerator(long rootSeed, long nodeSeed) {
        return null;
    }

    @Override
    public long getRandomGeneratorRootState(OpaqueRandomGenerator ptr) {
        return 0;
    }

    @Override
    public long getRandomGeneratorNodeState(OpaqueRandomGenerator ptr) {
        return 0;
    }

    @Override
    public void setRandomGeneratorStates(OpaqueRandomGenerator ptr, long rootSeed, long nodeSeed) {

    }

    @Override
    public int getRandomGeneratorRelativeInt(OpaqueRandomGenerator ptr, long index) {
        return 0;
    }

    @Override
    public long getRandomGeneratorRelativeLong(OpaqueRandomGenerator ptr, long index) {
        return 0;
    }

    @Override
    public void deleteRandomGenerator(OpaqueRandomGenerator ptr) {

    }

    @Override
    public String runLightBenchmarkSuit(boolean printOut) {
        return null;
    }

    @Override
    public String runFullBenchmarkSuit(boolean printOut) {
        return null;
    }

    @Override
    public long getCachedMemory(int deviceId) {
        return 0;
    }

    @Override
    public OpaqueLaunchContext defaultLaunchContext() {
        return null;
    }

    @Override
    public Pointer lcScalarPointer(OpaqueLaunchContext lc) {
        return null;
    }

    @Override
    public Pointer lcReductionPointer(OpaqueLaunchContext lc) {
        return null;
    }

    @Override
    public Pointer lcAllocationPointer(OpaqueLaunchContext lc) {
        return null;
    }

    @Override
    public Pointer lcExecutionStream(OpaqueLaunchContext lc) {
        return null;
    }

    @Override
    public Pointer lcCopyStream(OpaqueLaunchContext lc) {
        return null;
    }

    @Override
    public Pointer lcBlasHandle(OpaqueLaunchContext lc) {
        return null;
    }

    @Override
    public Pointer lcSolverHandle(OpaqueLaunchContext lc) {
        return null;
    }

    @Override
    public int lastErrorCode() {
        return 0;
    }

    @Override
    public String lastErrorMessage() {
        return null;
    }

    @Override
    public boolean isBlasVersionMatches(int major, int minor, int build) {
        return false;
    }

    @Override
    public int binaryLevel() {
        return 0;
    }

    @Override
    public int optimalLevel() {
        return 0;
    }

    @Override
    public boolean isMinimalRequirementsMet() {
        return false;
    }

    @Override
    public boolean isOptimalRequirementsMet() {
        return false;
    }

    @Override
    public OpaqueDataBuffer allocateDataBuffer(long elements, int dataType, boolean allocateBoth) {
        return null;
    }

    @Override
    public OpaqueDataBuffer dbAllocateDataBuffer(long elements, int dataType, boolean allocateBoth) {
        return null;
    }

    @Override
    public OpaqueDataBuffer dbCreateExternalDataBuffer(long elements, int dataType, Pointer primary, Pointer special) {
        return null;
    }

    @Override
    public OpaqueDataBuffer dbCreateView(OpaqueDataBuffer dataBuffer, long length, long offset) {
        return null;
    }

    @Override
    public Pointer dbPrimaryBuffer(OpaqueDataBuffer dataBuffer) {
        return null;
    }

    @Override
    public Pointer dbSpecialBuffer(OpaqueDataBuffer dataBuffer) {
        return null;
    }

    @Override
    public void dbExpandBuffer(OpaqueDataBuffer dataBuffer, long elements) {

    }

    @Override
    public void dbAllocatePrimaryBuffer(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbAllocateSpecialBuffer(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbSetPrimaryBuffer(OpaqueDataBuffer dataBuffer, Pointer primaryBuffer, long numBytes) {

    }

    @Override
    public void dbSetSpecialBuffer(OpaqueDataBuffer dataBuffer, Pointer specialBuffer, long numBytes) {

    }

    @Override
    public void dbSyncToSpecial(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbSyncToPrimary(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbTickHostRead(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbTickHostWrite(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbTickDeviceRead(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbTickDeviceWrite(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void deleteDataBuffer(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public void dbClose(OpaqueDataBuffer dataBuffer) {

    }

    @Override
    public int dbLocality(OpaqueDataBuffer dataBuffer) {
        return 0;
    }

    @Override
    public int dbDeviceId(OpaqueDataBuffer dataBuffer) {
        return 0;
    }

    @Override
    public void dbSetDeviceId(OpaqueDataBuffer dataBuffer, int deviceId) {

    }

    @Override
    public void dbExpand(OpaqueDataBuffer dataBuffer, long newLength) {

    }
}
