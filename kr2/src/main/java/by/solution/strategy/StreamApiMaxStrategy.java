package by.solution.strategy;

import by.solution.model.TreeList;
import by.solution.visitor.MaxTreeListStreamApiVisitor;

public class StreamApiMaxStrategy<T extends Comparable<T>> implements Strategy<T> {
    @Override
    public T process(TreeList<T> tree) {
        MaxTreeListStreamApiVisitor<T> maxVisitor = new MaxTreeListStreamApiVisitor<>(tree);
        tree.accept(maxVisitor);
        return maxVisitor.max();
    }
}