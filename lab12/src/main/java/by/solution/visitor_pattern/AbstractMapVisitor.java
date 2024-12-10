package by.solution.visitor_pattern;

import by.solution.model_map.MapModel;

public abstract class AbstractMapVisitor<K,V> implements Visitor<K,V>{
    MapModel<K, V> map;
    MapModel<K, V> resultMap;
}
