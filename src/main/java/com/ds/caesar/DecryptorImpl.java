package com.ds.caesar;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DecryptorImpl extends FileProcessor implements Decryptor {

    public DecryptorImpl() {
        super();
    }

    @Override
    public void decrypt(String inputFileName, String outputFileName, boolean enableStatistics)
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
            System.out.println("Reading encrypted file:");
            while ((c = reader.read()) != -1) {
                if(this.localization.isInRange(c)) {
                    if (enableStatistics) {
                        statistics.put(FileProcessor.toLowerCase(c));
                    }
                    writer.write(decryptChar(c));
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

    protected int decryptChar(int character) {
        int position = this.localization.indexOf(character) - 3;
        while (position < 0) {
            position += this.localization.alphabetSize();
        }
        int newChar = this.localization.at(position);
        if(character == FileProcessor.toUpperCase(character)) {
            return FileProcessor.toUpperCase(newChar);
        }
        return newChar;
    }
}
