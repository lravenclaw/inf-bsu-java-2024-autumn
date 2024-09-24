package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Test {
    static private  Vector<Pair<Integer, Integer>> readResult(String filename) {
        File file = new File(filename);
        Scanner sc;

        Vector<Pair<Integer, Integer>> result = new Vector<>();

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException _) {
            throw new RuntimeException();
        }

        while (sc.hasNextInt()) {
            result.add(new Pair<>(sc.nextInt(), sc.nextInt()));
        }
        sc.close();
        return result;
    }

    static private String resultToStr(Vector<Pair<Integer, Integer>> vec) {
        StringBuilder result = new StringBuilder();
        for (Pair<Integer, Integer> pair : vec) {
            result.append("(" + Integer.toString(pair.first) + ";" + Integer.toString(pair.second) + ") ");
        }
        return result.toString();
    }

    static public void runAllTest() {
        for (int i = 0; i != 8; ++i) {
            String full_prefix = "/home/user/Code/java/inf-bsu-java-2024-spring/lab3/src/main/resources/";
            String filename = Integer.toString(i) + ".txt";

            int[][] matrix = new int[0][0];

            try {
                matrix = MatrixTools.parseData(full_prefix + "input/" + filename);
            } catch (Exception _) {
                System.out.println("Error. Can't read data from file.");
            }

            Vector<Pair<Integer, Integer>> result = MatrixTools.getLocalMaxes(matrix);


            System.out.print(Integer.toString(i) + " test");
            Vector<Pair<Integer, Integer>> expected = readResult(full_prefix + "result/" + filename);
            if (result.equals(expected)) {
                System.out.println(" [PASSED]");
            } else {
                System.out.println(" [FAILED]");
                System.out.println("expected: " + resultToStr(expected));
                System.out.println("got: " + resultToStr(result));
            }
        }
    }
}
