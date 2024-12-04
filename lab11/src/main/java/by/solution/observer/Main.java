package by.solution.observer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeyObserverApp app = new KeyObserverApp("Key Observer Application");
            app.setVisible(true);
        });
    }
}