import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GamePlay {
    private JFrame frame;
    private JLabel wordLabel, statusLabel, attemptsLabel, hintLabel;
    private JTextField inputField;
    private Map<Character, JLabel> keyboardMap;
    private String wordToGuess, hint;
    private char[] guessedWord;
    private Set<Character> guessedLetters;
    private int maxAttempts = 6, attemptsLeft;

    public GamePlay() {
        loadRandomWord();
        guessedWord = new char[wordToGuess.length()];
        Arrays.fill(guessedWord, '_');
        guessedLetters = new HashSet<>();
        attemptsLeft = maxAttempts;

        initializeUI();
    }

    private void loadRandomWord() {
        java.util.List<String[]> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) words.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
            wordToGuess = "JAVA";
            hint = "A popular programming language";
            return;
        }
        if (!words.isEmpty()) {
            Random random = new Random();
            String[] chosenWord = words.get(random.nextInt(words.size()));
            wordToGuess = chosenWord[0].toUpperCase();
            hint = chosenWord[1];
        }
    }

    private void initializeUI() {
        frame = new JFrame("Hangman Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        wordLabel = new JLabel(getMaskedWord(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        hintLabel = new JLabel("Hint: " + hint, SwingConstants.CENTER);
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        
        statusLabel = new JLabel("Enter a letter and press Enter", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);

        mainPanel.add(wordLabel);
        mainPanel.add(hintLabel);
        mainPanel.add(statusLabel);
        mainPanel.add(attemptsLabel);

        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(createKeyboardPanel(), BorderLayout.CENTER);
        frame.add(createInputFieldPanel(), BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    private JPanel createKeyboardPanel() {
        JPanel keyboardPanel = new JPanel(new GridLayout(3, 10, 2, 2));
        keyboardPanel.setOpaque(false);
        keyboardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        keyboardMap = new HashMap<>();
        String keyboardLayout = "QWERTYUIOPASDFGHJKLZXCVBNM";
        
        for (char letter : keyboardLayout.toCharArray()) {
            JLabel letterLabel = new JLabel(String.valueOf(letter), SwingConstants.CENTER);
            letterLabel.setFont(new Font("Arial", Font.BOLD, 12));
            letterLabel.setForeground(Color.BLACK);
            letterLabel.setPreferredSize(new Dimension(20, 20));
            letterLabel.setOpaque(false);
            keyboardMap.put(letter, letterLabel);
            keyboardPanel.add(letterLabel);
        }
        return keyboardPanel;
    }

    private JPanel createInputFieldPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(5);
        inputField.setFont(new Font("Arial", Font.BOLD, 20));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processGuess();
                }
            }
        });
        inputPanel.add(inputField);
        return inputPanel;
    }

    private void processGuess() {
        String input = inputField.getText().toUpperCase().trim();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            statusLabel.setText("Please enter a single valid letter.");
            return;
        }
        
        char guessedChar = input.charAt(0);
        if (guessedLetters.contains(guessedChar)) {
            statusLabel.setText("Letter already guessed. Try again.");
            return;
        }
        
        guessedLetters.add(guessedChar);
        boolean correctGuess = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guessedChar) {
                guessedWord[i] = guessedChar;
                correctGuess = true;
            }
        }
        
        if (!correctGuess) attemptsLeft--;
        updateKeyboard(guessedChar, correctGuess);
        wordLabel.setText(getMaskedWord());
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        inputField.setText("");
        
        checkGameStatus();
    }

    private void updateKeyboard(char guessedChar, boolean correctGuess) {
        JLabel letterLabel = keyboardMap.get(guessedChar);
        if (letterLabel != null) {
            letterLabel.setForeground(correctGuess ? Color.GREEN : Color.RED);
        }
    }

    private String getMaskedWord() {
        return String.valueOf(guessedWord).replace("", " ").trim();
    }

    private void checkGameStatus() {
        if (String.valueOf(guessedWord).equals(wordToGuess)) {
            statusLabel.setText("Congratulations! You won!");
            inputField.setEnabled(false);
        } else if (attemptsLeft <= 0) {
            statusLabel.setText("Game Over! The word was: " + wordToGuess);
            inputField.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new GamePlay();
    }
}
