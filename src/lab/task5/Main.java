package lab.task5;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        String sentence = "This is a sample sentence with some words. Words may repeat, but in this collection, we only want unique words.";

        String[] wordsArray = sentence.toLowerCase().split("[\\s,.]+");

        SortedSet<String> uniqueWords = new TreeSet<>(Arrays.asList(wordsArray));

        System.out.println("Unique words in alphabetical order: ");
        for (String word : uniqueWords) {
            System.out.println(word);
        }
    }
}
