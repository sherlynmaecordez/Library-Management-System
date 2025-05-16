package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private JTextField bookIsbnField, memberIdField;
    private JTextArea displayArea;

    public LoanPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Manage Loans",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(90, 140, 90)));

        addStyledField(inputPanel, "Book ISBN:", bookIsbnField = new JTextField());
        addStyledField(inputPanel, "Member ID:", memberIdField = new JTextField());

        // ENHANCED SUBMIT BUTTON
        JButton submitButton = new JButton("SUBMIT LOAN");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setBackground(new Color(46, 139, 87)); // Forest green
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effects
        submitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(new Color(34, 139, 34)); // Darker green
            }
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(new Color(46, 139, 87));
            }
        });
        submitButton.addActionListener(new CreateLoanListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(submitButton);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Loan Records"));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addStyledField(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(label);
        
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        panel.add(field);
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
            }
        }
    }
}