package by.piasecki.studentData.parser;

import by.piasecki.studentData.data.AbstractStudent;
import by.piasecki.studentData.data.MiddleSchoolStudent;
import by.piasecki.studentData.data.UniStudent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentParser {

    private static UniStudent parseUniStudent(String[] fields) {
        // Expected length: 6 (Include student type + 5 other fields)
        if (fields.length != 6) {
            throw new IllegalArgumentException("Invalid number of fields for UniStudent: " + fields.length);
        }

        return new UniStudent(
                fields[1].trim(),  // Full Name
                fields[2].trim(),  // School Name
                Integer.parseInt(fields[3].trim()),  // Average Mark
                Integer.parseInt(fields[4].trim()),  // School Score
                Integer.parseInt(fields[5].trim())   // Some other field for UniStudent
        );
    }

    private static MiddleSchoolStudent parseMiddleStudent(String[] fields) {
        // Expected length: 7 (Include student type + 6 other fields)
        if (fields.length != 7) {
            throw new IllegalArgumentException("Invalid number of fields for MiddleSchoolStudent: " + fields.length);
        }

        return new MiddleSchoolStudent(
                fields[1].trim(),      // Full Name
                fields[2].trim(),      // School Name
                Integer.parseInt(fields[3].trim()), // Average Mark
                Integer.parseInt(fields[4].trim()), // School Score
                Integer.parseInt(fields[5].trim()), // Some other field for MiddleSchool
                Integer.parseInt(fields[6].trim())  // Another field for MiddleSchool
        );
    }

    public static List<AbstractStudent> parseCSV(String filePath) {
        List<AbstractStudent> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // If the fields are empty, skip the line
                if (fields.length == 0) {
                    System.out.println("Skipping empty line");
                    continue;
                }

                String studentType = fields[0].trim();
                System.out.println("Processing student type: " + studentType);

                try {
                    switch (studentType.toLowerCase()) {
                        case "middle":

                            students.add(parseMiddleStudent(fields));
                            break;
                        case "uni":
                            students.add(parseUniStudent(fields));
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown student type: " + studentType);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Error processing student line: '" + line + "'. Message: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read from file: " + filePath);
            e.printStackTrace();
        }

        return students;
    }
}
