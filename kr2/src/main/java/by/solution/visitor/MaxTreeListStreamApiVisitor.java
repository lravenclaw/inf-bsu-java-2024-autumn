package by.solution.visitor;

import by.solution.model.TreeList;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxTreeListStreamApiVisitor<T extends Comparable<T>> extends AbstractTreeVisitor<T> implements Visitor<T> {
    private T maxElement;

    public MaxTreeListStreamApiVisitor(TreeList<T> tree) {
        this.tree = tree;
    }

    public T max() {
        return maxElement;
    }

    @Override
    public void visit(TreeList<T> tree) {
        List<T> elements = tree.getList();
        Optional<T> maxOptional = elements.stream()
                .filter(e -> e != null)
                .max(Comparator.naturalOrder());
        maxElement = maxOptional.orElse(null);
    }
}