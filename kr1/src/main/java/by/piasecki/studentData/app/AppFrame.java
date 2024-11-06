package by.piasecki.studentData.app;

import by.piasecki.studentData.data.StudentData;
import by.piasecki.studentData.parser.StudentDataReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

import by.piasecki.studentData.data.StudentData; // Adjust the package according to your structure
import by.piasecki.studentData.parser.StudentDataReader; // Adjust the package according to your structure

public class AppFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton loadButton;
    private JButton statsButton;

    public AppFrame() {
        setTitle("Student Data Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the main table with columns for subjects
        String[] columnNames = {"Full Name", "Marks"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Load and Stats buttons
        loadButton = new JButton("Load Data");
        loadButton.addActionListener(new LoadDataButtonListener());

        statsButton = new JButton("Calculate Stats");
        statsButton.addActionListener(new StatsButtonListener());

        // Layout setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(statsButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class LoadDataButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileChooser.getSelectedFile();
                loadDataFromFile(file.getAbsolutePath());
            }
        }
    }

    private void loadDataFromFile(String filePath) {
        tableModel.setRowCount(0); // Clear existing data
        try {
            List<StudentData> students = StudentDataReader.readStudentsFromFile(filePath);
            for (StudentData student : students) {
                StringBuilder marksBuilder = new StringBuilder();
                for (Map.Entry<String, Integer> entry : student.getMarks().entrySet()) {
                    marksBuilder.append(entry.getKey()).append(":").append(entry.getValue()).append(", ");
                }
                // Remove trailing comma and space
                if (marksBuilder.length() > 0) {
                    marksBuilder.setLength(marksBuilder.length() - 2);
                }
                tableModel.addRow(new Object[]{student.getFullName(), marksBuilder.toString()});
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage());
        }
    }

    private class StatsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a new JFrame for statistics
            JFrame statsFrame = new JFrame("Student Statistics");
            statsFrame.setSize(500, 300);
            statsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow closing

            // Create a table for displaying statistics
            String[] statsColumnNames = {"Student", "Average Score", "Max Score", "Min Score"};
            DefaultTableModel statsModel = new DefaultTableModel(statsColumnNames, 0);
            JTable statsTable = new JTable(statsModel);

            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String fullName = (String) tableModel.getValueAt(i, 0);
                String marksString = (String) tableModel.getValueAt(i, 1);
                String[] marksArray = marksString.split(", ");
                double totalScore = 0;
                double maxScore = Double.MIN_VALUE;
                double minScore = Double.MAX_VALUE;
                int count = 0;

                for (String mark : marksArray) {
                    String[] subjectPart = mark.split(":");
                    if (subjectPart.length == 2) {
                        try {
                            Integer score = Integer.parseInt(subjectPart[1].trim());
                            totalScore += score;
                            maxScore = Math.max(maxScore, score);
                            minScore = Math.min(minScore, score);
                            count++;
                        } catch (NumberFormatException e1) {
                            // Handle invalid integer conversion
                        }
                    }
                }

                if (count > 0) {
                    double averageScore = totalScore / count;
                    statsModel.addRow(new Object[]{fullName, averageScore, maxScore, minScore});
                }
            }

            // Add to statsFrame
            statsFrame.add(new JScrollPane(statsTable), BorderLayout.CENTER);
            statsFrame.setVisible(true); // Show the statsFrame
        }
    }
}
