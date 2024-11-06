package by.piasecki.studentData.data;

import java.util.*;

abstract public class AbsractStudentData {
    String fullName;
    Map<String, Integer> marks;

    AbsractStudentData(String fullName, Map<String, Integer> marks) {
        this.fullName = fullName;
        this.marks = marks;
    }

    abstract int getTotalScore();
    abstract public double getAverageScore();
    abstract public int getMaxMark();
    abstract public int getMinMark();
}
