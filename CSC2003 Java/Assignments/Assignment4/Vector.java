package Assignment4;
import java.util.*;
import java.util.stream.Stream;

public class Vector {

    private int[] elements;
    public Vector(int[] elements) {
        this.elements = elements.clone();
    }
    public static Vector plus(Vector v1, Vector v2){
        int vec_length = v1.elements.length;
        int[] sum = new int[vec_length];
        for(int i = 0; i < vec_length; i++){
            sum[i] = v1.elements[i] + v2.elements[i];
        }
        return new Vector(sum);
    }
    public static Vector subtract(Vector v1, Vector v2){
        int vec_length = v1.elements.length;
        int[] minus = new int[vec_length];
        for(int i = 0; i < vec_length; i++){
            minus[i] = v1.elements[i] - v2.elements[i];
        }
        return new Vector(minus);
    }
    public Vector multiply(int a){
        int vec_length = elements.length;
        int[] product = new int[vec_length];
        for(int i = 0; i < vec_length; i++){
            product[i] = elements[i] * a;
        }
        return new Vector(product);
    }
    public static int dot(Vector v1, Vector v2){
        int dot_product = 0;
        for(int i = 0; i < v1.elements.length; i++){
            dot_product += v1.elements[i] * v2.elements[i];
        }
        return dot_product;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
            if (i < elements.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }



    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int vec_num = Integer.parseInt(input.nextLine());
        String[] vector1 = input.nextLine().split(" ");
        String[] vector2 = input.nextLine().split(" ");
        int vec_size = vector1.length;
        int[] vec1 = new int[vec_size];
        int[] vec2 = new int[vec_size];
        for(int i = 0; i < vec_size; i++){
            vec1[i] = Integer.parseInt(vector1[i]);
            vec2[i] = Integer.parseInt(vector2[i]);
        }
        Vector v1 = new Vector(vec1);
        Vector v2 = new Vector(vec2);
        Vector sum = Vector.plus(v1, v2);
        System.out.println(sum.toString());
    }
}
