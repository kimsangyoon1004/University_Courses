package Assignment3;
import java.util.*;
public class Word_Searching {
    static Scanner input = new Scanner(System.in);

    public static boolean search(String[][] map, String word){
        int row = map.length;
        int column = map[0].length;
        boolean[][] visited = new boolean[row][column];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(dfs(i, j, map, word, 0, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(int row, int column, String[][] map, String word, int index, boolean[][] visited){
        if (index == word.length()) {
            return true;
        }
        if(row >= map.length || row < 0 || column >= map[0].length || column < 0 || visited[row][column] || !map[row][column].equals(String.valueOf(word.charAt(index)))){
            return false;
        }
        visited[row][column] = true;
        boolean found = dfs(row, column + 1, map, word, index + 1, visited) ||
                        dfs(row, column - 1, map, word, index + 1, visited) ||
                        dfs(row + 1, column, map, word, index + 1, visited) ||
                        dfs(row - 1, column, map, word, index + 1, visited);
        visited[row][column] = false;
        return found;
    }
    public static void main(String[] args) throws Exception{
        String first_line = input.nextLine();
        String []row_col = (first_line.split(" "));
        int row = Integer.parseInt(row_col[0]);
        int column = Integer.parseInt(row_col[1]);

        String [][]map = new String[row][column];
        for(int m = 0; m < row; m++){
            String rows = input.nextLine();
            for(int n = 0; n < column; n++){
                map[m] = rows.split(" ");
            }
        }
        int T = Integer.parseInt(input.nextLine());
        for(int i = 0; i < T; i++){
            String word = input.nextLine();
            boolean result = search(map, word);
            System.out.println(result);
        }
    }
}
