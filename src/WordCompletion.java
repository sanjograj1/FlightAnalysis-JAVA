import java.io.File;
import java.util.Scanner;

import Std.StdOut;
import Trie.TrieST;
import Common.Utils;

public class WordCompletion {
    private static Scanner scannedWord;

    public static void main(String[] args) {

    }

    public static void run() throws Exception {
        scannedWord = new Scanner(System.in);

        String[] content = null;
        TrieST<Integer> trie = new TrieST<Integer>();
        File fileFolder = new File("src/resc/Web Pages/");
        for (File fileEntry : fileFolder.listFiles()) {
            content = Utils.readFile(fileFolder.getAbsolutePath() + "/" + fileEntry.getName());
            if (content != null)
                for (int a = 0; a < content.length; a++)
                    if (Utils.IsNumAlpha(content[a]))
                        trie.put(content[a].toLowerCase(), a);
        }

        // For Testing
        Utils.writeFile("TrieDictionary", trie.keys().toString().replace(" ", "\n"));

        System.out.print("Please enter a word: ");
        String input = scannedWord.nextLine();

        String text = input.toLowerCase();
        if (trie.contains(text)) {
            StdOut.println(text);
        } else {
            StdOut.println("Similar words to the entered word:");
            for (String textString : trie.keysWithPrefix(text))
                StdOut.println(textString);
        }
    }

}