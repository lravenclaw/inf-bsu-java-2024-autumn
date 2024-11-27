package by.solution;

import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame {

    JButton button;
    JLabel mouseCoords;
    boolean ctrlPressed = false;
    MyFrame(){
        setSize(600, 400);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        button = new JButton("Click");
        button.setSize(100, 50);
        add(button);

        mouseCoords = new JLabel("aaa");
        add(mouseCoords);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseCoords.setText("x: " + e.getLocationOnScreen().x + " y: " + e.getLocationOnScreen().y);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseCoords.setText("x: " + e.getLocationOnScreen().x + " y: " + e.getLocationOnScreen().y);
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setLocation(e.getLocationOnScreen().x - 6 - 50, e.getLocationOnScreen().y - 30 - 25);
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

        button.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 17){
                    ctrlPressed = true;
                } else if(e.getKeyCode() == 8){
                    String text = button.getText();
                    if (text.length() > 0){
                        text = text.substring(0, text.length() - 1);
                        button.setText(text);
                    }
                }else{
                    String text = button.getText();
                    text = String.valueOf(text + e.getKeyChar());
                    button.setText(text);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 17){
                    ctrlPressed = false;
                }
            }
        });

        button.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (ctrlPressed) {
                    button.setLocation(e.getLocationOnScreen().x - 5, e.getLocationOnScreen().y - 35);
                }
                mouseCoords.setText("x: " + e.getLocationOnScreen().x + " y: " + e.getLocationOnScreen().y);
            }

            @Override
            public void mouseMoved(MouseEvent me) {

            }
        });
    }
}
