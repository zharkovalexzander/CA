package com.ds.caesar;

public abstract class FileProcessor {
    protected Localization localization;

    public FileProcessor() {
        this.localization = null;
    }

    public static char toLowerCase(int character) {
        return String.valueOf((char) character)
                .toLowerCase()
                .charAt(0);
    }

    public static char toUpperCase(int character) {
        return String.valueOf((char) character)
                .toUpperCase()
                .charAt(0);
    }
}
