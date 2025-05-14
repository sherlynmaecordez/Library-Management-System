package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
    public MainFrame() {
        setTitle("Library Management System");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
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

        initComponents();
    }

    private void initComponents() {
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // RESTORED BIG HEADER
        JLabel header = new JLabel("LIBRARY MANAGEMENT SYSTEM", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(new Color(70, 130, 180));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(header, BorderLayout.NORTH);

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // Add panels to tabs
        tabbedPane.addTab("Books", createIcon("book.png"), new BookPanel());
        tabbedPane.addTab("Members", createIcon("user.png"), new MemberPanel());
        tabbedPane.addTab("Loans", createIcon("loan.png"), new LoanPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private ImageIcon createIcon(String filename) {
        try {
            URL imgURL = getClass().getResource("/images/" + filename);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Couldn't find icon: " + filename);
                return new ImageIcon();
            }
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
            return new ImageIcon();
        }
    }
}