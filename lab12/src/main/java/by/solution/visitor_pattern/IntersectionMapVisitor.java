package by.solution.visitor_pattern;

import by.solution.iterator_pair_pattern.Iterator;
import by.solution.model_map.MapModel;

public class IntersectionMapVisitor<K, V> extends AbstractMapVisitor<K, V> implements Visitor<K, V> {
    public IntersectionMapVisitor(MapModel<K, V> map) {
        this.map = map;
        this.resultMap = new MapModel<>();
    }

    @Override
    public void visit(MapModel<K, V> otherMap) {
        Iterator<MapModel.Entry<K, V>> iterator = map.createIterator();
        while (!iterator.isDone()) {
            MapModel.Entry<K, V> entry = iterator.currentItem();
            if (otherMap.get(entry.getKey()).isPresent()) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
            iterator.next();
        }
    }

    public MapModel<K, V> getResult() {
        return resultMap;
    }
}