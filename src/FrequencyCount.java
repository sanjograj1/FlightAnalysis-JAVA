import java.util.HashMap;

public class FrequencyCount {

    static String[] parseContent(String content) {

        int x;
        // This inserts words into the array based on whitespace
        String[] text = content.split("\\s+");
        for (x = 0; x < text.length; x++) {
            // This removes any non-alphanumeric characters
            text[x] = text[x].replaceAll("[^\\w]", "");
            // This removes any number and alphanumeric words
            text[x] = text[x].replaceAll("[^[A-Za-z]+$]", "");
        }

        return text;
    }

    // Counting the frequency of words using HashMap

    static void printWordFrequency(String[] textList) {
        // Initializing HashMap for storing words and their frequency
        HashMap<String, Integer> frequency = new HashMap<String, Integer>();
        int x;
        // This counts word frequency
        for (x = 0; x < textList.length; x++) {
            if (textList[x].equals("")) {
                continue;
            }
            if (frequency.get(textList[x].toLowerCase()) != null) {

                int count = frequency.get(textList[x].toLowerCase());
                frequency.put(textList[x].toLowerCase(), count + 1);
            } else {
                frequency.put(textList[x].toLowerCase(), 1);
            }
        }
        System.out.println("Words and their corresponding frequency:");
        // This prints the key - value pair of words and their frequency
        for (String hash : frequency.keySet()) {

            String word = hash.toString();
            String textFrequency = frequency.get(hash).toString();
            System.out.println("[" + word + " : " + textFrequency + "]");
        }
    }
}