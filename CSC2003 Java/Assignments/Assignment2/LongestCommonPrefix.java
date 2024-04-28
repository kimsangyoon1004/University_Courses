package Assignment2;


import java.util.*;

public class LongestCommonPrefix {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(input.nextLine());

        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = input.nextLine();
        }

        // Find the shortest word for efficiency
        String shortest = strs[0];
        for (String str : strs) {
            if (str.length() < shortest.length()) {
                shortest = str;
            }
        }

        // Check other words with the shortest word and add to prefix if match
        StringBuilder commonPrefix = new StringBuilder();
        for (int i = 0; i < shortest.length(); i++) {
            char c = shortest.charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != c) {
                    System.out.println(commonPrefix);
                    return;
                }
            }
            commonPrefix.append(c);
        }

        System.out.println(commonPrefix);
    }
}

