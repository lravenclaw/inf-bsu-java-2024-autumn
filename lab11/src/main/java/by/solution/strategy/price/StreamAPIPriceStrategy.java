package by.solution.strategy.price;

import by.solution.strategy.Strategy;
import by.solution.strategy.data.Toy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPIPriceStrategy implements Strategy<Toy> {
    @Override
    public List<Toy> process(List<Toy> toys, int totalSum) {
        List<Toy> result = new ArrayList<>();
        double[] currentSum = {0.0};

        toys.stream()
                .sorted(Comparator.comparingDouble(toy -> toy.price))
                .takeWhile(toy -> {
                    if (currentSum[0] + toy.price <= totalSum) {
                        currentSum[0] += toy.price;
                        result.add(toy);
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        return result;
    }
}
