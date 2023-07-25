import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Std.StdOut; // Custom class for standard output
import Trie.TrieST; // Custom Trie data structure

public class InvertedIndex {

    // Initialize a Trie data structure to store the inverted index
    static TrieST<Map<String, Integer>> wordTrie = new TrieST<>();

    // Method to tokenize the content and extract individual words
    static List<String> parseContent(String content) {
        // Split the content into words and store them in a list
        String[] tokens = content.split("\\s+");
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < tokens.length; i++) {
            // Remove any non-alphanumeric characters and convert to lowercase
            words.add(tokens[i].replaceAll("[^\\w]", "").toLowerCase());
        }
        return words;
    }

    // Method to construct the inverted index for a given file and list of words
    static void constructTrie(String filename, List<String> words) {
        // Create a hashmap to store the frequency of each word in the list
        HashMap<String, Integer> wordFrequencyMap = new HashMap<String, Integer>();
        for (String word : words) {
            // If the word is encountered for the first time, add it with frequency 1
            if (!wordFrequencyMap.containsKey(word)) {
                wordFrequencyMap.put(word, 1);
            } else {
                // If the word is already in the hashmap, increment its frequency
                Integer frequency = wordFrequencyMap.get(word);
                wordFrequencyMap.replace(word, frequency + 1);
            }
        }

        // For each word, check if it is already in the trie. If not, add it with its frequency
        for (String key : wordFrequencyMap.keySet()) {
            if (!wordTrie.contains(key)) {
                // Create a new hashmap to store filename-frequency mappings for the word
                HashMap<String, Integer> fileFrequencyMap = new HashMap<String, Integer>();
                fileFrequencyMap.put(filename, wordFrequencyMap.get(key));
                wordTrie.put(key, fileFrequencyMap);
            }
        }

        // Print the inverted index showing word and filename-frequency mappings
        for (String key : wordTrie.keys()) {
            StdOut.println(key + " " + wordTrie.get(key));
        }
    }
}
