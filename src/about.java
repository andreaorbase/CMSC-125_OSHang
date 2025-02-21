import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class about {
    private JFrame newFrame;
    private static Clip clip;
    public static boolean isSfxOn = true;
    public static boolean isMusicOn = true;

    public about() {
        playMusic("OSHang GUI/aboutMusic.wav");
        newFrame = new JFrame("About OSHang");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(900, 583);
        newFrame.setLayout(null);
        newFrame.setResizable(false);
        newFrame.setLocationRelativeTo(null);

        ImageIcon background = new ImageIcon("OSHang GUI/aboutWindow.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, newFrame.getWidth(), newFrame.getHeight());
        newFrame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        JButton backButton = createImageButton("OSHang GUI/homeButton.png", 425, 453, 50, 50);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
                playSound("OSHang GUI/buttonClick.wav"); 
                newFrame.dispose(); // Close current window
                new MainMenu(); // Open new window
            }
        });
        
        backgroundLabel.add(backButton);

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