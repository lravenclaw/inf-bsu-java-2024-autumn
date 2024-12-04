package by.solution.observer;

public class KeySubject extends AbstractSubject implements Subject {
    String lastKey;

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(lastKey);
        }
    }

    public void setKey(String key) {
        this.lastKey = key;
    }
}