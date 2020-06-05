package io.hosuaby.nd4j.backend.jvm;

import java.util.Map;

import org.bytedeco.javacpp.Pointer;
import org.nd4j.linalg.api.memory.BasicMemoryManager;
import org.nd4j.linalg.api.memory.enums.MemoryKind;
import org.nd4j.linalg.api.ndarray.INDArray;

public final class JvmMemoryManager extends BasicMemoryManager {

    @Override
    public void release(Pointer pointer, MemoryKind kind) {

    }

    @Override
    public void memset(INDArray array) {

    }

    @Override
    public Map<Integer, Long> getBandwidthUse() {
        return null;
    }

    @Override
    public long allocatedMemory(Integer deviceId) {
        return 0;
    }
}
