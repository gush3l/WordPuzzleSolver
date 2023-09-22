package me.gush3l;

import java.io.*;
import java.util.*;

public class WordHandler {

    private static final List<String> wordList = new ArrayList<>();

    private static int maxWordLength;

    public static int getMaxWordLength() {
        return maxWordLength;
    }

    public static void createWordsList() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Main.getWordDictionary().getAbsolutePath()));
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cached the dictionary file and created the words list successfully!");
    }

    public static void getLongestWordLenght() {
        int max = 0;
        for (String word : wordList) {
            if (word.length() > max) max = word.length();
        }
        maxWordLength = max;
    }

    public static List<String> getMatchingWordsList(String wordPlaceholder, String lettersString) {
        List<String> foundWords = new ArrayList<>();
        String regEx = "^"+wordPlaceholder.replaceAll("_","["+lettersString+"]")+"\\b";
        for (String word : wordList) {
            if (word.matches(regEx)) foundWords.add(word);
        }
        return foundWords;
    }

}
