package by.solution.strategy.io;

import by.solution.strategy.data.Toy;
import java.util.List;

public class ToyUtils {
    public static String toString(List<Toy> toys) {
//        if (toys == null) {
//            return "";
//        }

        StringBuilder result = new StringBuilder();
        for (Toy toy : toys) {
            result.append(toy.toString()).append("\n");
        }
        return result.toString();
    }
}