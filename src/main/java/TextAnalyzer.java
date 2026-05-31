/*
File Name: Problem Set Unit 5 (90%)
Author: Alex
Date Created: May 13, 2026
Date Last Modified: May 19, 2026
*/

import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the Text Analyzer.");
        System.out.print("\nPlease enter a sentence or paragraph: ");
        
        String text = input.nextLine(); // Input Intake
        
        int totalCharacters = text.length();
        int totalSpaces = 0;
        int totalVowels = 0;
        
        String textLowerCased = text.toLowerCase();
        
        for (int i = 0; i < textLowerCased.length(); i++) {
            char characters = textLowerCased.charAt(i);
            
            if (characters == ' ') { // Space detection
                totalSpaces++;
            } else if (characters == 'a' || characters == 'e' || characters == 'i' || characters == 'o' || characters == 'u') { // Vowel detection
                totalVowels++;
            }
        }
        
        // Removing punctuation
        String noPuncText = text.toLowerCase();
        noPuncText = noPuncText.replace(".", "");
        noPuncText = noPuncText.replace(",", "");
        noPuncText = noPuncText.replace("!", "");
        noPuncText = noPuncText.replace("?", "");
        noPuncText = noPuncText.replace(";", "");
        noPuncText = noPuncText.replace(":", "");
        
        // Creating an array for the unique words
        String[] words = noPuncText.trim().split(" ");
        
        // Parallel array for the word & their frequency
        String[] uniqueWords = new String[words.length];
        int[] wordCounts = new int[words.length];
        
        int uniqueCount = 0;
        int totalWords = 0;
        
        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            
            if (!currentWord.equals("")) { //Ignore empty strings
                
                totalWords++;
                
                boolean commonWords = currentWord.equals("the") || 
                                     currentWord.equals("a") || 
                                     currentWord.equals("an") || 
                                     currentWord.equals("and") || 
                                     currentWord.equals("is");
                
                // If word is not common
                if (!commonWords) {
                    boolean discovered = false; // If word is undiscovered
                
                    // Check if this word matches a unique word we already found
                    for (int j = 0; j < uniqueCount && discovered == false; j++) { // j is used, for i is occupied
                        if (uniqueWords[j].equals(currentWord)) {
                            wordCounts[j]++; // add count to assigned word
                            discovered = true; // End loop if word is discovered
                        }
                    }
                    
                    // If the word is undiscovered
                    if (!discovered) {
                        uniqueWords[uniqueCount] = currentWord;
                        wordCounts[uniqueCount] = 1;
                        uniqueCount++; // Expand unique word count
                    }
                } // <--- ADDED THIS BRACE to close "if (!commonWords)"
            }
        } // Closes the outer words loop
        
        // Displaying total characters, words, vowels and spaces
        System.out.println("\nTotal characters: " + totalCharacters);
        System.out.println("Total words: " + totalWords);
        System.out.println("Total vowels: " + totalVowels);
        System.out.println("Total spaces: " + totalSpaces);
        
        System.out.println("\nWord frequency:\n");
        if (totalWords == 0) {
            System.out.println("There are no words in your input");
        } else {
            // Display unique word & occurrences
            for (int j = 0; j < uniqueCount; j++) {
                System.out.println(uniqueWords[j] + " - " + wordCounts[j]);
            }
        }
    }
}