package by.solution.model;

import by.solution.itarator.Aggregate;
import by.solution.itarator.Iterator;
import by.solution.visitor.Element;
import by.solution.visitor.Visitor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TreeList<T extends Comparable<T>> implements Aggregate<T>, Element<T> {
    protected ArrayList<T> elements;

    public TreeList() {
        this.elements = new ArrayList<>();
        this.elements.add(null);
    }

    public void add(T value) {
        if (elements.size() == 1) {
            elements.add(value);
            return;
        }

        int index = 1;
        while (true) {
            while (index >= elements.size()) {
                elements.add(null);
            }

            T current = elements.get(index);
            if (current == null) {
                elements.set(index, value);
                return;
            }

            if (value.compareTo(current) < 0) {
                index = 2 * index;
            } else {
                index = 2 * index + 1;
            }
        }
    }

    public List<T> preOrderTraversal() {
        List<T> result = new ArrayList<>();
        preOrderTraversal(1, result);
        return result;
    }

    private void preOrderTraversal(int index, List<T> result) {
        if (index >= elements.size() || elements.get(index) == null) {
            return;
        }

        result.add(elements.get(index));
        preOrderTraversal(2 * index, result);
        preOrderTraversal(2 * index + 1, result);
    }

    public int size() {
        int count = 0;
        for (T element : elements) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        elements.clear();
        elements.add(null);
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

    public JList<T> toJList() {
        DefaultListModel<T> listModel = new DefaultListModel<>();
        elements.stream().filter(Objects::nonNull).forEach(listModel::addElement);
        return new JList<>(listModel);
    }

    public List<T> getList() {
        return elements;
    }

    private class TreeListIterator implements Iterator<T> {
        private int index;

        public TreeListIterator() {
            this.index = 1;
        }

        @Override
        public void first() {
            this.index = 0;
        }

        @Override
        public boolean isDone() {
            while (index < elements.size() && elements.get(index) == null) {
                index++;
            }
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
        public T currentItem() {
            return elements.get(index);
        }
    }

    @Override
    public Iterator<T> createIterator() {
        return new TreeListIterator();
    }

    @Override
    public void accept(Visitor<T> visitor) {
        visitor.visit(this);
    }
}
