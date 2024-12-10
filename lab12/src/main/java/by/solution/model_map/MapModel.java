package by.solution.model_map;

import by.solution.iterator_pair_pattern.Aggregate;
import by.solution.iterator_pair_pattern.Iterator;
import by.solution.visitor_pattern.Element;
import by.solution.visitor_pattern.Visitor;
import javax.swing.*;
import java.util.*;

public class MapModel<K, V> implements Aggregate<MapModel.Entry<K, V>>, Element<K, V> {
    @Override
    public Iterator<MapModel.Entry<K, V>> createIterator() {
        return new PairIterator();
    }

    static public class Entry<F, S> {
        private final F key;
        private final S value;

        public Entry(F first, S second) {
            this.key = first;
            this.value = second;
        }

        public F getKey() {
            return key;
        }

        public S getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry<?, ?> p = (Entry<?, ?>) o;
            return Objects.equals(p.getKey(), key) && Objects.equals(p.getValue(), value);
        }

        public String toString() {
            return "(" + key + ", " + value + ")";
        }

        public static <A, B> Entry<A, B> create(A a, B b) {
            return new Entry<A, B>(a, b);
        }
    }

    protected ArrayList<Entry<K, V>> elements;

    public MapModel() {
        this.elements = new ArrayList<>();
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void clear() {
        elements.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MapModel<?, ?> that = (MapModel<?, ?>) obj;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<Entry<K, V>> iterator = createIterator();
        while (!iterator.isDone()) {
            Entry<K, V> entry = iterator.currentItem();
            iterator.next();
            sb.append(entry.toString());
            if (!iterator.isDone()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public JList<Entry<K, V>> getJList(){
        DefaultListModel<Entry<K, V>> listModel = new DefaultListModel<>();
        for (var entry : elements) {
            listModel.addElement(entry);
        }
        return new JList<>(listModel);
    }

    private class PairIterator implements Iterator<Entry<K, V>> {
        private int index;

        public PairIterator() {
            this.index = 0;
        }

        @Override
        public void first() {
            this.index = 0;
        }

        @Override
        public boolean isDone() {
            return index >= elements.size();
        }

        @Override
        public void next() {
            if (isDone()) {
                throw new NoSuchElementException("No more elements");
            }
            ++index;
        }

        @Override
        public Entry<K, V> currentItem() {
            return (Entry<K, V>) elements.get(index);
        }
    }

    public Optional<V> get(K key) {
        for (var entity : elements) {
            if (entity.getKey().equals(key)) {
                return Optional.of(entity.getValue());
            }
        }
        return Optional.empty();
    }

    public void put(K key, V value) {
        if (get(key).isPresent()) {
            return;
        }
        elements.add(new Entry<>(key, value));
    }

    public void putAll(Map<K, V> data) {
        for (var entity : data.entrySet()) {
            put(entity.getKey(), entity.getValue());
        }
    }

    @Override
    public void accept(Visitor<K, V> visitor) {
        visitor.visit(this);
    }

    public List<Entry<K, V>> getList() {
        return elements;
    }

    public void remove(K key) {
        elements.removeIf(entry -> entry.getKey().equals(key));
    }
}