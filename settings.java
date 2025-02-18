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
    public static boolean isMusicOn = true;
    public static boolean isSfxOn = true;
    
    public settings() {
        playMusic("OSHang GUI/settingsMusic.wav");
        newFrame = new JFrame("Settings Window");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(900, 583);
        newFrame.setLayout(null);
        newFrame.setResizable(false);
        newFrame.setLocationRelativeTo(null);

        ImageIcon background = new ImageIcon("OSHang GUI/settingsWindow.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, newFrame.getWidth(), newFrame.getHeight());
        newFrame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Home Button
        JButton homeButton = createImageButton("OSHang GUI/homeButton.png", 425, 433, 50, 50);
        homeButton.addActionListener(e -> {
            playSound("OSHang GUI/buttonClick.wav");
            stopMusic();
            newFrame.dispose();
            new MainMenu();
        });

        // Music Button
        JButton musicButton = createImageButton(isMusicOn? "OSHang GUI/musicOn.png": "OSHang GUI/musicOff.png", 475, 242, 50, 50);
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
       JButton sfxButton = createImageButton(isSfxOn ? "OSHang GUI/sfxOn.png" : "OSHang GUI/sfxOff.png", 475, 302, 50, 50);
        sfxButton.addActionListener(e -> {
            if (isSfxOn) {
                sfxButton.setIcon(new ImageIcon("OSHang GUI/sfxOff.png"));
            } else {
                sfxButton.setIcon(new ImageIcon("OSHang GUI/sfxOn.png"));
            }
            isSfxOn = !isSfxOn; // Toggle the value
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