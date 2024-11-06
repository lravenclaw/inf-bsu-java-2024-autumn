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
import java.util.Optional;

public class AppFrame extends JFrame {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentCollection<AbstractStudent> studentCollection;

    public AppFrame() {
        setTitle("Student Management");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Table for displaying student data
        String[] columnNames = {"Type", "Full Name", "School Name", "Average Score", "School Score"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        Button loadButton = new Button("Load Students");
        Button sortedButton = new Button("Sorted Students");
        Button countButton = new Button("Count Students");
        Button searchButton = new Button("Search Student");
        Button filteringButton = new Button("Rating Ratio");
        Button lastNamesButton = new Button("Last Names starting with B");

        // Add buttons to panel
        buttonPanel.add(loadButton);
        buttonPanel.add(sortedButton);
        buttonPanel.add(countButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(filteringButton);
        buttonPanel.add(lastNamesButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        loadButton.addActionListener(new LoadButtonListener());
        sortedButton.addActionListener(new SortedButtonListener());
        countButton.addActionListener(new CountButtonListener());
        searchButton.addActionListener(new SearchButtonListener());
        filteringButton.addActionListener(new FilteringButtonListener());
        lastNamesButton.addActionListener(new LastNamesButtonListener());

        setVisible(true);
    }

    // Load students from CSV and populate table
    class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                List<AbstractStudent> students = StudentParser.parseCSV(file.getAbsolutePath());
                studentCollection = new StudentCollection<>(students);
                populateTable(students);
            }
        }
    }

    // Display sorted student data
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

    // Show count of students in a specified school
    class CountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            String schoolName = JOptionPane.showInputDialog("Enter school name:");
            long count = studentCollection.countStudentsInSchool(schoolName);
            JOptionPane.showMessageDialog(null, "Count of students in " + schoolName + ": " + count);
        }
    }

    // Search for a student by full name
    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            String studentName = JOptionPane.showInputDialog("Enter full name of the student:");
            Optional<AbstractStudent> result = studentCollection.binarySearch(new MiddleSchoolStudent(studentName, "", 0, 0, 0, 0)); // adapt according to your actual fields
            showResultsInTable(result.isPresent() ? List.of(result.get()) : List.of(), "Search Result");
        }
    }

    // Display students filtered by rating ratio
    class FilteringButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            List<AbstractStudent> filteredStudents = studentCollection.filterByRatingRatio();
            showResultsInTable(filteredStudents, "Filtered by Rating Ratio");
        }
    }

    // Display last names starting with 'B'
    class LastNamesButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentCollection == null) {
                JOptionPane.showMessageDialog(null, "Please load students first.");
                return;
            }
            List<String> lastNames = studentCollection.lastNamesStartingWith("B");
            showLastNamesInTable(lastNames, "Last Names Starting with B");
        }
    }

    private void populateTable(List<AbstractStudent> students) {
        tableModel.setRowCount(0); // Clear existing data
        for (AbstractStudent student : students) {
            String studentType = student instanceof MiddleSchoolStudent ? "Middle School" : "University";
            tableModel.addRow(new Object[]{studentType, student.getFullName(), student.getSchoolName(), student.getAverageMark(), student.getSchoolScore()});
        }
    }

    private void showResultsInTable(List<AbstractStudent> students, String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(600, 400);

        String[] columnNames = {"Type", "Full Name", "School Name", "Average Score", "School Score"};
        DefaultTableModel resultTableModel = new DefaultTableModel(columnNames, 0);

        JTable resultTable = new JTable(resultTableModel);

        for (AbstractStudent student : students) {
            String studentType = student instanceof MiddleSchoolStudent ? "Middle School" : "University";
            resultTableModel.addRow(new Object[]{studentType, student.getFullName(), student.getSchoolName(), student.getAverageMark(), student.getSchoolScore()});
        }

        dialog.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void showLastNamesInTable(List<String> lastNames, String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 300);

        DefaultTableModel lastNamesModel = new DefaultTableModel(new String[]{"Last Names"}, 0);
        JTable lastNamesTable = new JTable(lastNamesModel);

        for (String lastName : lastNames) {
            lastNamesModel.addRow(new Object[]{lastName});
        }

        dialog.add(new JScrollPane(lastNamesTable), BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}
