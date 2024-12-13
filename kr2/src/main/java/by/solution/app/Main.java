package by.solution.app;

import by.solution.controller.Controller;
import by.solution.model.TreeList;
import by.solution.view.View;

public class Main {
    public static void main(String[] args) {
        TreeList<Integer> tree = new TreeList<>();
        View view = new View("Tree List Application");
        Controller controller = new Controller(tree, view);
        view.setVisible(true);
    }
}