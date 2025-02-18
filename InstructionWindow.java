import javax.swing.*;

public class InstructionWindow {
    private JFrame newFrame;

    public InstructionWindow() {
        newFrame = new JFrame("About OSHang");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setSize(600, 400);
        newFrame.setLayout(null);
        newFrame.setResizable(false);
        newFrame.setLocationRelativeTo(null);

        // Set Background Image
        ImageIcon background = new ImageIcon("OSHang GUI/instructionWindow.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, newFrame.getWidth(), newFrame.getHeight());
        newFrame.setContentPane(backgroundLabel);
        backgroundLabel.setLayout(null);

        newFrame.setVisible(true);
    }
}
