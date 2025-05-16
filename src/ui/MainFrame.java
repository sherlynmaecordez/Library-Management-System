package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Library Management System");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set window icon
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/library-icon.png"));
            Image image = icon.getImage();
            setIconImage(image); // This sets the window icon
        } catch (Exception e) {
            System.err.println("Could not load window icon: " + e.getMessage());
        }
        
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create header panel with logo and text
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));

        // Logo and title panel
        JPanel logoTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        logoTitlePanel.setBackground(new Color(245, 245, 245));

        try {
            // Load and scale the logo for header
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/library-icon.png"));
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoTitlePanel.add(logoLabel);
        } catch (Exception e) {
            System.err.println("Could not load logo: " + e.getMessage());
        }

        // System name label
        JLabel systemNameLabel = new JLabel("LibriSphere");
        systemNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        systemNameLabel.setForeground(new Color(70, 130, 180));
        logoTitlePanel.add(systemNameLabel);

        headerPanel.add(logoTitlePanel, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Tabbed pane for different sections
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Books", loadIcon("book.png"), new BookPanel());
        tabbedPane.addTab("Members", loadIcon("user.png"), new MemberPanel());
        tabbedPane.addTab("Loans", loadIcon("loan.png"), new LoanPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private ImageIcon loadIcon(String filename) {
        try {
            URL imageUrl = getClass().getResource("/images/" + filename);
            return imageUrl != null ? new ImageIcon(imageUrl) : new ImageIcon();
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon();
        }
    }
}