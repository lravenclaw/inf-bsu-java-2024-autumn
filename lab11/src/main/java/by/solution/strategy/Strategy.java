package by.solution.strategy;

import by.solution.strategy.data.Toy;

import java.util.Comparator;
import java.util.List;

public interface Strategy<T> {
    List<T> process(List<T> list, int value);
}
