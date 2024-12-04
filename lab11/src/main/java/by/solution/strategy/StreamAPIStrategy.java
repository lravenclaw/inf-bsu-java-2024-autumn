package by.solution.strategy;

import by.solution.strategy.data.Toy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIStrategy implements Strategy {
    @Override
    public <T> List<T> sort(List<T> list, Comparator<T> comp) {
        return list.stream().sorted(comp).collect(Collectors.toList());
    }
}
