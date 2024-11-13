package by.solution.part2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HahaApp extends JFrame {
    public HahaApp() {
        setTitle("Вопрос");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        questionLabel = new JLabel("Радует ли Вас размер стипендии?");
        add(questionLabel);

        comfortingButton = new JButton("радует =)");
        comfortingButton.addActionListener(e -> showComfortingMessage());
        add(comfortingButton);

        fleeingButton = new JButton("к сожалению, нет");
        fleeingButton.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                moveFleeingButton();
            }
        });
        add(fleeingButton);

        setVisible(true);
    }

    private void showComfortingMessage() {
        JOptionPane.showMessageDialog(this, "Good job!");
    }

    private void moveFleeingButton() {
        Dimension size = getSize();
        int x = (int) (Math.random() * (size.width - fleeingButton.getWidth()));
        int y = (int) (Math.random() * (size.height - fleeingButton.getHeight() - 50));
        fleeingButton.setLocation(x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HahaApp::new);
    }

    private JLabel questionLabel;
    private JButton comfortingButton;
    private JButton fleeingButton;
}

