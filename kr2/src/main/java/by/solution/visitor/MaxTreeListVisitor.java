package by.solution.visitor;

import by.solution.model.TreeList;

import java.util.ArrayList;
import java.util.List;

public class MaxTreeListVisitor<T extends Comparable<T>>
        extends AbstractTreeVisitor<T> implements Visitor<T> {
    private ArrayList<T> result;

    public MaxTreeListVisitor(TreeList<T> tree) {
        this.tree = tree;
        this.result = new ArrayList<>();
    }

    public List<T> getResult() {
        return this.result;
    }

    @Override
    public void visit(TreeList<T> tree) {
        if (tree == null || tree.isEmpty()) {
            return;
        }

        T maxElement = findMax(1);
        result.add(maxElement);
    }

    private T findMax(int index) {
        if (index >= tree.getList().size() || tree.getList().get(index) == null) {
            return null;
        }

        T current = tree.getList().get(index);
        T leftMax = findMax(2 * index);
        T rightMax = findMax(2 * index + 1);

        T max = current;
        if (leftMax != null && leftMax.compareTo(max) > 0) {
            max = leftMax;
        }
        if (rightMax != null && rightMax.compareTo(max) > 0) {
            max = rightMax;
        }

        return max;
    }
}