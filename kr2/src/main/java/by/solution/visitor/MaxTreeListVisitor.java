package by.solution.visitor;

import by.solution.model.TreeList;

public class MaxTreeListVisitor<T> extends AbstractTreeVisitor<T> implements Visitor<T> {
    private T result;

    public MaxTreeListVisitor(TreeList<T> tree) {
        this.tree = tree;
    }

    public T getResult() {
        return result;
    }

    @Override
    public void visit(TreeList<T> tree) {
        // max element
    }
}