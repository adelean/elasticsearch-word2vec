package com.adelean.elasticsearch.word2vec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;

public class TestModel {
    public static void main(String[] args) throws FileNotFoundException, UnknownHostException {
        System.setProperty("org.bytedeco.javacpp.cachedir.nosubdir", "true");

        File modelFile = new File("/home/hosuaby/projects/doc2vec/model/model-tot.bin");
        FileInputStream fileInputStream = new FileInputStream(modelFile);
    }
}
