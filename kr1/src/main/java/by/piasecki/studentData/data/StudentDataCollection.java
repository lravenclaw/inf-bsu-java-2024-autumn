package by.piasecki.studentData.data;

import java.util.*;

public class StudentDataCollection<T extends AbsractStudentData> {
    private List<T> students;

    public StudentDataCollection() {
        this.students = new ArrayList<>();
    }

    public void addStudent(T student) {
        if (student != null) {
            students.add(student);
        }
    }

    public double getClassAverageScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        return students.stream()
                .mapToDouble(T::getAverageScore)
                .average()
                .orElse(0.0);
    }

    public T getTopStudent() {
        return students.stream()
                .max(Comparator.comparingInt(T::getTotalScore))
                .orElse(null); // Returns null if no students are present
    }

    public void displayAllStudents() {
        students.forEach(System.out::println);
    }
}
