package by.solution.example;

import by.solution.pair.MyMap;
import by.solution.Iterator;

public class Main {
    public static void main(String[] args) {
        MyMap<String, Integer> myMap = new MyMap<>();
        myMap.put("One", 1);
        myMap.put("Two", 2);
        myMap.put("Three", 3);
        myMap.put("Four", 4);
        myMap.put("Five", 5);

        Iterator<MyMap.Entry<String, Integer>> iterator = myMap.createIterator();
        System.out.println("Iterating over MyMap entries:");
        while (!iterator.isDone()) {
            MyMap.Entry<String, Integer> entry = iterator.currentItem();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            iterator.next();
        }

        iterator.first();
        System.out.println("\nIterating over MyMap entries again after resetting:");
        while (!iterator.isDone()) {
            MyMap.Entry<String, Integer> entry = iterator.currentItem();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            iterator.next();
        }
    }
}