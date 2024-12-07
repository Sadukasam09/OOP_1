package PartB.src;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MenuScreen extends JFrame {
    private BookManager bookManager;
    private MemberManager memberManager;

    public MenuScreen() {
        super("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bookManager = new BookManager();
        memberManager = new MemberManager();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons without styles
        panel.add(createStyledButton("Add Book", e -> showAddBookDialog()));
        panel.add(createStyledButton("View Books", e -> showViewBooksDialog()));
        panel.add(createStyledButton("Borrow Book", e -> showBorrowBookDialog()));
        panel.add(createStyledButton("Return Book", e -> showReturnBookDialog()));
        panel.add(createStyledButton("Add Member", e -> showAddMemberDialog()));
        panel.add(createStyledButton("View Members", e -> memberManager.viewMembersScreen()));

        add(panel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.addActionListener(action);
        
        return button;
    }

    private void showAddBookDialog() {
        JDialog dialog = new JDialog(this, "Add Book", true);
        dialog.setLayout(new GridLayout(4, 2, 5, 5));
        dialog.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField isbnField = new JTextField();

        dialog.add(new JLabel("Title:"));
        dialog.add(titleField);
        dialog.add(new JLabel("Author:"));
        dialog.add(authorField);
        dialog.add(new JLabel("ISBN:"));
        dialog.add(isbnField);

        JButton submitButton = new JButton("Add");
        submitButton.addActionListener(e -> {
            if (!titleField.getText().trim().isEmpty() && 
                !authorField.getText().trim().isEmpty() && 
                !isbnField.getText().trim().isEmpty()) {
                bookManager.addBook(
                    titleField.getText().trim(),
                    authorField.getText().trim(),
                    isbnField.getText().trim()
                );
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, 
                    "Please fill all fields", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(submitButton);
        dialog.add(cancelButton);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showViewBooksDialog() {
        JDialog dialog = new JDialog(this, "View Books", true);
        dialog.add(new JScrollPane(bookManager.getBookTable()));
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showBorrowBookDialog() {
        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN of book to borrow:");
        if (isbn != null && !isbn.trim().isEmpty()) {
            bookManager.borrowBook(isbn);
        }
    }

    private void showReturnBookDialog() {
        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN of book to return:");
        if (isbn != null && !isbn.trim().isEmpty()) {
            bookManager.returnBook(isbn);
        }
    }

    private void showAddMemberDialog() {
        JDialog dialog = new JDialog(this, "Add New Member", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
   
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
   
        JTextField nameField = new JTextField(20);
        JTextField memberIdField = new JTextField(20);
        
   
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 10);
        panel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Member ID:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(memberIdField, gbc);
        

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton submitButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        
     
        submitButton.setMnemonic(KeyEvent.VK_A);
        cancelButton.setMnemonic(KeyEvent.VK_C);
  
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String memberId = memberIdField.getText().trim();
                
                if (validateInput(name, memberId)) {
                    memberManager.addMember(name, memberId);
                    dialog.dispose();
                }
            } catch (Exception ex) {
                showError(dialog, ex.getMessage());
            }
        });
        
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
      
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        
       
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(buttonPanel, gbc);
        
    
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        
   
        dialog.getRootPane().setDefaultButton(submitButton);
        nameField.requestFocusInWindow();
        
      
        dialog.getRootPane().registerKeyboardAction(
            e -> dialog.dispose(),
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        dialog.setVisible(true);
    }

    private boolean validateInput(String name, String memberId) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (memberId.isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty");
        }
        if (!memberId.matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Member ID must be alphanumeric");
        }
        return true;
    }

    private void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Input Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}