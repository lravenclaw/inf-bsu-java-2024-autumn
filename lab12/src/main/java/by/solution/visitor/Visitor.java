package by.solution.visitor;

import by.solution.model.MapModel;

public interface Visitor<K, V> {
    void visit(MapModel<K, V> map);
}
