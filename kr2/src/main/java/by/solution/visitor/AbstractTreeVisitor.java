package by.solution.visitor;

import by.solution.model.*;

public abstract class AbstractTreeVisitor<T extends Comparable<T>> implements Visitor<T>{
    TreeList<T> tree;
}
