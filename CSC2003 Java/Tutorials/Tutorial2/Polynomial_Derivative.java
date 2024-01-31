package Tutorial2;

import java.util.Scanner;

public class Polynomial_Derivative {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        String[] numString = input.nextLine().split(" ");
        int[] num = new int[numString.length];

        for(int i = 0; i < num.length; i++){
            num[i] = Integer.parseInt(numString[i]);
        }
        int cnt = 0;
        while (cnt < n) {
            cnt++;
            for (int i = 0; i < num.length - cnt; i++)
                num[i] = num[i + 1] * (i + 1);
        }

        for (int i = 0; i < num.length - cnt; i++)
            System.out.print(num[i] + " ");



        }
}
