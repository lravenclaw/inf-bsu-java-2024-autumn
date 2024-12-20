package by.solution.strategy.price;

import by.solution.strategy.ProcessStrategy;
import by.solution.strategy.data.Toy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class DefaultPriceStrategy implements ProcessStrategy<Toy> {
    @Override
    public List<Toy> process(List<Toy> toys, int totalSum) {
        ArrayList<Toy> result = new ArrayList<>();

        TreeSet<Integer> used = new TreeSet<>();
        double currentSum = 0.;
        boolean full = false;

        while (!(full ||toys.isEmpty())) {
            Random random = new Random();
            Integer index = random.nextInt(toys.size());
            if (used.contains(index)) {
                continue;
            }

            var currentToy = toys.get(index);
            if (currentSum + currentToy.price - totalSum >= 1e-6) {
                full = true;
                continue;
            }

            used.add(index);
            result.add(currentToy);
            currentSum += currentToy.price;
        }
        return result;
    }
}
