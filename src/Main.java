import javax.swing.*;          // For Swing components (JFrame, ImageIcon)
import javax.swing.border.*;   // For BorderFactory
import java.awt.*;             // For Color, Font, and other AWT classes
import java.awt.event.*;       // For event handling
import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}