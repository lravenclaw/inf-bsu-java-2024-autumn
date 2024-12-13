package by.solution.visitor;

import by.solution.model.*;

public interface Visitor<T extends Comparable<T>> {
    void visit(TreeList<T> tree);
}
