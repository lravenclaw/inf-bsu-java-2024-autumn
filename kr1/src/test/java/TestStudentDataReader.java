import java.io.IOException;

import by.piasecki.studentData.data.*;
import by.piasecki.studentData.parser.*;
import by.piasecki.studentData.app.*;
import java.util.*;

public class TestStudentDataReader {
    public static void main(String[] args) {
        try {
            List<StudentData> students = StudentDataReader.readStudentsFromFile("src/main/resources/input.csv");
            for (StudentData student : students) {
                System.out.println(student); // This will display average scores
            }
        } catch (IOException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }
    }
}
