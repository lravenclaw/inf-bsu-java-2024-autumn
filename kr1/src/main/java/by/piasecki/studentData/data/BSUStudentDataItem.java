package by.piasecki.studentData.data;

import java.util.Map;

public class BSUStudentDataItem extends StudentData {
    BSUStudentDataItem(int id, String fullName, Map<String, Integer> marks) {
        super(fullName, marks);
        studentCardNumber = id;
    }

    @Override
    public String toString() {
        return "BSU Student Data{" +
                "fullName='" + fullName + '\'' +
                ", id=" + studentCardNumber +
                ", marks=" + marks +
                ", totalScore=" + getTotalScore() +
                ", averageScore=" + getAverageScore() +
                ", maxMark=" + getMaxMark() +
                ", minMark=" + getMinMark() +
                '}';
    }

    int studentCardNumber;
}