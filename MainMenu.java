import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainMenu {
    private JFrame frame;
    private static Clip clip;

    public MainMenu() {
        playMusic("OSHang GUI/menuMusic.wav");
        frame = new JFrame("OSHang");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 525);
        frame.setLayout(null); 
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        ImageIcon serverRoom = new ImageIcon("OSHang GUI/serverRoom.gif");
        JLabel serverRoomLabel = new JLabel(serverRoom);
        serverRoomLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.setContentPane(serverRoomLabel);
        serverRoomLabel.setLayout(null);

        JButton playButton = createImageButton("OSHang GUI/playButton.png", 300, 150, 155, 50);
        JButton settingsButton = createImageButton("OSHang GUI/settingsButton.png", 300, 220, 155, 50);
        JButton exitButton = createImageButton("OSHang GUI/exitButton.png", 300, 290, 155, 50);
        JButton aboutButton = createImageButton("OSHang GUI/aboutButton.png", 700, 410, 50, 50);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
                playSound("OSHang GUI/buttonClick.wav"); 
                frame.dispose(); // Close current window
                new playButton(); // Open new window
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
                playSound("OSHang GUI/buttonClick.wav"); 
                frame.dispose(); // Close current window
                new instructions(); // Open new window
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
                playSound("OSHang GUI/buttonClick.wav"); 
                frame.dispose(); // Close current window
                new settings(); // Open new window
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
                playSound("OSHang GUI/buttonClick.wav"); 
                System.exit(0); // Terminate the application
            }
        });

        serverRoomLabel.add(playButton);
        serverRoomLabel.add(settingsButton);
        serverRoomLabel.add(exitButton);
        serverRoomLabel.add(aboutButton);

        frame.setVisible(true);
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

    public static void main(String[] args) {
        new MainMenu();
    }
}
