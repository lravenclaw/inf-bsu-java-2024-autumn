package by.solution.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToyManager manager = new ToyManager();
            manager.setVisible(true);
        });
    }
}
