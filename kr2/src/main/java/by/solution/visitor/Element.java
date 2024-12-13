package by.solution.visitor;

public interface Element<T> {
    void accept(Visitor<T> visitor);
}
