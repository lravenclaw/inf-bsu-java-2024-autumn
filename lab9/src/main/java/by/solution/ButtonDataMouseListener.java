package by.solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonDataMouseListener implements MouseListener {
    String prevValue;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        prevValue = button.getText();
        button.setText("Clicked!");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setText(prevValue);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Color.CYAN);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Color.WHITE);
    }
}
