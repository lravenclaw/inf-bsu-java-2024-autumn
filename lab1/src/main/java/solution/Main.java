package solution;

import java.lang.reflect.*;
import java.util.*;

class Variant4 implements TaylorSeries {
    @Override
    public SeriesElement getNext(SeriesElement prev, double x) {
        int k = prev.k + 1;
        double sub_value = prev.sub_value * (-1) * Math.pow(x, 2) / (k + 2);
        double real_value = sub_value / (k + 1);
        return new SeriesElement(sub_value, real_value, k);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter x value: ");
        double x = in.nextDouble();

        System.out.println("Enter error value: ");
        double error = in.nextDouble();

        try {
            Variant4 instance = new Variant4();
            double result = calculateSum(instance.getClass(), x, error);
            System.out.printf("Result sum: %f", result);
        } catch (Exception e) {
            System.out.println("Error occurred.");
        }
    }

    public static double calculateSum(Class<? extends TaylorSeries> series, double x, double error) throws Exception {
        Method method = series.getMethod("getNext", SeriesElement.class, double.class);
        Object instance = series.getDeclaredConstructor().newInstance();

        final double eps = 1e-6;

        double sum = 0;
        SeriesElement el = new SeriesElement(1, 1, 1);
        do {
            sum += el.real_value;
            el = (SeriesElement) method.invoke(instance, el, x);
        } while (Math.abs(el.real_value) - error > eps);
        return sum;
    }
}