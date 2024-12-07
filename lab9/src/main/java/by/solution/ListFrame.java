package by.solution;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListFrame extends JFrame {
    JList<String> list1, list2;
    JButton leftToRight, rightToLeft;
    DefaultListModel<String> data1, data2;

    ListFrame(List<String> $list1, List<String> $list2) {
        setTitle("List Transfer");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Инициализация списков
        data1 = new DefaultListModel<>();
        $list1.forEach(data1::addElement);
        data2 = new DefaultListModel<>();
        $list2.forEach(data2::addElement);

        list1 = new JList<>(data1);
        list2 = new JList<>(data2);
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


        leftToRight = new JButton(">");
        rightToLeft = new JButton("<");

        leftToRight.addActionListener(e -> {
            List<String> selectedValues = list1.getSelectedValuesList();
            for (String value : selectedValues) {
                data1.removeElement(value);
                data2.addElement(value);
            }
        });

        rightToLeft.addActionListener(e -> {
            List<String> selectedValues = list2.getSelectedValuesList();
            for (String value : selectedValues) {
                data2.removeElement(value);
                data1.addElement(value);
            }
        });

        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(list1), BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(200, 0));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(list2), BorderLayout.CENTER);
        rightPanel.setPreferredSize(new Dimension(200, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10));

        buttonPanel.add(leftToRight);

        buttonPanel.add(Box.createVerticalStrut(10));

        buttonPanel.add(rightToLeft);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(false);
    }
}