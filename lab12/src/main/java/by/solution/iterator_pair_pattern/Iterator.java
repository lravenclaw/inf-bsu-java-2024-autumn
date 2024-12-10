package by.solution.iterator_pair_pattern;

public interface Iterator <T>{
    void first();
    boolean isDone();
    void next();
    T currentItem();
}
