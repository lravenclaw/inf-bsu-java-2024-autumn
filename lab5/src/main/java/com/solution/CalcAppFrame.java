package com.solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CalcAppFrame extends JFrame implements ActionListener{
    JLabel originLabel, differenceLabel, resultLabel, amountLabel;
    JTextField originField, differenceField, amountField;
    JTextArea resultArea;
    JComboBox sequenceChooser;
    JButton confirmButton;
    JFileChooser fileChooser;

    public CalcAppFrame() {
        setTitle("Series");
        setLayout(new FlowLayout());

        originLabel = new JLabel("origin");
        this.add(originLabel);

        originField = new JTextField(8);
        this.add(originField);

        differenceLabel = new JLabel("difference");
        this.add(differenceLabel);

        differenceField = new JTextField(8);
        this.add(differenceField);

        amountLabel = new JLabel("amount of elements");
        this.add(amountLabel);

        amountField = new JTextField(8);
        this.add(amountField);

        String[] types = {"Linear", "Exponential"};
        sequenceChooser = new JComboBox(types);
        this.add(sequenceChooser);

        fileChooser = new JFileChooser();
        this.add(fileChooser);

        confirmButton = new JButton("Calculate");
        confirmButton.setSize(200, 50);
        this.add(confirmButton);
        confirmButton.addActionListener(this);

        resultArea = new JTextArea(" ");
        resultArea.setColumns(40);
        this.add(resultArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int type = sequenceChooser.getSelectedIndex();
        File file = fileChooser.getSelectedFile();

        int origin = Integer.parseInt(originField.getText());
        int difference = Integer.parseInt(differenceField.getText());
        int amount = Integer.parseInt(amountField.getText());
        String result;

        Series sequence;
        if (type == 0){
            sequence = new Linear(origin, difference);
        } else {
            sequence = new Exponential(origin, difference);
        }
        result = sequence.toString(amount);
        resultArea.setText(formatResultStr(result, 70));

        try {
            sequence.saveToFile(file, amount);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String formatResultStr(String str, int interval) {
        StringBuilder result = new StringBuilder(str);

        for (int i = interval; i < result.length(); i += interval + 1) {
            result.insert(i, '\n');
        }
        return result.toString();
    }
}