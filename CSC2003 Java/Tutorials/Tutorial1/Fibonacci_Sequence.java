package Tutorial1;

import java.lang.System;
import java.util.Scanner;

public class Fibonacci_Sequence {

    public static void Fibonacci(String s1, String s2){
        int integer_1 = Integer.parseInt(s1);
        int integer_3 = Integer.parseInt(s2);
        int[] sequence = new int[integer_1 + 1];
        sequence[0] = 1;
        sequence[1] = 1;
        for(int i = 2; i < integer_1; i++){
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int line_number = Integer.parseInt(input.nextLine());
        for(int i = 0; i < line_number; i++){
            String s = input.nextLine();
            String t[] = s.split(", ");
            Fibonacci(t[0], t[1]);
        }


    }
}

