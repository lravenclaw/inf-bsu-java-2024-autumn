package by.solution.strategy.app;

import by.solution.strategy.ProcessStrategy;
import by.solution.strategy.age.DefaultAgeStrategy;
import by.solution.strategy.age.StreamAPIAgeStrategy;
import by.solution.strategy.data.Toy;
import by.solution.strategy.io.*;
import by.solution.strategy.price.DefaultPriceStrategy;
import by.solution.strategy.price.StreamAPIPriceStrategy;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class ToyManagerApp extends JFrame {
    private JTextArea displayAreaBefore;
    private JTextArea displayAreaAfter;
    private List<Toy> toys;
    private List<Toy> toysAfter;

    private ProcessStrategy<Toy> ageStrategy;
    private ProcessStrategy<Toy> priceStrategy;

    private ReaderStrategy readerStrategy;

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

        JMenuItem saveItem = new JMenuItem("Сохранить итоговый файл");
        saveItem.addActionListener(e -> saveFile());
        fileMenu.add(saveItem);

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

        JMenu strategyAge = new JMenu("Стратегия по возрасту");
        strategyMenu.add(strategyAge);
        JMenu strategyPrice = new JMenu("Стратегия по цене");
        strategyMenu.add(strategyPrice);
        JMenu strategyXML = new JMenu("XML");
        strategyMenu.add(strategyXML);

        JMenuItem strategyDeafaultAge = new JMenuItem("DefaultAgeStrategy");
        strategyDeafaultAge.addActionListener(e -> ageStrategy = new DefaultAgeStrategy());
        strategyAge.add(strategyDeafaultAge);

        JMenuItem strategyStreamAPIAge = new JMenuItem("StreamAPIAgeStrategy");
        strategyStreamAPIAge.addActionListener(e -> ageStrategy = new StreamAPIAgeStrategy());
        strategyAge.add(strategyStreamAPIAge);

        JMenuItem strategyDefaultPrice = new JMenuItem("DefaultPriceStrategy");
        strategyDefaultPrice.addActionListener(e -> priceStrategy = new DefaultPriceStrategy());
        strategyPrice.add(strategyDefaultPrice);

        JMenuItem strategyStreamAPIPrice = new JMenuItem("StreamAPIPriceStrategy");
        strategyStreamAPIPrice.addActionListener(e -> priceStrategy = new StreamAPIPriceStrategy());
        strategyPrice.add(strategyStreamAPIPrice);

        JMenuItem strategyDomReader = new JMenuItem("DomReader");
        strategyDomReader.addActionListener(e -> readerStrategy = new DomReader());
        strategyXML.add(strategyDomReader);

        JMenuItem strategySaxReader = new JMenuItem("SaxReader");
        strategySaxReader.addActionListener(e -> readerStrategy = new SaxReader());
        strategyXML.add(strategySaxReader);



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
        priceStrategy = new StreamAPIPriceStrategy();
        readerStrategy = new DomReader();
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
            try {
                toys = readerStrategy.read(selectedFile);
                toysAfter = toys;
                displayAreaBefore.setText(ToyUtils.toString(toys));
                displayAreaAfter.setText(ToyUtils.toString(toysAfter));
            } catch (IOException | ParserConfigurationException | SAXException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при чтении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                XmlWriter.write(toysAfter, selectedFile);
                JOptionPane.showMessageDialog(this, "Файл успешно сохранен", "Успех", JOptionPane.INFORMATION_MESSAGE);
            } catch (ParserConfigurationException | TransformerException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при сохранении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void sortToys(Comparator<Toy> comparator) {
        toysAfter.sort(comparator);
        displayAreaAfter.setText(ToyUtils.toString(toysAfter));
    }

    private void toysFromAge() {
        String input = JOptionPane.showInputDialog(ToyManagerApp.this, "Введите возраст:");
        if (input != null && !input.isEmpty()) {
            try {
                toysAfter = ageStrategy.process(toys, Integer.parseInt(input));
                displayAreaAfter.setText(ToyUtils.toString(toysAfter));
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
                displayAreaAfter.setText(ToyUtils.toString(toysAfter));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при чтении данных", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Нет данных для чтения", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}