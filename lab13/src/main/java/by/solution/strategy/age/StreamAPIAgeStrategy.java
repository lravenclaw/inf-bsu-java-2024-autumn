package by.solution.strategy.age;

import by.solution.strategy.ProcessStrategy;
import by.solution.strategy.data.Toy;

import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIAgeStrategy implements ProcessStrategy<Toy> {
    @Override
    public List<Toy> process(List<Toy> list, int age) {
        return list.stream()
                .filter(toy -> age >= toy.ageLimit)
                .collect(Collectors.toList());
    }
}
