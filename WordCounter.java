import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WordCounter extends JFrame {
    private JTextArea textArea;
    private JTextArea resultArea;

    public WordCounter() {
        setTitle("Word Counter");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Enter text or choose a file:");
        panel.add(label, BorderLayout.NORTH);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countWords(textArea.getText());
            }
        });
        panel.add(countButton, BorderLayout.SOUTH);

        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });
        panel.add(chooseFileButton, BorderLayout.SOUTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        panel.add(resultScrollPane, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void countWords(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        int totalWords = words.length;
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        resultArea.setText("Total words: " + totalWords + "\n");
        resultArea.append("Unique words: " + uniqueWords.size() + "\n\n");
        resultArea.append("Word Frequency:\n");
        for (Map.Entry<String, Integer> entry : wordFreq.entrySet()) {
            resultArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                countWords(content.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordCounter().setVisible(true);
            }
        });
    }
}

