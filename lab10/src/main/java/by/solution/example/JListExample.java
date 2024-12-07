package by.solution.example;

import java.awt.*;
import javax.swing.*;
import by.solution.pair.MyMap;

public class JListExample extends JFrame {
    private static JList<MyMap.Entry<String, Integer>> b;

    public static void main(String[] args) {
        JFrame f = new JListExample();
        f.setTitle("frame");
        JPanel p = new JPanel();
        JLabel l = new JLabel("Select an entry");

        MyMap<String, Integer> myMap = new MyMap<>();
        myMap.put("One", 1);
        myMap.put("Two", 2);
        myMap.put("Three", 3);
        myMap.put("Four", 4);
        myMap.put("Five", 5);

        DefaultListModel<MyMap.Entry<String, Integer>> listModel = myMap.getListModel();
        b = new JList<>(listModel);

        b.setSelectedIndex(2);
        p.add(b);
        f.add(p);
        f.setSize(400, 400);
        f.setVisible(true);
    }
}