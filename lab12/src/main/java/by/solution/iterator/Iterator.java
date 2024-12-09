package by.solution.iterator;

public interface Iterator <T>{
    void first();
    boolean isDone();
    void next();
    T currentItem();
}
