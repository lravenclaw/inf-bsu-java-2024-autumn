package by.solution;

import javax.swing.*;
import java.awt.*;

public class RadioButtonFrame extends JFrame {
    JRadioButton button1, button2, button3;
    ButtonGroup group;
    ImageIcon iconSelected, iconNotSelected, iconPressed, mouseOnIcon;
    RadioButtonFrame(){
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);
        setLayout(new FlowLayout());

        iconNotSelected = new ImageIcon("src/main/resources/error.png");
        iconSelected = new ImageIcon("src/main/resources/tick.png");
        iconPressed = new ImageIcon("src/main/resources/warning.png");
        mouseOnIcon = new ImageIcon("src/main/resources/sync.png");

        group = new ButtonGroup();

        button1 = new JRadioButton("1");
        group.add(button1);
        button1.setRolloverIcon(mouseOnIcon);
        button1.setSelectedIcon(iconSelected);
        button1.setIcon(iconNotSelected);
        button1.setPressedIcon(iconPressed);

        button2 = new JRadioButton("2");
        group.add(button2);
        button2.setRolloverIcon(mouseOnIcon);
        button2.setSelectedIcon(iconSelected);
        button2.setIcon(iconNotSelected);
        button2.setPressedIcon(iconPressed);

        button3 = new JRadioButton("3");
        group.add(button3);
        button3.setRolloverIcon(mouseOnIcon);
        button3.setSelectedIcon(iconSelected);
        button3.setIcon(iconNotSelected);
        button3.setPressedIcon(iconPressed);

        add(button1);
        add(button2);
        add(button3);
    }
}
