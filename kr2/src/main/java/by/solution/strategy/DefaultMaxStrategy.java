package by.solution.strategy;

import by.solution.model.TreeList;
import by.solution.visitor.MaxTreeListVisitor;

public class DefaultMaxStrategy<T extends Comparable<T>> implements Strategy<T> {
    @Override
    public T process(TreeList<T> tree) {
        MaxTreeListVisitor<T> maxVisitor = new MaxTreeListVisitor<>(tree);
        tree.accept(maxVisitor);
        return maxVisitor.max();
    }
}