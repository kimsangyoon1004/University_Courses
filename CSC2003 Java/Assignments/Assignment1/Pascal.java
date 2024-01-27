package Assignment1;

import java.util.*;
public class Pascal {
    static Scanner input = new Scanner(System.in);

    // here is the function you need to implement
    public static void parse(int N) {
        // Assign double array for given size N Rows
        int[][] triangle = new int[N][];

        for (int row = 0; row < N; row++) {
            triangle[row] = new int[row + 1];       // Assign different column size for each row
            for (int column = 0; column <= row; column++) {
                if (column == 0 || column == row)       // Make first and last element of each row to 1
                    triangle[row][column] = 1;
                else
                    triangle[row][column] = triangle[row - 1][column - 1] + triangle[row - 1][column]; // triangle[4][1] = triangle[3][0] + triangle[3][1], triangle[4][2] = triangle[3][1] + triangle[3][2]
                System.out.print(triangle[row][column] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(input.nextLine());
        parse(N);


    }
}
