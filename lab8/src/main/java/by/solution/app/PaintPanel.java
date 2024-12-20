package by.solution.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PaintPanel extends JPanel {
    private BufferedImage image;
    private Point lastPoint = null;

    private Color backgroundColor = Color.WHITE;
    private Color currentColor = Color.BLACK;

    public PaintPanel() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(backgroundColor);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastPoint != null) {
                    updatePanel(lastPoint, e.getPoint());
                }
                lastPoint = e.getPoint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
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
            g.drawImage(image, 0, 0, this);
        }
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        repaint();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void clear() {
        if (image != null) {
            Graphics imageGraphics = image.createGraphics();
            imageGraphics.setColor(getBackground());
            imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
            imageGraphics.dispose();

            Graphics g = getGraphics();
            if (g == null) {
                return;
            }

            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
            g.dispose();
        }
    }

    private void updatePanel(Point a, Point b) {
        if (image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        Graphics imageGraphics = image.createGraphics();
        imageGraphics.setColor(currentColor);
        imageGraphics.drawLine(a.x, a.y, b.x, b.y);
        imageGraphics.dispose();

        Graphics g = getGraphics();
        if (g == null) {
            return;
        }
        g.setColor(currentColor);
        g.drawLine(a.x, a.y, b.x, b.y);
        g.dispose();
    }

}
