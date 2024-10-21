package by.solution.parser;

import by.solution.data.Toy;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToyParser {
    public static ArrayList<Toy> readToysFromFile(File file) {
        ArrayList<Toy> toys = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line_buffer;
            while ((line_buffer = br.readLine()) != null) {
                String[] parts = line_buffer.split(",");

                if (parts.length < 3) {
                    throw new RuntimeException("Error input file format! Can't read from file.");
                }

                Toy toy = new Toy(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));

                toys.add(toy);
            }
        } catch (IOException e) {
            throw new RuntimeException("Invalid input! Error input file format.");
        }

        return toys;
    }
}
