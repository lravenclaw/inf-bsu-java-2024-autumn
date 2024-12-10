package by.solution.visitor_pattern;

import by.solution.iterator_pair_pattern.Iterator;
import by.solution.model_map.MapModel;

public class UnionMapVisitor<K, V> extends AbstractMapVisitor<K, V> implements Visitor<K, V> {
    public UnionMapVisitor(MapModel<K, V> map) {
        this.map = map;
        this.resultMap = new MapModel<>();
    }

    @Override
    public void visit(MapModel<K, V> otherMap) {
        resultMap.getList().addAll(map.getList());
        Iterator<MapModel.Entry<K, V>> iterator = otherMap.createIterator();
        while (!iterator.isDone()) {
            MapModel.Entry<K, V> entry = iterator.currentItem();
            if (!resultMap.getList().contains(entry)) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
            iterator.next();
        }
    }

    public MapModel<K, V> getResult() {
        return resultMap;
    }
}