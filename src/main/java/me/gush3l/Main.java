package me.gush3l;

import java.io.File;

public class Main {

    private static File wordDictionary;

    public static void main(String[] args) {
        System.out.println("Initializing...");
        MainWindow.createJFrame();
    }

    public static void setWordDictionaryFile(File wordDictionaryFile) {
        wordDictionary = wordDictionaryFile;
    }

    public static File getWordDictionary() {
        return wordDictionary;
    }

}