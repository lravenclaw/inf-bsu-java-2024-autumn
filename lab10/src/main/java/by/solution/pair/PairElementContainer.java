package by.solution.pair;

import java.util.*;

public class PairElementContainer<K, V> {
    protected ArrayList<Entry<K, V>> elements;

    public PairElementContainer() {
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
        PairElementContainer<?, ?> that = (PairElementContainer<?, ?>) obj;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<Entry<K, V>> iterator = iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            sb.append("Entry{key=").append(entry.getKey()).append(", value=").append(entry.getValue()).append("}");
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ArrayList<Entry<K, V>> toJList() {
        return elements;
    }

    private class PairIterator implements Iterator<Entry<K, V>> {
        private int index;
        private final PairElementContainer<K, V> container;

        public PairIterator(PairElementContainer<K, V> container) {
            this.container = container;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < container.size();
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            return (Entry<K, V>) container.elements.get(index++);
        }
    }

    public Iterator<Entry<K, V>> iterator() {
        return new PairIterator(this);
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
        if (get(key) != null) {
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