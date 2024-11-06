package by.solution;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<LightBulb> b=BulbCollection.getFromFile("src/main/resources/LED.txt");
        System.out.println(BulbCollection.Cmanufacturer(b));
        System.out.println(BulbCollection.findAveragePrice(b,"name1"));
        System.out.println(BulbCollection.sortUp(b));
        System.out.println(BulbCollection.sortDownByPricePower(b));
    }
}