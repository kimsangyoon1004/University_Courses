package Assignment2;

import java.util.*;
public class Matrix {
    static Scanner input = new Scanner(System.in);

    // Matrix multiplication function
    public static  int[][] Matrix_multiplication(int[][] matrixA, int[][] matrixB){
        int[][] mul_matrix = new int[matrixA.length][matrixB[0].length];
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixB[0].length; j++){
                for(int k = 0; k < matrixA[0].length; k++){
                    mul_matrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return mul_matrix;
    }

    // Matrix addition function
    public static int[][] Matrix_addition(int[][] matrixA, int[][] matrixB){
        int[][] add_matrix = new int[matrixA.length][matrixB[0].length];
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixB[0].length; j++){
                add_matrix[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return add_matrix;
    }

    // Print matrix function
    public static void printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        // print
        for (int[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                sb.append(row[j]);
                if (j < row.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    public static void main(String[] args) throws Exception {
        int T = input.nextInt();  // Types of Matrix
        for(int i = 0; i < T; i++){
            int m, n, p, q, r;      // Read each dimension
            m = input.nextInt();
            n = input.nextInt();
            p = input.nextInt();
            q = input.nextInt();
            r = input.nextInt();

            int[][] matrixA = new int[m][n];
            int[][] matrixB = new int[p][q];

            // matrix A
            for(int a_row = 0; a_row < m; a_row++){
                for(int a_col = 0; a_col < n; a_col++){
                    matrixA[a_row][a_col] = input.nextInt();
                }
            }
            // matrix B
            for(int b_row = 0; b_row < p; b_row++){
                for(int b_col = 0; b_col < q; b_col++){
                    matrixB[b_row][b_col] = input.nextInt();
                }
            }
            // matrix multiplication if r = 0, else add matrix
            int[][] result;
            if(r == 0){
                result = Matrix_multiplication(matrixA, matrixB);
            }
            else {
                result = Matrix_addition(matrixA, matrixB);
            }
            printMatrix(result);
        }
    }
}


