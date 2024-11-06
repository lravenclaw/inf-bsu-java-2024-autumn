package by.piasecki.studentData.data;

import java.util.*;

import by.piasecki.studentData.data.AbstractStudent;

import java.util.*;
import java.util.stream.Collectors;

public class StudentCollection<T extends AbstractStudent> {
    private final List<T> students;

    public StudentCollection(List<T> students) {
        this.students = students;
    }

    public List<T> sortedBySchoolAndLastName() {
        return students.stream()
                .sorted(Comparator.comparing(AbstractStudent::getSchoolName)
                        .thenComparing(Comparator.comparing(AbstractStudent::getFullName).reversed()))
                .collect(Collectors.toList());
    }

    public long countStudentsInSchool(String schoolName) {
        return students.stream()
                .filter(student -> student.getSchoolName().equalsIgnoreCase(schoolName))
                .count();
    }

    public Optional<T> binarySearch(T studentToFind) {
        List<T> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparing(AbstractStudent::getFullName)
                .thenComparing(AbstractStudent::getSchoolName)
                .thenComparing(AbstractStudent::getSchoolScore)
                .thenComparing(AbstractStudent::getAverageMark));

        int index = Collections.binarySearch(sortedStudents, studentToFind, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return Comparator
                        .comparing(AbstractStudent::getFullName)
                        .thenComparing(AbstractStudent::getSchoolName)
                        .thenComparing(AbstractStudent::getSchoolScore)
                        .thenComparing(AbstractStudent::getAverageMark)
                        .compare(o1, o2);
            }
        });

        return index >= 0 ? Optional.of(sortedStudents.get(index)) : Optional.empty();
    }

    public List<T> filterByRatingRatio() {
        return students.stream()
                .filter(student -> (float) student.getSchoolScore() / student.getAverageMark() > 1)
                .collect(Collectors.toList());
    }

    public List<String> lastNamesStartingWith(String str) {
        return students.stream()
                .map(student -> student.getFullName().split(" ")[1])
                .filter(lastName -> lastName.startsWith(str))
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
