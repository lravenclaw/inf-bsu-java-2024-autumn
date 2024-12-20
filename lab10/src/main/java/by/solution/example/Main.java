package by.solution.example;

import by.solution.pair.MyMap;
import by.solution.Iterator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        Optional<Integer> value = myMap.get("Three");
        System.out.println("\nValue for key 'Three': " + value.orElse(null));

        System.out.println("\nIs MyMap empty? " + myMap.isEmpty());

        System.out.println("Size of MyMap: " + myMap.size());

        myMap.clear();
        System.out.println("Size of MyMap after clearing: " + myMap.size());

        Map<String, Integer> data = new HashMap<>();
        data.put("Six", 6);
        data.put("Seven", 7);
        myMap.putAll(data);
        System.out.println("\nMyMap after putAll:");
        iterator.first();
        while (!iterator.isDone()) {
            MyMap.Entry<String, Integer> entry = iterator.currentItem();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            iterator.next();
        }

        System.out.println("\nMyMap toString: " + myMap.toString());

        MyMap<String, Integer> anotherMap = new MyMap<>();
        anotherMap.put("Six", 6);
        anotherMap.put("Seven", 7);

        System.out.println("\nIs myMap equal to anotherMap? " + myMap.equals(anotherMap));
    }
}