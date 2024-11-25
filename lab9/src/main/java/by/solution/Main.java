package by.solution;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tabbed Pane Example");
        frame.setSize(1800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        ListFrame listFrame = new ListFrame(
                List.of("a", "b", "c", "d"),
                List.of("e", "f", "g", "h"));
        tabbedPane.addTab("List Frame", listFrame.getContentPane());


        ButtonFrame buttonFrame = new ButtonFrame();
        tabbedPane.addTab("Button Frame", buttonFrame.getContentPane());


        RadioButtonFrame radioButtonFrame = new RadioButtonFrame();
        tabbedPane.addTab("Radio Button Frame", radioButtonFrame.getContentPane());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}