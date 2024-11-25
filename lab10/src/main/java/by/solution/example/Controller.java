package by.solution.example;

import by.solution.pair.PairElementContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JPanel {
    Controller(PairElementContainer<String, String> model, SetView view){
        setLayout(new FlowLayout());

        JButton addElement = new JButton("Add element");
        add(addElement);

        JTextField elementToAdd = new JTextField(5);
        add(elementToAdd);

        addElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addElement){
                    model.put(elementToAdd.getText(), "value");
                    view.update();
                }
            }
        });

//        JButton popElement = new JButton("Pop Element");
//        add(popElement);
//        popElement.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == popElement){
//                    model.pop();
//                    view.update();
//                }
//            }
//        });
    }
}
