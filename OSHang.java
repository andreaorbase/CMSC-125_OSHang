import javax.swing.*;
import java.awt.*;

public class OSHang {
    public static void main(String[] args) {
        showSplashScreen();
        new MainMenu();
    }

    private static void showSplashScreen() {
        JWindow splash = new JWindow();
        JPanel panel = new JPanel(new BorderLayout());

        // Load the GIF directly (no resizing for animations)
        ImageIcon gifIcon = new ImageIcon("OSHang GUI/splashScreen.gif");
        JLabel splashLabel = new JLabel(gifIcon);
        
        panel.add(splashLabel, BorderLayout.CENTER);
        splash.getContentPane().add(panel);

        splash.setSize(900, 583);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        try {
            Thread.sleep(4500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.setVisible(false);
        splash.dispose();
    }
}
