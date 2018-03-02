package com.ds.caesar;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptorImpl extends FileProcessor implements Encryptor {

    public EncryptorImpl() {
        super();
    }

    @Override
    public void encrypt(String inputFileName, String outputFileName, boolean enableStatistics)
            throws IOException {
        Path path = Paths.get("D:/" + outputFileName + ".txt");
        Statistics statistics = new Statistics();
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        try (InputStream in = new FileInputStream(new File("D:/" + inputFileName + ".txt"));
             Reader reader = new InputStreamReader(in);
             BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            int c;
            StringBuilder local = new StringBuilder();
            local.append((char) reader.read())
                .append((char) reader.read());
            System.out.println(local.toString());
            this.localization = Localization.defineLocalisation(local.toString());
            writer.write(local.toString());
            System.out.println("Reading text file:");
            while ((c = reader.read()) != -1) {
                if (this.localization.isInRange(c)) {
                    if (enableStatistics) {
                        statistics.put(FileProcessor.toLowerCase(c));
                    }
                    writer.write(encryptChar(c));
                } else {
                    writer.write(c);
                }
            }
            if (enableStatistics) {
                System.out.println(statistics);
            }
        } catch (Exception exe) {
            exe.printStackTrace();
        }
    }

    private int encryptChar(int character) {
        int position = this.localization.indexOf(character) + 3;
        while (position >= this.localization.alphabetSize()) {
            position -= this.localization.alphabetSize();
        }
        int newChar = this.localization.at(position);
        if(character == FileProcessor.toUpperCase(character)) {
            return FileProcessor.toUpperCase(newChar);
        }
        return newChar;
    }
}
