package com.ds.caesar;

import java.io.IOException;

public interface Decryptor {
    void decrypt(String inputFileName, String outputFileName, boolean enableStatistics) throws IOException;
}
