package by.solution.visitor;

import by.solution.iterator.Iterator;
import by.solution.model.MapModel;

public class UnionVisitor<K, V> implements Visitor<K, V> {

    private final MapModel<K, V> map;
    private final MapModel<K, V> unitedMap;

    public UnionVisitor(MapModel<K, V> map) {
        this.map = map;
        this.unitedMap = new MapModel<>();
    }

    @Override
    public void visit(MapModel<K, V> otherMap) {
        unitedMap.getList().addAll(map.getList());
        Iterator<MapModel.Entry<K, V>> iterator = otherMap.createIterator();
        while (!iterator.isDone()) {
            MapModel.Entry<K, V> entry = iterator.currentItem();
            if (!unitedMap.getList().contains(entry)) {
                unitedMap.put(entry.getKey(), entry.getValue());
            }
            iterator.next();
        }
    }

    public MapModel<K, V> getResult() {
        return unitedMap;
    }
}