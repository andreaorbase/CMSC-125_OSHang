import javax.swing.*;
import java.awt.*;

public class playButton {
    private JFrame newFrame;

    public playButton() {
        newFrame = new JFrame("Play Window");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(800, 525);
        newFrame.setLayout(null); // Using absolute positioning like OSHang
        newFrame.setResizable(false);
        newFrame.setLocationRelativeTo(null);

        // Background image (replace with correct path)
        ImageIcon background = new ImageIcon("C:\\Users\\ACER\\Downloads\\OSHang\\OSHang GUI\\playWindowBackground.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, newFrame.getWidth(), newFrame.getHeight());
        newFrame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        // Sample label (you can add buttons or other UI elements here)
        JLabel label = new JLabel("OSHang");
        label.setBounds(300, 200, 300, 50);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        backgroundLabel.add(label);

        newFrame.setVisible(true);
    }
}