package by.solution.strategy;

import java.util.Comparator;
import java.util.List;

public class DefaultStrategy implements Strategy {
    @Override
    public <T> List<T> sort(List<T> list, Comparator<T> comp) {
        list.sort(comp);
        return list;
    }
}
