package by.solution.app;

import by.solution.data.Toy;
import by.solution.parser.ToyParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ToyManagerApp extends JFrame {
    private JTextArea displayAreaBefore;
    private JTextArea displayAreaAfter;
    private List<Toy> toys;
    private List<Toy> toysAfter;

    public ToyManagerApp() {
        setTitle("Toy Manager");
        setSize(800, 1000);
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

        JMenu fontMenu = new JMenu("Шрифт");
        JMenu changeFontSize = new JMenu("Изменить размер шрифта");
        fontMenu.add(changeFontSize);

        String[] fontSizes = {"12", "16", "20", "24", "28", "32"};
        for (String size : fontSizes) {
            JMenuItem menuItem = new JMenuItem(size);
            menuItem.addActionListener(new FontSizeActionListener(Integer.parseInt(size)));
            changeFontSize.add(menuItem);
        }
        viewMenu.add(fontMenu);

        JMenu taskMenu = new JMenu("Задание");
        JMenuItem taskGetToysFromAge = new JMenuItem("Cписок игрушек по возрасту");
        taskGetToysFromAge.addActionListener((e) -> toysFromAge());
        taskMenu.add(taskGetToysFromAge);
        JMenuItem taskGetListsWithTotalSum = new JMenuItem("Списки игрушек по заданной сумме");
        taskGetListsWithTotalSum.addActionListener((e) -> toysWithPrice());
        taskMenu.add(taskGetListsWithTotalSum);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(taskMenu);
        setJMenuBar(menuBar);

        setLayout(new GridLayout(1, 2));

        displayAreaBefore = new JTextArea();
        displayAreaBefore.setEditable(false);
        displayAreaAfter = new JTextArea();
        displayAreaAfter.setEditable(false);

        displayAreaBefore.setLineWrap(true);
        displayAreaBefore.setWrapStyleWord(true);
        displayAreaAfter.setLineWrap(true);
        displayAreaAfter.setWrapStyleWord(true);

        add(new JScrollPane(displayAreaBefore), BorderLayout.WEST);
        add(new JScrollPane(displayAreaAfter), BorderLayout.EAST);
    }

    private class FontSizeActionListener implements ActionListener {
        private final int fontSize;

        public FontSizeActionListener(int fontSize) {
            this.fontSize = fontSize;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Font font = new Font("Monospaced", Font.PLAIN, fontSize);
            displayAreaBefore.setFont(font);
            displayAreaAfter.setFont(font);
        }
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
            toysAfter = toys;
            displayAreaBefore.setText(ToyParser.toString(toys));
            displayAreaAfter.setText(displayAreaBefore.getText());
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при чтении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortToys(Comparator<Toy> comparator) {
        toysAfter.sort(comparator);
        displayAreaAfter.setText(ToyParser.toString(toysAfter));
    }

    private void toysFromAge() {
        String input = JOptionPane.showInputDialog(ToyManagerApp.this, "Введите возраст:");
        if (input != null && !input.isEmpty()) {
            try {
                toysAfter = getToysFromAge(toys, Integer.parseInt(input));
                displayAreaAfter.setText(ToyParser.toString(toysAfter));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при чтении данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Нет данных для чтения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static List<Toy> getToysFromAgeStreamAPI(List<Toy> toys, int age) {
        return toys.stream()
                .filter(toy -> age >= toy.ageLimit)
                .collect(Collectors.toList());
    }

    private static List<Toy> getToysFromAge(List<Toy> toys, int age) {
        ArrayList<Toy> result = new ArrayList<>();
        for (var toy : toys) {
            if (age >= toy.ageLimit) {
                result.add(toy);
            }
        }
        return result;
    }

    private void toysWithPrice() {
        String input = JOptionPane.showInputDialog(ToyManagerApp.this, "Введите cуммарную стоимость:");
        if (input != null && !input.isEmpty()) {
            try {
                toysAfter = getToysWithTotalPrice(toys, Double.parseDouble(input));
                displayAreaAfter.setText(ToyParser.toString(toysAfter));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при чтении данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Нет данных для чтения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static List<Toy> getToysWithTotalPriceStreamAPI(List<Toy> toys, double totalSum) {
        List<Toy> result = new ArrayList<>();
        double[] currentSum = {0.0};

        toys.stream()
                .sorted(Comparator.comparingDouble(toy -> toy.price))
                .takeWhile(toy -> {
                    if (currentSum[0] + toy.price <= totalSum) {
                        currentSum[0] += toy.price;
                        result.add(toy);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return result;
    }

    private static List<Toy> getToysWithTotalPrice(List<Toy> toys, double totalSum) {
        ArrayList<Toy> result = new ArrayList<>();

        TreeSet<Integer> used = new TreeSet<>();
        double currentSum = 0.;
        boolean full = false;
        Random random = new Random();

        while (!(full ||toys.isEmpty())) {
            Integer index = random.nextInt(toys.size());
            if (used.contains(index)) {
                continue;
            }

            var currentToy = toys.get(index);
            if (currentSum + currentToy.price - totalSum >= 1e-6) {
                full = true;
                continue;
            }

            used.add(index);
            result.add(currentToy);
            currentSum += currentToy.price;
        }
        return result;
    }

}