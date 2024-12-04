package by.solution.strategy.data;

import java.util.Comparator;

public class Toy {
    public String name;
    public double price;
    public int ageLimit;

    public Toy(String name, double price, int ageLimit) {
        this.name = name;
        this.price = price;
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        return "Название: " + name + ", Цена: " + price + ", Возраст: " + ageLimit;
    }

    public static Comparator<Toy> byPrice = Comparator.comparingDouble(toy -> toy.price);
    public static Comparator<Toy> byAgeLimit = Comparator.comparingInt(toy -> toy.ageLimit);
    public static Comparator<Toy> byName = Comparator.comparing(toy -> toy.name);
}
