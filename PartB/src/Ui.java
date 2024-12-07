package PartB.src;

import java.awt.Font;
import javax.swing.*;

public class Ui {
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(() -> {
            try {
                showSplashScreen();
            } catch (Exception e) {
                handleStartupError(e);
            }
        });
    }

    private static void showSplashScreen() {
        JWindow splashScreen = new JWindow();
        splashScreen.setSize(400, 300);
        
        JPanel splashPanel = new JPanel();
        
        JLabel titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        
        splashPanel.add(titleLabel);
        splashPanel.add(progressBar);
        
        splashScreen.add(splashPanel);
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);
        

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(2000);
                return null;
            }
            
            @Override
            protected void done() {
                splashScreen.dispose();
                new LoginScreen();
            }
        }.execute();
    }

    private static void handleStartupError(Exception e) {
        JOptionPane.showMessageDialog(
            null, 
            "Failed to start application: " + e.getMessage(), 
            "Startup Error", 
            JOptionPane.ERROR_MESSAGE
        );
        System.exit(1);
    }
}