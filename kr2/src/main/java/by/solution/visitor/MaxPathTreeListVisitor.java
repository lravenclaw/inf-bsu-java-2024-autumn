package by.solution.visitor;

import by.solution.model.TreeList;
import java.util.ArrayList;
import java.util.List;

public class MaxPathTreeListVisitor<T extends Comparable<T>> extends AbstractTreeVisitor<T> implements Visitor<T> {
    private List<T> result;

    public MaxPathTreeListVisitor(TreeList<T> tree) {
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
        findMaxPath(1, new ArrayList<>());
    }

    private void findMaxPath(int index, List<T> currentPath) {
        if (index >= tree.getList().size() || tree.getList().get(index) == null) {
            return;
        }

        currentPath.add(tree.getList().get(index));

        T current = tree.getList().get(index);
        T leftMax = findMax(2 * index);
        T rightMax = findMax(2 * index + 1);

        if (leftMax != null && leftMax.compareTo(current) > 0) {
            findMaxPath(2 * index, new ArrayList<>(currentPath));
        } else if (rightMax != null && rightMax.compareTo(current) > 0) {
            findMaxPath(2 * index + 1, new ArrayList<>(currentPath));
        } else {
            if (result.isEmpty() || current.compareTo(result.get(result.size() - 1)) > 0) {
                result.clear();
                result.addAll(currentPath);
            }
        }
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