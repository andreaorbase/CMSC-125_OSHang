import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class WordGuessed {
    private JFrame frame;
    private static Clip clip;
    public static boolean isMusicOn = true;
    public static boolean isSfxOn = true;

    public WordGuessed() {
        playMusic("OSHang GUI/gameWinnerMusic.wav");
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

        JButton homeButton = createImageButton("OSHang GUI/homeButton.png", 188, 210);
        homeButton.addActionListener(e -> {
            stopMusic();
            playSound("OSHang GUI/buttonClick.wav"); 
            frame.dispose();
            new MainMenu();
        });

        JButton nextButton = createImageButton("OSHang GUI/nextButton.png", 248, 210);
        nextButton.addActionListener(e -> {
            stopMusic();
            playSound("OSHang GUI/buttonClick.wav"); 
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

    private static void playSound(String filePath) {
        if (!settings.isSfxOn) { // Check global SFX setting
            return; // Exit without playing sound
        }
        
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(String filePath) {
        if (!settings.isMusicOn) { // Check global SFX setting
            return; // Exit without playing sound
        }
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music indefinitely
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    
    
    

}
class GameOver extends JDialog {
    private static Clip clip;
    public static boolean isMusicOn = true;
    public static boolean isSfxOn = true;

    private JFrame parent; // Store the reference to the GamePlay frame

    public GameOver(JFrame parent, int wordsGuessed) {
        
        super(parent, "Game Over", true); // Modal dialog
        playMusic("OSHang GUI/gameOverMusic.wav");
        this.parent = parent; // Store reference to the parent frame

        setSize(500, 320);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(parent); // Center relative to the parent window

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, getWidth(), getHeight());

        // Background Panel
        BackgroundPanel wrongGuessPanel = new BackgroundPanel("OSHang GUI/wrongGuess.png");
        wrongGuessPanel.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight());

        // Display the number of words guessed
        JLabel wordsGuessedLabel = new JLabel("Words Guessed: " + wordsGuessed, SwingConstants.CENTER);
        wordsGuessedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        wordsGuessedLabel.setForeground(Color.WHITE);
        wordsGuessedLabel.setBounds(50, 165, 400, 30); // Position the label
        layeredPane.add(wordsGuessedLabel, Integer.valueOf(1));

        // Home Button
        JButton homeButton = createImageButton("OSHang GUI/homeButton.png", 188, 210);
        homeButton.addActionListener(e -> {
            stopMusic();
            playSound("OSHang GUI/buttonClick.wav"); 
            dispose(); 
            if (parent != null) {
                parent.dispose(); 
            }
            new MainMenu(); 
        });

        // Try Again Button
        JButton againButton = createImageButton("OSHang GUI/againButton.png", 248, 210);
        againButton.addActionListener(e -> {
            stopMusic();
            playSound("OSHang GUI/buttonClick.wav"); 
            dispose(); 
            if (parent != null) {
                parent.dispose(); 
            }
            new GamePlay(); 
        });

        layeredPane.add(wrongGuessPanel, Integer.valueOf(0));
        layeredPane.add(homeButton, Integer.valueOf(1));
        layeredPane.add(againButton, Integer.valueOf(1));

        add(layeredPane);
        setVisible(true);
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
    private static void playSound(String filePath) {
        if (!settings.isSfxOn) { // Check global SFX setting
            return; // Exit without playing sound
        }
        
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(String filePath) {
        if (!settings.isMusicOn) { // Check global SFX setting
            return; // Exit without playing sound
        }
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music indefinitely
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}

// Background Panel Class
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new BorderLayout()); 
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

