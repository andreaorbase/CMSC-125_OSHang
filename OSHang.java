import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;

public class OSHang {
    public static void main(String[] args) {
        showSplashScreen();
        new MainMenu(); 
    }

    private static void showSplashScreen() {
        JWindow splash = new JWindow();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Load and display the splash screen image
        ImageIcon splashImage = new ImageIcon("OSHang GUI/splashScreen.gif"); // REPLACE WITH THE ACTUAL SPLASH SCREEN
        JLabel splashLabel = new JLabel(splashImage);
        
        panel.add(splashLabel, BorderLayout.CENTER);
        splash.getContentPane().add(panel);

        splash.setSize(splashImage.getIconWidth(), splashImage.getIconHeight());
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        try {
            Thread.sleep(4500); // Show splash screen for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.setVisible(false);
        splash.dispose();
    }
}
