import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class instructions {
    private JFrame newFrame;

    public instructions() {
        newFrame = new JFrame("Instruction Window");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(800, 525);
        newFrame.setLayout(null); // Using absolute positioning like OSHang
        newFrame.setResizable(false);
        newFrame.setLocationRelativeTo(null);

        ImageIcon background = new ImageIcon("OSHang GUI/instructionWindow.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, newFrame.getWidth(), newFrame.getHeight());
        newFrame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        JButton startButton = createImageButton("OSHang GUI/playButton.png", 450, 350, 155, 50);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose(); // Close current window
                new playButton(); // Open new window
            }
        });
        
        backgroundLabel.add(startButton);

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
}