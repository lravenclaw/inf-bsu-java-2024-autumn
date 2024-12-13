package by.solution.visitor;

import by.solution.model.*;

public abstract class AbstractTreeVisitor<T> implements Visitor<T>{
    TreeList<T> tree;
}
