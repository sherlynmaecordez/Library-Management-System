package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    public MainFrame() {
        setTitle("LibriSphere • Library Management System");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        try {
            URL iconUrl = getClass().getResource("/images/library-icon.png");
            if (iconUrl != null) {
                setIconImage(new ImageIcon(iconUrl).getImage());
            }
        } catch (Exception e) {
            System.err.println("Custom icon not loaded");
        }

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        JLabel header = new JLabel("LIBRISPHERE", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(new Color(30, 80, 150));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JLabel subtitle = new JLabel("Library Management System", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        subtitle.setForeground(new Color(100, 100, 100));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(header, BorderLayout.CENTER);
        headerPanel.add(subtitle, BorderLayout.SOUTH);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Books", createIcon("book.png"), new BookPanel());
        tabbedPane.addTab("Members", createIcon("user.png"), new MemberPanel());
        tabbedPane.addTab("Loans", createIcon("loan.png"), new LoanPanel());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private ImageIcon createIcon(String filename) {
        try {
            URL imgURL = getClass().getResource("/images/" + filename);
            return imgURL != null ? new ImageIcon(imgURL) : new ImageIcon();
        } catch (Exception e) {
            return new ImageIcon();
        }
    }
}