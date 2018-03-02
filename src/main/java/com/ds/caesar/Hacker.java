package com.ds.caesar;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hacker extends FileProcessor {

    public void hack(String inputFileName, String original, boolean enableStatistics)
            throws IOException {
        Path path = Paths.get("D:/tmp.txt");
        if(!Files.exists(path)) {
            Files.createFile(path);
        }
        int count = 1;
        while (!FileUtils.contentEqualsIgnoreEOL(new File("D:/" + original + ".txt"),
                new File("D:/tmp.txt"),
                "utf-8")) {
            read(inputFileName, count++);
        }
        System.out.println("It took " + (count - 1) + " shifts to find suitable combination");

    }

    private void read(String inputFileName, int raiseBy)
            throws IOException {
        Path path = Paths.get("D:/tmp.txt");
        try (InputStream in = new FileInputStream(new File("D:/" + inputFileName + ".txt"));
             Reader reader = new InputStreamReader(in);
             BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            int c;
            StringBuilder local = new StringBuilder();
            local.append((char) reader.read())
                    .append((char) reader.read());
            this.localization = Localization.defineLocalisation(local.toString());
            writer.write(local.toString());
            while ((c = reader.read()) != -1) {
                if(this.localization.isInRange(c)) {
                    int index = this.localization.indexOf(c) - raiseBy;
                    while (index < 0) {
                        index += this.localization.alphabetSize();
                    }
                    if(c == FileProcessor.toUpperCase(c)) {
                        writer.write(FileProcessor.toUpperCase(this.localization.at(index)));
                    } else {
                        writer.write(this.localization.at(index));
                    }
                } else {
                    writer.write(c);
                }
            }
        } catch (Exception exe) {
            exe.printStackTrace();
        }
    }
}
