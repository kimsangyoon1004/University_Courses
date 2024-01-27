package Assignment1;

import java.util.*;
public class Prime {
    static Scanner input = new Scanner(System.in);

    // here is the function you need to implement
    public static void parse(int N) {
        for(int i = 1; i <= N; i++){        // check numbers below N
            int count = 0;
            for(int j = 1; j <= i; j++){
                if(i % j == 0){
                    count++;
                }
            }
            if(count == 2){         // Counter == 2 means the number is a prime number and can only be divided by 1 and itself
                System.out.print(i + " ");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(input.nextLine());
        parse(N);
    }
}
