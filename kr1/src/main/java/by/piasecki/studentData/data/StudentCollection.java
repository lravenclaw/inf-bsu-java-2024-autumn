package by.piasecki.studentData.data;

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

    public List<T> filterByRatingRatio(double ratioThreshold) {
        return students.stream()
                .filter(student -> (float) student.getSchoolScore() / student.getAverageMark() > ratioThreshold)
                .collect(Collectors.toList());
    }

    public List<T> getLastNamesStartingWith(String prefix) {
        return students.stream()
                .filter(student -> student.getFullName().split(" ").length > 1) // ensure there is a last name
                .filter(student -> student.getFullName().split(" ")[1].startsWith(prefix))
                .collect(Collectors.toList());
    }

    // Search by full name
    public List<T> searchByName(String name) {
        return students.stream()

                .filter(student -> student.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public int size() {
        return students.size();
    }
}
