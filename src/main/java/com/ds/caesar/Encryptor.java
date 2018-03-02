package com.ds.caesar;

import java.io.IOException;

public interface Encryptor {
    void encrypt(String inputFileName, String outputFileName, boolean enableStatistics) throws IOException;
}
