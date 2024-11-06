package by.piasecki.studentData.parser;

import by.piasecki.studentData.data.StudentData;

import java.util.*;
import java.io.*;

public class StudentDataReader {

    public static List<StudentData> readStudentsFromFile(String filePath) throws IOException {
        List<StudentData> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                String fullName = parts[0].trim();
                Map<String, Integer> marks = new HashMap<>();

                for (int i = 1; i < parts.length; i++) {
                    String[] subjectPart = parts[i].split(":");
                    if (subjectPart.length == 2) {
                        String subject = subjectPart[0].trim();
                        try {
                            Integer mark = Integer.parseInt(subjectPart[1].trim());
                            marks.put(subject, mark);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid score for subject " + subject + ": " + subjectPart[1]);
                        }
                    }
                }
                students.add(new StudentData(fullName, marks));
            }
        }
        return students;
    }
}
