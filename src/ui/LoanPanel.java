package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;

public class LoanPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField bookIsbnField, memberIdField, searchField;
    private JTextArea displayArea;
    private JComboBox<String> filterComboBox;

    public LoanPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        filterComboBox = new JComboBox<>(new String[]{"Book ISBN", "Member ID"});
        filterComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JButton searchButton = createButton("🔍 SEARCH LOANS", new Color(0, 150, 136));
        searchButton.addActionListener(e -> filterLoans());

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(filterComboBox, BorderLayout.WEST);
        searchPanel.add(searchButton, BorderLayout.EAST);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Manage Loans",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(0, 150, 136)));

        addStyledField(inputPanel, "Book ISBN:", bookIsbnField = new JTextField());
        addStyledField(inputPanel, "Member ID:", memberIdField = new JTextField());

        JButton submitButton = createButton("✔ SUBMIT LOAN", new Color(0, 150, 136));
        submitButton.addActionListener(new CreateLoanListener());
        
        JButton returnButton = createButton("↩ RETURN BOOK", new Color(229, 57, 53));
        returnButton.addActionListener(new ReturnBookListener());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(submitButton);
        buttonPanel.add(returnButton);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(searchPanel, BorderLayout.NORTH);
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        displayArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(200, 200, 200, 50));
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 60));
                g2d.drawString("LibriSphere", 
                              getWidth()/2 - 180, 
                              getHeight()/2 + 20);
            }
        };
        
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        displayArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Loan Records"));
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker()),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(brighterColor(bgColor, 20));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(brighterColor(bgColor.darker(), 10)),
                    BorderFactory.createEmptyBorder(10, 25, 10, 25)
                ));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(bgColor.darker()),
                    BorderFactory.createEmptyBorder(10, 25, 10, 25)
                ));
            }
        });
        return button;
    }

    private Color brighterColor(Color color, int percent) {
        int r = Math.min(255, color.getRed() + (255 - color.getRed()) * percent / 100);
        int g = Math.min(255, color.getGreen() + (255 - color.getGreen()) * percent / 100);
        int b = Math.min(255, color.getBlue() + (255 - color.getBlue()) * percent / 100);
        return new Color(r, g, b);
    }

    private void addStyledField(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);
        
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        panel.add(field);
    }

    private void filterLoans() {
        String query = searchField.getText().toLowerCase();
        String filterType = (String) filterComboBox.getSelectedItem();
        
        displayArea.setText("");
        displayArea.append("🔍 Search Results (" + filterType + ": " + query + ")\n\n");
        displayArea.append("(Search functionality will display real results\nwhen connected to your Library class)");
    }

    private class CreateLoanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String isbn = bookIsbnField.getText();
            String memberId = memberIdField.getText();
            
            if (!isbn.isEmpty() && !memberId.isEmpty()) {
                LocalDate today = LocalDate.now();
                LocalDate dueDate = today.plusWeeks(2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                
                displayArea.append("✓ Loan created:\n  Book: " + isbn + 
                                 "\n  Member: " + memberId + 
                                 "\n  Date: " + today.format(formatter) + 
                                 "\n  Due: " + dueDate.format(formatter) + "\n\n");
                bookIsbnField.setText("");
                memberIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(LoanPanel.this, 
                    "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ReturnBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String isbn = bookIsbnField.getText();
            
            if (!isbn.isEmpty()) {
                displayArea.append("✓ Book returned: " + isbn + "\n\n");
                bookIsbnField.setText("");
                memberIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(LoanPanel.this, 
                    "Please enter the ISBN of the book to return", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}