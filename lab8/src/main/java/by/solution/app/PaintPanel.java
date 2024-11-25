package by.solution.app;

import by.solution.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PaintPanel extends JPanel {
    private BufferedImage image;
    private Pair<Point, Color> currentPoint = null;
    private ArrayList<Pair<Point, Color>> points = new ArrayList<>();

    private Color backgroundColor = Color.WHITE;
    private Color currentColor = Color.BLACK;

    public PaintPanel() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(backgroundColor);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentPoint = new Pair<>(new Point(e.getX(), e.getY()), currentColor);
                updatePanel();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentPoint = new Pair<>(new Point(e.getX(), e.getY()), currentColor);
                updatePanel();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);  // Увеличиваем изображение до размеров панели
        }

        for (var p : points) {
            g.setColor(p.second);
            g.fillOval(p.first.x, p.first.y, 5, 5);
            g.drawOval(p.first.x, p.first.y, 5, 5);
        }
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        points.clear();
        updatePanel();
    }

    private void updatePanel() {
        Graphics g = getGraphics();
        if (g == null) {
            return;
        }

        g.setColor(currentPoint.second);
        g.fillOval(currentPoint.first.x, currentPoint.first.y, 5, 5);
        g.drawOval(currentPoint.first.x, currentPoint.first.y, 5, 5);
        points.add(currentPoint);
        //g.dispose();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }
}