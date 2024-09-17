package solution;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String input = args[0];
        } else {
            System.out.println("No cmd args found.");
        }

        String expression = "X+5-3+X-2";
        int x = 10;
        int result = evaluateExpression(expression, x);
        System.out.println("Result: " + result);
    }

    enum Operation {
        ADD,
        SUBTRACT
    }

    public static int evaluateExpression(String expression, int X) {
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-", true);
        int result = 0;
        Operation currentOperation = Operation.ADD;

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            switch (token) {
                case "+":
                    currentOperation = Operation.ADD;
                    break;
                case "-":
                    currentOperation = Operation.SUBTRACT;
                    break;
                default:
                    int value;
                    if (token.equals("X")) {
                    value = X;
                } else {
                    value = Integer.parseInt(token);
                }

                switch (currentOperation) {
                    case ADD:
                        result += value;
                        break;
                    case SUBTRACT:
                        result -= value;
                        break;
                }
            }
        }
        return result;
    }
}
