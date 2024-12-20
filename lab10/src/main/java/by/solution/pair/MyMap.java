package by.solution.pair;

import by.solution.Aggregate;
import by.solution.Iterator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.NoSuchElementException;

public class MyMap<K, V> implements Aggregate<MyMap.Entry<K, V>> {
    @Override
    public Iterator<MyMap.Entry<K, V>> createIterator() {
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

    public MyMap() {
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
        MyMap<?, ?> that = (MyMap<?, ?>) obj;
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

    public DefaultListModel<Entry<K, V>> getListModel(){
        DefaultListModel<Entry<K, V>> listModel = new DefaultListModel<Entry<K, V>>();
        elements.forEach(listModel::addElement);
        return listModel;
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
        if (get(key) != Optional.empty()) {
            return;
        }
        elements.add(new Entry<>(key, value));
    }

    public void putAll(Map<K, V> data) {
        for (var entity : data.entrySet()) {
            put(entity.getKey(), entity.getValue());
        }
    }
}