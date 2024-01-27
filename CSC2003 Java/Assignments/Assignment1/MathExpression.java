package Assignment1;

import java.util.*;

public class MathExpression {
    static Scanner input = new Scanner(System.in);

    // here is the function you need to implement
    // s1, s2, s3 are the integer 1, operation, integer 2 of each line respectively
    public static void parse_line(String s1, String s2, String s3) {
        int integer_1 = Integer.parseInt(s1);
        int integer_3 = Integer.parseInt(s3);
        switch (s2) {
            case "+" -> System.out.println(integer_1 + integer_3);
            case "-" -> System.out.println(integer_1 - integer_3);
            case "*" -> System.out.println(integer_1 * integer_3);
            case "/" -> System.out.println(integer_1 / integer_3);
        }
    }
    public static void main(String[] args) throws Exception {
        int line_number = Integer.parseInt(input.nextLine());
        for(int i = 0; i < line_number; i++) {
            String s = input.nextLine();
            String t[] = s.split(" ");
            parse_line(t[0], t[1], t[2]);
        }

    }
}
