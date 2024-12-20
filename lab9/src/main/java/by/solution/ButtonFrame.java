package by.solution;

import javax.swing.*;
import java.awt.*;

public class ButtonFrame extends JFrame {
    ButtonFrame(){
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);

        var buttonDataMouseListener = new ButtonDataMouseListener();

        setLayout(new GridLayout(6, 4));
        for (int i = 1; i <= 24; i++){
            JButton button = new JButton(String.valueOf(i));
            button.setBackground(Color.WHITE);
            button.addMouseListener(buttonDataMouseListener);
            add(button);
        }
    }
}
