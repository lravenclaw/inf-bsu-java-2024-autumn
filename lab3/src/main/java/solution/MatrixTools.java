package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

class MatrixTools {
    public static int[][] parseData(String filename) {
        File file = new File(filename);
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw e;
        }

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (sc.hasNextInt()) {
                    matrix[i][j] = sc.nextInt();
                } else if (i < rows - 1 || j < cols - 1) {
                    throw new NotEnoughDataException("Not enough data in file");
                }
            }
        }

        if (sc.hasNextInt()) {
            throw new TooMuchDataException("Too much data in file");
        }

        sc.close();

        return matrix;
    }


    public static boolean isLocalMax(int[][] matrix, Pair<Integer, Integer> pair) {
        boolean result = true;
        Integer current = matrix[pair.first][pair.second];

        if (matrix.length == 0) {
            return false;
        }

        if (pair.first + 1 < matrix.length) {
            result &= current > matrix[pair.first + 1][pair.second];
        }
        if (pair.first > 0 && matrix.length > 1) {
            result &= current > matrix[pair.first - 1][pair.second];
        }
        if (pair.second + 1 < matrix[0].length) {
            result &= current > matrix[pair.first][pair.second + 1];
        }
        if (pair.second > 0 && matrix[0].length > 1) {
            result &= current > matrix[pair.first][pair.second - 1];
        }

        return result;
    }

    public static boolean isLocalMin(int[][] matrix, Pair<Integer, Integer> pair) {
        boolean result = true;
        Integer current = matrix[pair.first][pair.second];

        if (matrix.length == 0) {
            return false;
        }

        if (pair.first + 1 < matrix.length) {
            result &= current < matrix[pair.first + 1][pair.second];
        }
        if (pair.first > 0 && matrix.length > 1) {
            result &= current < matrix[pair.first - 1][pair.second];
        }
        if (pair.second + 1 < matrix[0].length) {
            result &= current < matrix[pair.first][pair.second + 1];
        }
        if (pair.second > 0 && matrix[0].length > 1) {
            result &= current < matrix[pair.first][pair.second - 1];
        }

        return result;
    }


    public static Vector<Pair<Integer, Integer>> getLocalMaxes(int[][] matrix) {
        Vector<Pair<Integer, Integer>> result = new Vector<>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                Pair<Integer, Integer> current_pos = new Pair<>(i, j);
                if (isLocalMax(matrix, current_pos)) {
                    result.add(current_pos);
                }
            }
        }
        return result;
    }

    public static Vector<Pair<Integer, Integer>> getLocalMins(int[][] matrix) {
        Vector<Pair<Integer, Integer>> result = new Vector<>();
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                Pair<Integer, Integer> current_pos = new Pair<>(i, j);
                if (isLocalMin(matrix, current_pos)) {
                    result.add(current_pos);
                }
            }
        }
        return result;
    }


}
