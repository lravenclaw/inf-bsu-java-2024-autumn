package by.piasecki.studentData.data;

import lombok.*;

import java.util.Map;

public class StudentData extends AbsractStudentData {
    public StudentData(String fullName, Map<String, Integer> marks) {
        super(fullName, marks);
    }

    public String getFullName() {
        return this.fullName;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    @Override
    public int getTotalScore() {
        return marks.values().stream()
                .mapToInt(Integer::intValue)
                .sum(); // Sum all the marks in the map
    }

    @Override
    public double getAverageScore() {
        if(marks == null || marks.isEmpty()) {
            return 0;
        }
        return marks.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    @Override
    public int getMaxMark() {
        if (marks == null || marks.isEmpty()) {
            return 0;
        }
        return marks.values().stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public int getMinMark() {
        if (marks == null || marks.isEmpty()) {
            return 0;
        }
        return marks.values().stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public String toString() {
        return "Student Data{" +
                "fullName='" + fullName + '\'' +
                ", marks=" + marks +
                ", totalScore=" + getTotalScore() +
                ", averageScore=" + getAverageScore() +
                ", maxMark=" + getMaxMark() +
                ", minMark=" + getMinMark() +
                '}';
    }
}
