package solution;

import java.util.Vector;
import solution.*;

public class Main {
    public static void main(String[] args) {
        try {
            Test.runAllTest();
        } catch (FileNotFoundException) {
            System.out.println("Error. Can't read data from files.");
        } catch (NotEnoughDataException e) {
            System.out.println("Error. Not enough data in file.");
        } catch (TooMuchDataException e) {
            System.out.println("Error. Too much data in file.");
        }
    }
}