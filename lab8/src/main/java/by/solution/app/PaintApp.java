package by.solution.app;

import by.solution.Pair;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


class PaintApp extends JFrame {
    public PaintApp() {
        setTitle("Рисование");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new DrawingPanel();
        JScrollPane scrollPane = new JScrollPane(drawingPanel);
        add(scrollPane, BorderLayout.CENTER);

        createMenuBar();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        JMenuItem openItem = new JMenuItem("Открыть");

        saveItem.addActionListener(e -> saveImage());
        openItem.addActionListener(e -> openImage());

        fileMenu.add(saveItem);
        fileMenu.add(openItem);

        JMenu penMenu = new JMenu("Перо");
        JMenu colorMenu = new JMenu("Цвет");
        JMenuItem blueColor = new JMenuItem("Синий");
        JMenuItem redColor = new JMenuItem("Красный");
        JMenuItem greenColor = new JMenuItem("Зеленый");
        JMenuItem blackColor = new JMenuItem("Чёрный");

        blueColor.addActionListener(e -> currentColor = Color.BLUE);
        redColor.addActionListener(e -> currentColor = Color.RED);
        greenColor.addActionListener(e -> currentColor = Color.GREEN);
        blackColor.addActionListener(e -> currentColor = Color.BLACK);

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
        BufferedImage image = new BufferedImage(drawingPanel.getWidth(), drawingPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        drawingPanel.paint(g2d);
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
            drawingPanel.setImage(image);
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

    public class DrawingPanel extends JPanel {
        private BufferedImage image;
        private ArrayList<Pair<Point, Color>> points = new ArrayList<>();

        public DrawingPanel() {
            setPreferredSize(new Dimension(800, 500));
            setBackground(backgroundColor);
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    points.add(new Pair<>(new Point(e.getX(), e.getY()), currentColor));
                    repaint();
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    points.add(new Pair<>(new Point(e.getX(), e.getY()), currentColor));
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, null);
            }

            for (Pair<Point, Color> p : points) {
                g.setColor(p.second);
                g.fillOval(p.first.x, p.first.y, 5, 5);
            }
        }

        public void setImage(BufferedImage img) {
            this.image = img;
            points.clear();
            repaint();
        }

        public void clearPoints() {
            points.clear();
        }
    }

    private DrawingPanel drawingPanel;
    private Color currentColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
}
