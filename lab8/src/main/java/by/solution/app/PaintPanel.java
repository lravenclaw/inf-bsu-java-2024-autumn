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
    private ArrayList<Pair<Point, Color>> points = new ArrayList<>();
    private Point lastPoint = null;

    private Color backgroundColor = Color.WHITE;
    private Color currentColor = Color.BLACK;

    public PaintPanel() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(backgroundColor);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentPoint = new Point(e.getX(), e.getY());
                if (lastPoint != null) {
                    points.add(new Pair<>(lastPoint, currentColor));
                    points.add(new Pair<>(currentPoint, currentColor));
                }
                lastPoint = currentPoint;
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastPoint = null;
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }

        for (int i = 1; i < points.size(); i += 2) {
            Point p1 = points.get(i - 1).first;
            Point p2 = points.get(i).first;
            g.setColor(points.get(i).second);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        points.clear();
        repaint();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void clear() {
        image = null;
        points.clear();
    }
}