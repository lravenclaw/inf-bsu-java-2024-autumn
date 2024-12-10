package by.solution.visitor_pattern;

import by.solution.model_map.MapModel;

public interface Visitor<K, V> {
    void visit(MapModel<K, V> map);
}
