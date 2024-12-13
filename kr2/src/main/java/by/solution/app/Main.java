package by.solution.app;

import by.solution.view_pattern.View;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        View view = new View("Map");
        view.setMinimumSize(new Dimension(500, 650));
        view.setVisible(true);
    }
}