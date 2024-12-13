package by.solution.visitor;

public interface Element<T extends Comparable<T>> {
    void accept(Visitor<T> visitor);
}
