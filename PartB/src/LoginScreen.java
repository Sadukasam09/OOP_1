package PartB.src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private static final String PLACEHOLDER_USERNAME = "Username";
    private static final String PLACEHOLDER_PASSWORD = "Password";
    private static final Color PLACEHOLDER_COLOR = Color.GRAY;
    private static final Color INPUT_COLOR = Color.BLACK;

    public LoginScreen() {
        setTitle("Library Management System - Login");
        setSize(450, 550);
        initializeLoginPanel();
        setVisible(true);
    }

    private void initializeLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = createGridBagConstraints();
        
        
        addTitle(loginPanel, gbc);
        
        
        usernameField = createUsernameField();
        gbc.gridy++;
        loginPanel.add(usernameField, gbc);
        
        
        passwordField = createPasswordField();
        gbc.gridy++;
        loginPanel.add(passwordField, gbc);
        
        
        JButton loginButton = createLoginButton();
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 10, 10);
        loginPanel.add(loginButton, gbc);
        
        add(loginPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(loginButton);
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        return gbc;
    }

    private void addTitle(JPanel panel, GridBagConstraints gbc) {
        JLabel titleLabel = new JLabel("Library Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);
    }

    private JTextField createUsernameField() {
        JTextField field = new JTextField(20);
        field.setText(PLACEHOLDER_USERNAME);
        field.setForeground(PLACEHOLDER_COLOR);
        
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(PLACEHOLDER_USERNAME)) {
                    field.setText("");
                    field.setForeground(INPUT_COLOR);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(PLACEHOLDER_USERNAME);
                    field.setForeground(PLACEHOLDER_COLOR);
                }
            }
        });
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setEchoChar((char) 0);
        field.setText(PLACEHOLDER_PASSWORD);
        field.setForeground(PLACEHOLDER_COLOR);
        
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(PLACEHOLDER_PASSWORD)) {
                    field.setText("");
                    field.setEchoChar('•');
                    field.setForeground(INPUT_COLOR);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setText(PLACEHOLDER_PASSWORD);
                    field.setEchoChar((char) 0);
                    field.setForeground(PLACEHOLDER_COLOR);
                }
            }
        });
        return field;
    }

    private JButton createLoginButton() {
        JButton button = new JButton("Login");
        button.setPreferredSize(new Dimension(200, 35));
        button.addActionListener(e -> handleLogin());
        return button;
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.equals(PLACEHOLDER_USERNAME) || password.equals(PLACEHOLDER_PASSWORD)) {
            showError("Please enter your credentials");
            return;
        }
        
        if (!validateCredentials(username, password)) {
            showError("Invalid username or password");
            return;
        }
        
        
        proceedWithLogin();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Login Error",
            JOptionPane.ERROR_MESSAGE);
    }

    private boolean validateCredentials(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }

    private void proceedWithLogin() {
        new MenuScreen();
        dispose();
    }
}