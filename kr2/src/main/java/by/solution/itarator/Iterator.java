package by.solution.itarator;

public interface Iterator <T>{
    void first();
    boolean isDone();
    void next();
    T currentItem();
}
