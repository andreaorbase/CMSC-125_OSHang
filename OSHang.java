package imagecompression2;

import javax.swing.*;
import java.awt.*;

public class OSHang {
    private JFrame frame;

    public OSHang() {
        frame = new JFrame("OSHang");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 525);
        frame.setLayout(null); 
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        ImageIcon serverRoom = new ImageIcon("C:\\Users\\ACER\\Downloads\\OSHang\\OSHang GUI\\serverRoom.gif");
        JLabel serverRoomLabel = new JLabel(serverRoom);
        serverRoomLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        frame.setContentPane(serverRoomLabel);
        serverRoomLabel.setLayout(null);

        JButton playButton = createImageButton("C:\\Users\\ACER\\Downloads\\OSHang\\OSHang GUI\\playButton.png", 300, 150, 155, 50);
        JButton settingsButton = createImageButton("C:\\Users\\ACER\\Downloads\\OSHang\\OSHang GUI\\settingsButton.png", 300, 220, 155, 50);
        JButton exitButton = createImageButton("C:\\Users\\ACER\\Downloads\\OSHang\\OSHang GUI\\exitButton.png", 300, 290, 155, 50);
        JButton aboutButton = createImageButton("C:\\Users\\ACER\\Downloads\\OSHang\\OSHang GUI\\aboutButton.png", 700, 410, 50, 50);

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


    public static void main(String[] args) {
        new OSHang();
    }
}
