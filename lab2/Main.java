import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String expression = "";
	    if (args.length > 0) {
            expression = args[0];
        } else {
            System.out.println("No cmd args found.");
        }

       	int x = 10;

        try {
            System.out.println("Result: " + evaluateExpression(expression, x));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
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
