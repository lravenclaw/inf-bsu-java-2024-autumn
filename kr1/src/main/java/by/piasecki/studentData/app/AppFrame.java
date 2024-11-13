package by.piasecki.studentData.app;

import by.piasecki.studentData.data.*;
import by.piasecki.studentData.parser.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class AppFrame extends JFrame {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentCollection<AbstractStudent> studentCollection;

    public AppFrame() {
        setTitle("Student Management");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"Type", "Full Name", "School Name", "Average Score", "School Score"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load Students");
        JButton sortedButton = new JButton("Sorted Students");
        JButton countButton = new JButton("Count Students");
        JButton searchButton = new JButton("Search Student");
        JButton filteringButton = new JButton("Rating Ratio");
        JButton lastNamesButton = new JButton("Last Names starting with B");

        buttonPanel.add(loadButton);
        buttonPanel.add(sortedButton);
        buttonPanel.add(countButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(filteringButton);
        buttonPanel.add(lastNamesButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadButton.addActionListener(new LoadButtonListener());
        sortedButton.addActionListener(new SortedButtonListener());
        countButton.addActionListener(new CountButtonListener());
        searchButton.addActionListener(new SearchButtonListener());
        filteringButton.addActionListener(new FilteringButtonListener());
        lastNamesButton.addActionListener(new LastNamesButtonListener());

        setVisible(true);
    }

    class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (!file.getName().toLowerCase().endsWith(".csv")) {
                    JOptionPane.showMessageDialog(null, "Error: Please select a valid CSV file.", "Invalid File", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<AbstractStudent> students = StudentParser.parseCSV(file.getAbsolutePath());
                studentCollection = new StudentCollection<>(students);
                populateTable(students);
            }
        }
    }


    class SortedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            List<AbstractStudent> sortedStudents = studentCollection.sortedBySchoolAndLastName();
            showResultsInTable(sortedStudents, "Sorted Students");
        }
    }

    class CountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            int count = studentCollection.size();
            JOptionPane.showMessageDialog(null, "Total Students: " + count);
        }
    }

    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = JOptionPane.showInputDialog("Enter student name to search:");
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                List<AbstractStudent> foundStudents = studentCollection.searchByName(searchTerm);
                showResultsInTable(foundStudents, "Search Results");
            }
        }
    }

    class FilteringButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            double ratioThreshold = Double.parseDouble(JOptionPane.showInputDialog("Enter minimum rating ratio:"));
            List<AbstractStudent> filteredStudents = studentCollection.filterByRatingRatio(ratioThreshold);
            showResultsInTable(filteredStudents, "Filtered Students");
        }
    }

    class LastNamesButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            String input = JOptionPane.showInputDialog("Enter the starting letter for last names:");
            if (input != null && !input.trim().isEmpty() && input.length() == 1) {
                List<AbstractStudent> students = studentCollection.getLastNamesStartingWith(input.toString());
                if (students.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No last names found starting with '" + input + "'.");
                } else {
                    JOptionPane.showMessageDialog(null, "Last Names starting with '" + input + "':\n" + String.join(", "));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid single character.");
            }
        }
    }


    private void populateTable(List<AbstractStudent> students) {
        tableModel.setRowCount(0); // Clear existing rows
        for (AbstractStudent student : students) {
            tableModel.addRow(new Object[]{
                    student.getType(),
                    student.getFullName(),
                    student.getSchoolName(),
                    student.getAverageMark(),
                    student.getSchoolScore()
            });
        }
    }

    private void showResultsInTable(List<AbstractStudent> students, String title) {
        populateTable(students);
        JOptionPane.showMessageDialog(this, title, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
