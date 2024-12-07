package by.solution;

import javax.swing.*;
import java.awt.*;

public class RadioButtonFrame extends JFrame {
    JRadioButton button1, button2, button3;
    ButtonGroup group;
    ImageIcon iconSelected, iconNotSelected, iconPressed, mouseOnIcon;

    RadioButtonFrame() {
        setLayout(new FlowLayout());

        iconNotSelected = new ImageIcon("src/main/resources/error.png");
        iconSelected = new ImageIcon("src/main/resources/tick.png");
        iconPressed = new ImageIcon("src/main/resources/warning.png");
        mouseOnIcon = new ImageIcon("src/main/resources/sync.png");

        group = new ButtonGroup();

        button1 = createRadioButton("1");
        button2 = createRadioButton("2");
        button3 = createRadioButton("3");

        group.add(button1);
        group.add(button2);
        group.add(button3);

        add(button1);
        add(button2);
        add(button3);
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton button = new JRadioButton(text);
        button.setRolloverIcon(mouseOnIcon);
        button.setSelectedIcon(iconSelected);
        button.setIcon(iconNotSelected);
        button.setPressedIcon(iconPressed);
        button.setIconTextGap(10);
        button.setPreferredSize(new Dimension(
                iconSelected.getIconWidth() + 50,
                iconSelected.getIconHeight() + 20
        ));
        return button;
    }
}