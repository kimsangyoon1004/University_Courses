package Assignment2;

import java.util.*;

public class Power {
    static Scanner input = new Scanner(System.in);

    public static int pow(int n, int m, int x){
        if((n == 1) && (m != 1)){                 // case when n = 1
            return 0;
        }
        else if(m == 1){
            return 0;
        }
        else if(n == 0){                           // case when n = 0
            return 0;
        }
        else if(m == (int)Math.pow(n, x)){       // if m = n^x -> true
            return 1;
        }
        else if((int)Math.pow(n,x) > m){     // if n^x > m -> false
            return 0;
        }
        else{
            return pow(n, m, x + 1);      // Before it reaches true or false, continue recursion
        }
    }

    public static void main(String[] args) throws Exception {
        int m = input.nextInt();
        int n = input.nextInt();
       if(pow(n, m, 0) == 1){
           System.out.println("true");
       }
       else{
           System.out.println("false");
       }
    }
}
