import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    public static void main(String[] args) {
        System.out.println("Welcome to the Word Count Tool!");
        System.out.println("Enter '0' to input text, or '1' to read from a file:");

        try (BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in))) {
            String choice = reader.readLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter your text:");
                    String inputText = reader.readLine();
                    processText(inputText);
                    break;
                case "2":
                    System.out.println("Enter the path to the text file:");
                    String filePath = reader.readLine();
                    processFile(filePath);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processText(String text) {
        String[] words = text.split("\\s+");
        int totalWords = words.length;
        Map<String, Integer> wordFrequency = getWordFrequency(words);
        int uniqueWords = wordFrequency.size();

        System.out.println("Total words: " + totalWords);
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Word frequency:");

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void processFile(String filePath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = fileReader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            String fileContent = contentBuilder.toString();
            processText(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> getWordFrequency(String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency;
    }
}
