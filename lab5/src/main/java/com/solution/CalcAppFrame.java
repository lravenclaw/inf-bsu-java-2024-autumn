package com.solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class CalcAppFrame extends JFrame implements ActionListener{
    JLabel startLabel, stepLabel, countLabel;
    JTextField startField, stepField, countField;
    JTextArea resultArea;
    JComboBox sequenceChooser;
    JButton confirmButton;

    public CalcAppFrame() {
        setTitle("Series");
        setLayout(new FlowLayout());

        startLabel = new JLabel("start");
        this.add(startLabel);

        startField = new JTextField(8);
        this.add(startField);

        stepLabel = new JLabel("step");
        this.add(stepLabel);

        stepField = new JTextField(8);
        this.add(stepField);

        countLabel = new JLabel("amount of elements");
        this.add(countLabel);

        countField = new JTextField(8);
        this.add(countField);


        String[] types = {"Linear", "Exponential"};
        sequenceChooser = new JComboBox(types);
        this.add(sequenceChooser);

        confirmButton = new JButton("Calculate");
        confirmButton.setSize(200, 50);
        this.add(confirmButton);
        confirmButton.addActionListener(this);

        resultArea = new JTextArea(" ");
        resultArea.setColumns(20);
        this.add(resultArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        File file = null;
        int type = sequenceChooser.getSelectedIndex();

        Integer start, step, count;
        String result;

        try {
            start = Integer.parseInt(startField.getText().trim());
            step = Integer.parseInt(stepField.getText().trim());
            count = Integer.parseInt(countField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ошибка ввода, неверные данные!");
            return;
        }

        Series sequence;
        if (type == 0){
            sequence = new Linear(start, step, count);
        } else {
            sequence = new Exponential(start, step, count);;
        }
        result = sequence.toString();
        resultArea.setText(formatResultStr(result, 70));

        try {
            JFileChooser fileChooser = new JFileChooser();
            int showOpenDialog = fileChooser.showOpenDialog(this);
            if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Ошибка выбора файла!");
            return;
        }

        try {
            sequence.saveToFile(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения в файл.");
            return;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Фйл не выбран!");
            return;
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