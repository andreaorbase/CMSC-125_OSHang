import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WordGuessed {
    private JFrame frame;
    
    public WordGuessed() {
        frame = new JFrame("You Won!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        
        JLabel messageLabel = new JLabel("Congratulations! You guessed the word!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(messageLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        
        JButton homeButton = new JButton(new ImageIcon("OSHang GUI/homeButton.png"));
        homeButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });
        
        JButton nextButton = new JButton(new ImageIcon("OSHang GUI/nextButton.png"));
        nextButton.addActionListener(e -> {
            frame.dispose();
            new GamePlay();
        });
        
        buttonPanel.add(homeButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
}

class GameOver {
    private JFrame frame;
    
    public GameOver() {
        frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        
        JLabel messageLabel = new JLabel("Game Over! The word was lost!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(messageLabel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        
        JButton homeButton = new JButton(new ImageIcon("homeButton.png"));
        homeButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });
        
        JButton againButton = new JButton(new ImageIcon("againButton.png"));
        againButton.addActionListener(e -> {
            frame.dispose();
            new GamePlay();
        });
        
        buttonPanel.add(homeButton);
        buttonPanel.add(againButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
}
