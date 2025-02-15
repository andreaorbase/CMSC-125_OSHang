import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameLogic {
    private JFrame frame;
    private JLabel wordLabel;
    private JTextField inputField;
    private JButton guessButton;
    private JButton replayButton;
    private JLabel statusLabel;
    private JLabel attemptsLabel;
    private JLabel hintLabel;
    
    private String wordToGuess;
    private String hint;
    private char[] guessedWord;
    private Set<Character> guessedLetters;
    private int maxAttempts = 6;
    private int attemptsLeft;

    public GameLogic() {
        loadRandomWord();
        guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
        guessedLetters = new HashSet<>();
        attemptsLeft = maxAttempts;

        initializeUI();
    }

    private void loadRandomWord() {
        List<String[]> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";"); // Assuming word and hint are separated by a semicolon
                if (parts.length == 2) {
                    words.add(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            wordToGuess = "JAVA"; // Default fallback word
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
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(7, 1));
        frame.setLocationRelativeTo(null);

        wordLabel = new JLabel(getMaskedWord(), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        hintLabel = new JLabel("Hint: " + hint, SwingConstants.CENTER);
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        
        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.CENTER);
        
        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessAction());
        
        replayButton = new JButton("Play Again");
        replayButton.addActionListener(new ReplayAction());
        replayButton.setEnabled(false);
        
        statusLabel = new JLabel("Enter a letter to guess", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft, SwingConstants.CENTER);
        
        frame.add(wordLabel);
        frame.add(hintLabel);
        frame.add(inputField);
        frame.add(guessButton);
        frame.add(replayButton);
        frame.add(statusLabel);
        frame.add(attemptsLabel);

        frame.setVisible(true);
    }

    private class GuessAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
            
            if (!correctGuess) {
                attemptsLeft--;
            }
            
            wordLabel.setText(getMaskedWord());
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
            inputField.setText("");
            
            checkGameStatus();
        }
    }

    private class ReplayAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new GameLogic();
        }
    }

    private String getMaskedWord() {
        return String.valueOf(guessedWord).replace("", " ").trim();
    }

    private void checkGameStatus() {
        if (String.valueOf(guessedWord).equals(wordToGuess)) {
            statusLabel.setText("Congratulations! You won!");
            guessButton.setEnabled(false);
            replayButton.setEnabled(true);
        } else if (attemptsLeft <= 0) {
            statusLabel.setText("Game Over! The word was: " + wordToGuess);
            guessButton.setEnabled(false);
            replayButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new GameLogic();
    }
}
