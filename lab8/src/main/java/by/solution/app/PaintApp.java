package by.solution.app;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;


class PaintApp extends JFrame {
    public PaintApp() {
        setTitle("Рисование");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        paintPanel = new PaintPanel();
        paintPanel.setBackgroundColor(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(paintPanel);
        add(scrollPane, BorderLayout.CENTER);

        createMenuBar();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paintPanel.repaint();
            }
        });

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        JMenuItem openItem = new JMenuItem("Открыть");
        JMenuItem clearItem = new JMenuItem("Oчистить");

        saveItem.addActionListener(e -> saveImage());
        openItem.addActionListener(e -> openImage());
        clearItem.addActionListener(e ->  {paintPanel.clear(); paintPanel.repaint();});

        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.add(clearItem);

        JMenu penMenu = new JMenu("Перо");
        JMenu colorMenu = new JMenu("Цвет");
        JMenuItem blueColor = new JMenuItem("Синий");
        JMenuItem redColor = new JMenuItem("Красный");
        JMenuItem greenColor = new JMenuItem("Зеленый");
        JMenuItem blackColor = new JMenuItem("Чёрный");

        blueColor.addActionListener(e -> paintPanel.setCurrentColor(Color.BLUE));
        redColor.addActionListener(e -> paintPanel.setCurrentColor(Color.RED));
        greenColor.addActionListener(e -> paintPanel.setCurrentColor(Color.GREEN));
        blackColor.addActionListener(e -> paintPanel.setCurrentColor(Color.BLACK));

        colorMenu.add(blueColor);
        colorMenu.add(redColor);
        colorMenu.add(greenColor);
        colorMenu.add(blackColor);

        penMenu.add(colorMenu);
        menuBar.add(fileMenu);
        menuBar.add(penMenu);

        setJMenuBar(menuBar);
    }

    private void saveImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();
        BufferedImage image = new BufferedImage(paintPanel.getWidth(), paintPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        paintPanel.paint(g2d);
        g2d.dispose();

        try {
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(this, "Изображение сохранено!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения изображения: " + e.getMessage());
        }
    }

    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        if (!validateFile(file)) {
            JOptionPane.showMessageDialog(this, "Ошибка открытия изображения: " + "неверный формат");
            return;
        }

        try {
            BufferedImage image = ImageIO.read(file);
            paintPanel.setImage(image);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка открытия изображения: " + ex.getMessage());
        }
    }

    private boolean validateFile(File file) {
        String extension = "";

        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            extension = file.getName().substring(i+1);
            if (!extension.equals("png")) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private PaintPanel paintPanel;
    private Color currentColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
}
