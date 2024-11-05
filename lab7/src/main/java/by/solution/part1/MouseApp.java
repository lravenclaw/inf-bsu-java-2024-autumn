package by.solution.part1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseApp extends JFrame {
    private JButton btn;
    private JLabel infoLabel;
    private Point mouseOffset;

    public MouseApp() {
        setTitle("Приложение с мышью");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        btn = new JButton("Нажми меня");
        btn.setBounds(150, 125, 100, 50);
        add(btn);

        infoLabel = new JLabel("Координаты: (0, 0)");
        infoLabel.setBounds(10, 230, 200, 20);
        add(infoLabel);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updateLabel(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                updateLabel(e.getXOnScreen(), e.getYOnScreen());
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - btn.getWidth() / 2;
                int y = e.getY() - btn.getHeight() / 2;

                if (x < 0) x = 0;
                if (y < 0) y = 0;
                if (x + btn.getWidth() > getWidth()) x = getWidth() - btn.getWidth();
                if (y + btn.getHeight() > getHeight()) y = getHeight() - btn.getHeight();

                btn.setLocation(x, y);
            }
        });

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    mouseOffset = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseOffset = null;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String input = JOptionPane.showInputDialog(MouseApp.this, "Введите текст для кнопки:", btn.getText());
                    if (input != null && !input.isEmpty()) {
                        btn.setText(input);
                    }
                }
            }
        });

        btn.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseOffset != null) {
                    int x = btn.getX() + e.getX() - mouseOffset.x;
                    int y = btn.getY() + e.getY() - mouseOffset.y;
                    btn.setLocation(x, y);
                }
                updateLabel(e.getXOnScreen(), e.getYOnScreen());
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                btn.setText(btn.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    String text = btn.getText();
                    if (text.length() > 0) {
                        btn.setText(text.substring(0, text.length() - 1));
                    }
                }
            }
        });

        setFocusable(true);
        setVisible(true);
    }

    private void updateLabel(int x, int y) {
        infoLabel.setText("Координаты: (" + x + ", " + y + ")");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MouseApp::new);
    }
}
