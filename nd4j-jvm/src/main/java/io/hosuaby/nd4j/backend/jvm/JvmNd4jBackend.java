package io.hosuaby.nd4j.backend.jvm;

import org.nd4j.common.io.ClassPathResource;
import org.nd4j.common.io.Resource;
import org.nd4j.linalg.exception.ND4JIllegalStateException;
import org.nd4j.linalg.factory.Environment;
import org.nd4j.linalg.factory.Nd4jBackend;

public final class JvmNd4jBackend extends Nd4jBackend {
    private static final ND4JIllegalStateException FORCE_LOAD = new ND4JIllegalStateException();
    private final static String PROPS = "/nd4j-jvm.properties";

    @Override
    public boolean allowsOrder() {
        return false;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public boolean canRun() {
        return true;
    }

    @Override
    public Resource getConfigurationResource() {
        return new ClassPathResource(PROPS, JvmNd4jBackend.class.getClassLoader());
    }

    @Override
    public Class getNDArrayClass() {
        return NDArray.class;
    }

    @Override
    public Environment getEnvironment() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void logBackendInit() {
        // No additional logging for JVM backend
    }
}
