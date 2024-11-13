package by.solution.app;

import by.solution.data.Toy;
import by.solution.parser.ToyParser;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ToyManager extends JFrame {
    private JTextArea displayAreaBefore;
    private JTextArea displayAreaAfter;
    private List<Toy> toys;

    public ToyManager() {
        setTitle("Toy Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toys = new ArrayList<>();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");

        JMenuItem openItem = new JMenuItem("Открыть файл");
        openItem.addActionListener(e -> openFile());
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu viewMenu = new JMenu("Вид");

        JMenu sortMenu = new JMenu("Сортировка");
        JMenuItem sortByPriceItem = new JMenuItem("По цене");
        sortByPriceItem.addActionListener(e -> sortToys(Toy.byPrice));
        sortMenu.add(sortByPriceItem);

        JMenuItem sortByAgeLimitItem = new JMenuItem("По возрасту");
        sortByAgeLimitItem.addActionListener(e -> sortToys(Toy.byAgeLimit));
        sortMenu.add(sortByAgeLimitItem);

        JMenuItem sortByNameItem = new JMenuItem("По имени");
        sortByNameItem.addActionListener(e -> sortToys(Toy.byName));
        sortMenu.add(sortByNameItem);

        viewMenu.add(sortMenu);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);

        displayAreaBefore = new JTextArea();
        displayAreaBefore.setEditable(false);
        displayAreaBefore.

        displayAreaAfter = new JTextArea();
        displayAreaAfter.setEditable(false);

        add(new JScrollPane(displayAreaBefore), BorderLayout.CENTER);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            readToysFromFile(selectedFile);
        }
    }

    private void readToysFromFile(File file) {
        try {
            toys = ToyParser.readToysFromFile(file);
            StringBuilder displayText = new StringBuilder();
            displayAreaBefore.setText(displayText.toString());
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при чтении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortToys(Comparator<Toy> comparator) {
        toys.sort(comparator);
        StringBuilder displayText = new StringBuilder();
        for (Toy toy : toys) {
            displayText.append(toy.toString()).append("\n");
        }
        displayAreaBefore.setText(displayText.toString());
    }
}