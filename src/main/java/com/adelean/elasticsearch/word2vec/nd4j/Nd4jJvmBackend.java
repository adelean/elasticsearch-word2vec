package com.adelean.elasticsearch.word2vec.nd4j;

import org.nd4j.common.io.ClassPathResource;
import org.nd4j.common.io.Resource;
import org.nd4j.linalg.exception.ND4JIllegalStateException;
import org.nd4j.linalg.factory.Environment;
import org.nd4j.linalg.factory.Nd4jBackend;

public final class Nd4jJvmBackend extends Nd4jBackend {
    private static final ND4JIllegalStateException FORCE_LOAD = new ND4JIllegalStateException();
    private final static String PROPS = "/nd4j-jvm.properties";

    @Override
    public boolean allowsOrder() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPriority() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public boolean canRun() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Resource getConfigurationResource() {
        return new ClassPathResource(PROPS, Nd4jJvmBackend.class.getClassLoader());
    }

    @Override
    public Class getNDArrayClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Environment getEnvironment() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void logBackendInit() {
        throw new UnsupportedOperationException();
    }
}
