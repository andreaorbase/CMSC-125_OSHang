import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class settings {
    private JFrame newFrame;
    private static Clip clip;
    private boolean isMusicOn = true;
    private boolean isSfxOn = true;
    
    public settings() {
        playMusic("OSHang GUI/settingsMusic.wav");
        newFrame = new JFrame("Settings Window");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(800, 525);
        newFrame.setLayout(null);
        newFrame.setResizable(false);
        newFrame.setLocationRelativeTo(null);

        ImageIcon background = new ImageIcon("OSHang GUI/settingsWindow.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, newFrame.getWidth(), newFrame.getHeight());
        newFrame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Home Button
        JButton homeButton = createImageButton("OSHang GUI/homeButton.png", 370, 400, 50, 50);
        homeButton.addActionListener(e -> {
            playSound("OSHang GUI/buttonClick.wav");
            newFrame.dispose();
            new MainMenu();
        });

        // Music Button
        JButton musicButton = createImageButton("OSHang GUI/musicOn.png", 425, 215, 50, 50);
        musicButton.addActionListener(e -> {
            if (isMusicOn) {
                stopMusic();
                musicButton.setIcon(new ImageIcon("OSHang GUI/musicOff.png"));
            } else {
                playMusic("OSHang GUI/settingsMusic.wav");
                musicButton.setIcon(new ImageIcon("OSHang GUI/musicOn.png"));
            }
            isMusicOn = !isMusicOn;
        });

        // SFX Button
        JButton sfxButton = createImageButton("OSHang GUI/sfxOn.png", 425, 275, 50, 50);
        sfxButton.addActionListener(e -> {
            if (isSfxOn) {
                sfxButton.setIcon(new ImageIcon("OSHang GUI/sfxOff.png"));
            } else {
                sfxButton.setIcon(new ImageIcon("OSHang GUI/sfxOn.png"));
            }
            isSfxOn = !isSfxOn;
        });

        backgroundLabel.add(homeButton);
        backgroundLabel.add(musicButton);
        backgroundLabel.add(sfxButton);

        newFrame.setVisible(true);
    }

    private JButton createImageButton(String imagePath, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        return button;
    }

    private static void playSound(String filePath) {
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
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
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
