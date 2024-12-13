package by.solution.visitor;

import by.solution.model.*;

public interface Visitor<T> {
    void visit(TreeList<T> tree);
}
