package by.solution.visitor;

import by.solution.model.TreeList;

import java.util.ArrayList;
import java.util.List;

public class MaxPathTreeListVisitor<T> extends AbstractTreeVisitor<T> implements Visitor<T> {
    private ArrayList<T> result;

    public MaxPathTreeListVisitor(TreeList<T> tree) {
        this.tree = tree;
        this.result = new ArrayList<>();
    }

    public List<T> getResult() {
        return this.result;
    }

    @Override
    public void visit(TreeList<T> tree) {
        // max element
    }
}