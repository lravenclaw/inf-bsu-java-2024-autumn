package by.solution.strategy;

import java.util.List;

public interface ProcessStrategy<T> {
    List<T> process(List<T> list, int value);
}
