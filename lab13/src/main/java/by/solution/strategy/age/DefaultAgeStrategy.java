package by.solution.strategy.age;

import by.solution.strategy.ProcessStrategy;
import by.solution.strategy.data.Toy;

import java.util.ArrayList;
import java.util.List;

public class DefaultAgeStrategy implements ProcessStrategy<Toy> {
    @Override
    public List<Toy> process(List<Toy> list, int age) {
        ArrayList<Toy> result = new ArrayList<>();
        for (Toy toy : list) {
            if (age >= toy.ageLimit) {
                result.add(toy);
            }
        }
        return result.stream().toList();
    }
}
