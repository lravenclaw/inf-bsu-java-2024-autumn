package by.solution.app;

import by.solution.data.Toy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ToyManager extends JFrame {
    private JTextArea displayArea;
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

        menuBar.add(fileMenu);
        menuBar.add(sortMenu);
        setJMenuBar(menuBar);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
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
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            toys.clear();
            StringBuilder displayText = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Toy toy = new Toy(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
                toys.add(toy);
                displayText.append(toy.toString()).append("\n");
            }
            displayArea.setText(displayText.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при чтении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortToys(Comparator<Toy> comparator) {
        toys.sort(comparator);
        StringBuilder displayText = new StringBuilder();
        for (Toy toy : toys) {
            displayText.append(toy.toString()).append("\n");
        }
        displayArea.setText(displayText.toString());
    }
}