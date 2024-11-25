package by.solution.part1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseApp extends JFrame {
    private JButton button;
    private JLabel infoLabel;
    private Point mouseOffset;

    public MouseApp() {
        setTitle("Приложение с мышью");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);


        button = new JButton("F");
        button.setBounds(150, 125, 100, 50);
        add(button);

        infoLabel = new JLabel("Координаты: (0, 0)");
        infoLabel.setSize(200,20);
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
                int x = e.getX() - button.getWidth() / 2;
                int y = e.getY() - button.getHeight() / 2;

                if (x < 0) x = 0;
                if (y < 0) y = 0;
                if (x + button.getWidth() > getWidth()) x = getWidth() - button.getWidth();
                if (y + button.getHeight() > getHeight()) y = getHeight() - button.getHeight();

                button.setLocation(x, y);
            }
        });


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    mouseOffset = e.getPoint();
                }
                button.requestFocusInWindow();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseOffset = null;
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseOffset != null && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
                    int x = button.getX() + e.getX() - mouseOffset.x;
                    int y = button.getY() + e.getY() - mouseOffset.y;
                    button.setLocation(x, y);
                }
                updateLabel(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                updateLabel(e.getXOnScreen(), e.getYOnScreen());
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    String text = button.getText();
                    if (!text.isEmpty()){
                        text = text.substring(0, text.length() - 1);
                        button.setText(text);
                    }
                } else if (Character.isLetterOrDigit(e.getKeyChar()) || e.getKeyChar() == ' ') {
                    button.setText(button.getText() + e.getKeyChar());
                }
            }
        });

        button.setFocusable(false);
        requestFocusInWindow();
        setVisible(true);
    }

    private void updateLabel(int x, int y) {
        infoLabel.setText("Координаты: (" + x + ", " + y + ")");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MouseApp::new);
    }
}
