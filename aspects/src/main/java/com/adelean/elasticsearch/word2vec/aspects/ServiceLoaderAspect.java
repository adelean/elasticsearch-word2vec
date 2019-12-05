package com.adelean.elasticsearch.word2vec.aspects;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.ServiceLoader;

@Aspect
public class ServiceLoaderAspect {
    private static final ClassLoader PLUGIN_CLASSLOADER = ServiceLoaderAspect.class.getClassLoader();

    @Around("call(ServiceLoader ServiceLoader.load(Class))"
            + "&& args(service)"
            + "&& ( within(org.nd4j.linalg.compression.BasicNDArrayCompressor) "
            + "     || within(org.nd4j.linalg.factory.Nd4jBackend) )")
    public ServiceLoader aroundCallLoadWithSingleArgument(Class service) {
        return ServiceLoader.load(service, PLUGIN_CLASSLOADER);
    }
}
