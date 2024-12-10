package by.solution.visitor_pattern;

public interface Element<K, V> {
    void accept(Visitor<K, V> visitor);
}
