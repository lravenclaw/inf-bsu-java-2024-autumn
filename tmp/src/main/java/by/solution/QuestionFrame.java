package by.solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuestionFrame extends JFrame {
    JLabel question, answer;
    JButton yesButton, noButton;
    QuestionFrame(){
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        question = new JLabel("Are you satisfied with scholarship amount?");
        add(question);

        yesButton = new JButton("Yes");
        yesButton.setSize(100, 50);
        add(yesButton);

        noButton = new JButton("No");
        noButton.setSize(100, 50);
        add(noButton);

        answer = new JLabel("");
        add(answer);

        yesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                answer.setText("Cool");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        noButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                noButton.setLocation((int) Math.floor(Math.random() * 600), (int) Math.floor(Math.random() * 400));
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
