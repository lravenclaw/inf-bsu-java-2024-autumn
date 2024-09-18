package solution;

import java.util.Vector;
import solution.MatrixTools;

public class Main {
    public static void main(String[] args) {
        String filename = new String();
        if (args.length > 0) {
            filename = args[0]; // Parse the first command-line argument as an integer
        } else {
            System.out.println("No cmd args found.");
        }

        int[][] matrix = new int[0][0];

        try {
            matrix = MatrixTools.parseData(filename);
        } catch (Exception _) {
            System.out.println("Error. Can't read data from file.");
        }

        Vector<Pair<Integer, Integer>> result = MatrixTools.getLocalMaxes(matrix);

        for (int i = 0; i < result.size(); ++i) {
            System.out.println("(" + result.get(i).first + ";" + result.get(i).second + ")");
        }
    }
}