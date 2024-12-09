package by.solution.visitor;

public interface Element<K, V> {
    void accept(Visitor<K, V> visitor);
}
