package Assignment3;
import java.util.*;

public class Math_Expression2 {
    static Scanner input = new Scanner(System.in);

    public static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static int precedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "sin", "cos", "tan", "sqrt" -> 3;
            default -> -1;
        };
    }

    public static boolean isLeftAssociative(String op) {
        return switch (op) {
            case "+", "-", "*", "/", "sin", "cos", "tan", "sqrt" -> true;
            default -> false;
        };
    }

    // Reverse Polish Notation
    public static List<String> toRPN(String expression) {
        List<String> output = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                output.add(number.toString());
                i--; // Adjust back
            } else if (c == '+' || c == '-') {
                boolean isUnary = (i == 0 || expression.charAt(i - 1) == '(');
                if (isUnary) {
                    output.add("0"); // To ensure unary operations work correctly
                }
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(Character.toString(c)) && isLeftAssociative(operatorStack.peek())) {
                    output.add(operatorStack.pop());
                }
                operatorStack.push(Character.toString(c));
            } else if (c == '*' || c == '/') {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(Character.toString(c))) {
                    output.add(operatorStack.pop());
                }
                operatorStack.push(Character.toString(c));
            } else if (c == '(') {
                operatorStack.push("(");
            } else if (')' == c) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    output.add(operatorStack.pop());
                }
                operatorStack.pop(); // Pop the matching '('
            } else if ((expression.startsWith("sin", i)) ||
                    (expression.startsWith("cos", i)) ||
                    (expression.startsWith("tan", i)) ||
                    (expression.startsWith("sqrt", i))) {
                String func;
                if (expression.startsWith("sqrt", i)) {
                    func = "sqrt";
                } else {
                    func = expression.substring(i, i + 3);
                }
                operatorStack.push(func);
                i += func.length() - 1; // Advance the index
            }
            i++; // Move to the next character
        }

        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop());
        }

        return output;
    }

    // Calculate
    public static double evaluateRPN(List<String> rpn) {
        Stack<Double> stack = new Stack<>();

        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(performOperation(a, b, token));
            } else { // For functions like sin, cos, tan, sqrt
                double operand = stack.pop();
                stack.push(applyFunction(operand, token));
            }
        }
        return stack.pop();
    }

    public static double performOperation(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }

    public static double applyFunction(double a, String func) {
        return switch (func) {
            case "sin" -> Math.sin(a);
            case "cos" -> Math.cos(a);
            case "tan" -> Math.tan(a);
            case "sqrt" -> Math.sqrt(a);
            default -> throw new IllegalArgumentException("Invalid function: " + func);
        };
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(input.nextLine());
        for (int i = 0; i < T; i++) {
            String expression = input.nextLine().replaceAll(" ", ""); // Remove spaces
            List<String> rpn = toRPN(expression);
            double result = evaluateRPN(rpn);
            System.out.println((int) Math.round(result));
        }
    }
}
