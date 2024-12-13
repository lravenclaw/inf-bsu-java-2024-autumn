package by.solution.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class TreeList<T> implements Iterable<T> {
    protected ArrayList<T> elements;

    public TreeList() {
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
        TreeList<?> that = (TreeList<?>) obj;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public DefaultListModel<T> getListModel(){
        DefaultListModel<T> listModel = new DefaultListModel<T>();
        elements.forEach(listModel::addElement);
        return listModel;
    }

    private class SingleIterator implements Iterator<T> {
        private int index;

        public SingleIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < elements.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            return (T) elements.get(index++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        elements.forEach(action);
    }
}
