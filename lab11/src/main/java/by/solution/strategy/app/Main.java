package by.solution.strategy.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToyManagerApp manager = new ToyManagerApp();
            manager.setVisible(true);
        });
    }
}
