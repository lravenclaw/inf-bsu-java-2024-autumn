package by.solution.strategy.app;

import by.solution.strategy.Strategy;
import by.solution.strategy.age.DefaultAgeStrategy;
import by.solution.strategy.age.StreamAPIAgeStrategy;
import by.solution.strategy.data.Toy;
import by.solution.strategy.parser.ToyParser;
import by.solution.strategy.price.DefaultPriceStrategy;
import by.solution.strategy.price.StreamAPIPriceStrategy;

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

    private Strategy<Toy> ageStrategy;
    private Strategy<Toy> priceStrategy;

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

        JMenu strategyMenu = new JMenu("Стратегия");

        JMenu ageStrategyMenu = new JMenu("Стратегия по возрасту");
        JMenuItem defaultAgeStrategyItem = new JMenuItem("DefaultAgeStrategy");
        defaultAgeStrategyItem.addActionListener(e -> ageStrategy = new DefaultAgeStrategy());
        ageStrategyMenu.add(defaultAgeStrategyItem);

        JMenuItem streamAPIAgeStrategyItem = new JMenuItem("StreamAPIAgeStrategy");
        streamAPIAgeStrategyItem.addActionListener(e -> ageStrategy = new StreamAPIAgeStrategy());
        ageStrategyMenu.add(streamAPIAgeStrategyItem);

        strategyMenu.add(ageStrategyMenu);

        JMenu priceStrategyMenu = new JMenu("Стратегия по цене");
        JMenuItem defaultPriceStrategyItem = new JMenuItem("DefaultPriceStrategy");
        defaultPriceStrategyItem.addActionListener(e -> priceStrategy = new DefaultPriceStrategy());
        priceStrategyMenu.add(defaultPriceStrategyItem);

        JMenuItem streamAPIPriceStrategyItem = new JMenuItem("StreamAPIPriceStrategy");
        streamAPIPriceStrategyItem.addActionListener(e -> priceStrategy = new StreamAPIPriceStrategy());
        priceStrategyMenu.add(streamAPIPriceStrategyItem);

        strategyMenu.add(priceStrategyMenu);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(taskMenu);
        menuBar.add(strategyMenu);
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

        ageStrategy = new StreamAPIAgeStrategy();
        priceStrategy = new DefaultPriceStrategy();
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
                toysAfter = ageStrategy.process(toys, Integer.parseInt(input));
                displayAreaAfter.setText(ToyParser.toString(toysAfter));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при чтении данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Нет данных для чтения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toysWithPrice() {
        String input = JOptionPane.showInputDialog(ToyManagerApp.this, "Введите cуммарную стоимость:");
        if (input != null && !input.isEmpty()) {
            try {
                toysAfter = priceStrategy.process(toys, Integer.parseInt(input));
                displayAreaAfter.setText(ToyParser.toString(toysAfter));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при чтении данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Нет данных для чтения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}