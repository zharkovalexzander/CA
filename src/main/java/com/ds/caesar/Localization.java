package com.ds.caesar;

import java.io.IOException;

public enum Localization {
    EN("abcdefghijklmnopqrstuvwxyz".toCharArray()),
    RU("абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray());

    private char[] alphabet;

    Localization(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public boolean isInRange(int character) {
        char localCharacter = FileProcessor.toLowerCase(character);
        for(char ch : this.alphabet) {
            if(ch == localCharacter) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(int character) {
        char localCharacter = FileProcessor.toLowerCase(character);
        for(int i = 0; i < alphabet.length; ++i) {
            if(alphabet[i] == localCharacter) {
                return i;
            }
        }
        return -1;
    }

    public char at(int index) {
        return this.alphabet[index];
    }

    public int alphabetSize() {
        return this.alphabet.length;
    }

    public static Localization defineLocalisation(String loc) throws IOException {
        switch (loc.toLowerCase()) {
            case "en" :
                return Localization.EN;
            case "ru" :
                return Localization.RU;
            default:
                throw new IOException("Cannot define localization");
        }
    }
}
