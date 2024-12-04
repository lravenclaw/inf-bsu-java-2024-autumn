package by.solution.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject implements Subject {
    List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
}

