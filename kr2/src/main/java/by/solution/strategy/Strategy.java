package by.solution.strategy;

import by.solution.model.TreeList;

public interface Strategy<T extends Comparable<T>> {
    T process(TreeList<T> tree);
}
