import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 'text' to input text, or 'file' to choose a file:");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("text")) {
            System.out.println("Enter your text:");
            String inputText = scanner.nextLine();
            processText(inputText);
        } else if (choice.equals("file")) {
            System.out.println("Choose a text file:");
            String filePath = scanner.nextLine();
            processFile(filePath);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    public static void processText(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        int totalWords = words.length;

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        int uniqueWords = wordFrequency.size();

        System.out.println("Total words: " + totalWords);
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void processFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            processText(content.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
