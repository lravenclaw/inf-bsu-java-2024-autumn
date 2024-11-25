package by.solution.example;

import by.solution.pair.PairElementContainer;
import by.solution.single.SingleElementContainer;

import javax.swing.*;
import java.awt.*;

public class ContainerApp extends JFrame {
    private PairElementContainer<String, String> model = new PairElementContainer<>();
    private SetView view = new SetView(model);
    private Controller controller = new Controller(model ,view);

    ContainerApp() {
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1, 2));

        add(view);
        add(controller);
    }
}