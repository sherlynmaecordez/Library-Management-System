package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class MemberPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private JTextField idField, nameField, emailField;
    private JTextArea displayArea;

    public MemberPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            "Add New Member", 
            TitledBorder.LEFT, 
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(80, 100, 160)));

        addStyledField(inputPanel, "Member ID:", idField = new JTextField());
        addStyledField(inputPanel, "Name:", nameField = new JTextField());
        addStyledField(inputPanel, "Email:", emailField = new JTextField());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        JButton addButton = createStyledButton("Add Member", new Color(80, 100, 160));
        addButton.addActionListener(new AddMemberListener());
        buttonPanel.add(addButton);

        // North Panel
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        northPanel.setBackground(new Color(245, 245, 245));
        add(northPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        displayArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Member Records"));
        add(scrollPane, BorderLayout.CENTER);
    }

    // Reuse helper methods from BookPanel
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

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker()),
            BorderFactory.createEmptyBorder(8, 25, 8, 25)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private class AddMemberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            
            if (!id.isEmpty() && !name.isEmpty() && !email.isEmpty()) {
                displayArea.append("✓ Added: " + name + " (ID: " + id + ", Email: " + email + ")\n");
                idField.setText("");
                nameField.setText("");
                emailField.setText("");
            } else {
                JOptionPane.showMessageDialog(MemberPanel.this, 
                    "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}