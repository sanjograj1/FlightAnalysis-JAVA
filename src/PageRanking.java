import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Common.Utils;

public class PageRanking {

    // This method takes a regular expression pattern as input and returns a Hashtable
    // containing the count of matches found for the pattern in each file in the "Web Pages" folder.
    public Hashtable<String, Integer> matchPattern(String pattern) throws IOException {
        // Initialize a Hashtable to store the page ranking (filename -> count of matches).
        Hashtable<String, Integer> pageRank = new Hashtable<String, Integer>();
        
        // Variable to store the content of each file read from the "Web Pages" folder.
        String[] fileContent;
        
        // Get the folder where the web pages are located.
        File folder = new File("src/resc/Web Pages/");

        // Loop through each file in the folder.
        for (File fileEntry : folder.listFiles()) {
            // Read the content of the file into the 'fileContent' array.
            fileContent = Utils.readFile(folder.getAbsolutePath() + "/" + fileEntry.getName());
            
            // Check if the file content is not null (file was read successfully).
            if (fileContent != null) {
                // Iterate through each line of the file's content.
                for (int i = 0; i < fileContent.length; i++) {
                    // Compile the given regular expression pattern into a Pattern object.
                    Pattern p1 = Pattern.compile(pattern);
                    
                    // Create a Matcher object to find matches in the current line.
                    Matcher m = p1.matcher(fileContent[i]);
                    
                    // Find all occurrences of the pattern in the current line and update the pageRank hashtable.
                    while (m.find()) {
                        // If the file is already in the hashtable, increment its count by 1.
                        // Otherwise, add it to the hashtable with a count of 1.
                        pageRank.merge(fileEntry.getName(), 1, Integer::sum);
                    }
                }
            }
        }
        // Return the final pageRank hashtable containing the counts of matches for each file.
        return pageRank;
    }
}
