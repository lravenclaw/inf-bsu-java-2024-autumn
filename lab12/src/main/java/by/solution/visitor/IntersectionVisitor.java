package by.solution.visitor;

import by.solution.iterator.Iterator;
import by.solution.model.MapModel;

public class IntersectionVisitor<K, V> implements Visitor<K, V> {

    private final MapModel<K, V> map;
    private final MapModel<K, V> intersectionMap;

    public IntersectionVisitor(MapModel<K, V> map) {
        this.map = map;
        this.intersectionMap = new MapModel<>();
    }

    @Override
    public void visit(MapModel<K, V> otherMap) {
        Iterator<MapModel.Entry<K, V>> iterator = map.createIterator();
        while (!iterator.isDone()) {
            MapModel.Entry<K, V> entry = iterator.currentItem();
            if (otherMap.get(entry.getKey()).isPresent()) {
                intersectionMap.put(entry.getKey(), entry.getValue());
            }
            iterator.next();
        }
    }

    public MapModel<K, V> getResult() {
        return intersectionMap;
    }
}