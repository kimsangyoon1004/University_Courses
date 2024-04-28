package Assignment3;

import java.util.*;
public class Banish_Letter {
    static Scanner input = new Scanner(System.in);

    public static void banish(String []banish_letter, String letter){

        for (String s : banish_letter) {
            letter = letter.replaceAll(s, "");
        }
        System.out.println(letter);


    }
    public static void main(String[] args) throws Exception {
        String banish_letter = input.nextLine();
        String []banish_letters_array = banish_letter.split(" ");
        int T = Integer.parseInt(input.nextLine());
        String []letters = new String[T];
        for(int i = 0; i < T; i++){
            letters[i] = input.nextLine();
            banish(banish_letters_array, letters[i]);
        }



    }
}
