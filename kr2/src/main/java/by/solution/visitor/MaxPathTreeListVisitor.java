package by.solution.visitor;

```java
        package by.solution.visitor;

import by.solution.model.TreeList;
import by.solution.visitor.AbstractTreeVisitor;
import by.solution.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class MaxPathTreeListVisitor<T extends Comparable<T>>
        extends AbstractTreeVisitor<T> implements Visitor<T> {
    private ArrayList<T> result;
    private TreeList<T> tree;

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

        findMaxPath(0, new ArrayList<>());
    }

    private void findMaxPath(int index, ArrayList<T> currentPath) {
        if (index >= tree.getList().size() || tree.getList().get(index) == null) {
            return;
        }

        currentPath.add(tree.getList().get(index));

        T current = tree.getList().get(index);
        T leftMax = findMax(2 * index + 1);
        T rightMax = findMax(2 * index + 2);

        if (leftMax != null && leftMax.compareTo(current) > 0) {
            findMaxPath(2 * index + 1, new ArrayList<>(currentPath));
        } else if (rightMax != null && rightMax.compareTo(current) > 0) {
            findMaxPath(2 * index + 2, new ArrayList<>(currentPath));
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
        T leftMax = findMax(2 * index + 1);
        T rightMax = findMax(2 * index + 2);

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