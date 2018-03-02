package com.ds.caesar;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("D:/abc.txt");
        try {
            /*EncryptorImpl encryptor = new EncryptorImpl();
            encryptor.encrypt("abc", "encrypted", true);
            DecryptorImpl decryptor = new DecryptorImpl();
            decryptor.decrypt("encrypted", "decrypted", true);*/
            Hacker hacker = new Hacker();
            hacker.hack("encrypted", "abc", true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
