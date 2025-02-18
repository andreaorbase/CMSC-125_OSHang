import javax.swing.*;
import java.awt.*;

class WordGuessed {
    private JFrame frame;

    public WordGuessed() {
        frame = new JFrame("Correct Guessed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 320);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Background Panel
        BackgroundPanel correctGuessPanel = new BackgroundPanel("OSHang GUI/correctGuess.png");
        correctGuessPanel.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight());

        JButton homeButton = createImageButton("OSHang GUI/homeButton.png", 188, 230);
        homeButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });

        JButton nextButton = createImageButton("OSHang GUI/nextButton.png", 248, 230);
        nextButton.addActionListener(e -> {
            frame.dispose();
            new GamePlay();
        });

        layeredPane.add(correctGuessPanel, Integer.valueOf(0));
        layeredPane.add(homeButton, Integer.valueOf(1));
        layeredPane.add(nextButton, Integer.valueOf(1));

        frame.add(layeredPane);
        frame.setVisible(true);
    }

    private JButton createImageButton(String path, int x, int y) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(scaledImage));
        button.setBounds(x, y, 50, 50);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
}

class GameOver {
    private JFrame frame;

    public GameOver() {
        frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 320);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Background Panel
        BackgroundPanel wrongGuessPanel = new BackgroundPanel("OSHang GUI/wrongGuess.png");
        wrongGuessPanel.setBounds(0, 0, frame.getContentPane().getWidth(), frame.getContentPane().getHeight());

        JButton homeButton = createImageButton("OSHang GUI/homeButton.png", 188, 230);
        homeButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });

        JButton againButton = createImageButton("OSHang GUI/againButton.png", 248, 230);
        againButton.addActionListener(e -> {
            frame.dispose();
            new GamePlay();
        });

        layeredPane.add(wrongGuessPanel, Integer.valueOf(0));
        layeredPane.add(homeButton, Integer.valueOf(1));
        layeredPane.add(againButton, Integer.valueOf(1));

        frame.add(layeredPane);
        frame.setVisible(true);
    }

    private JButton createImageButton(String path, int x, int y) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(scaledImage));
        button.setBounds(x, y, 50, 50);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
}

// Background Panel Class
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new BorderLayout()); // Ensure proper layout handling
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setSize(getParent().getSize()); // Resize to match the parent container
    }
}

