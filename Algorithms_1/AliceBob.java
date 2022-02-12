package src;

import java.util.Arrays;

public class AliceBob {

    public static int[] randCoin(){
        int i = (int) (Math.random()*4);
        int[][] mat = new int[][] {{0,1}, {0,0}, {1,0}, {1,1}};
        return mat[i];
    }

    public static boolean guess(){
        int[] game = randCoin();
        int Alice = game[0], Bob = (int) Math.pow((game[1] -1), 2);
        System.out.println("Alice guss: " + Alice + " Bob guss: " + Bob);
        if(Alice == game[0] || Alice == game[1] || Bob == game[0] || Bob == game[1]){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("the permutation: " + Arrays.toString(randCoin()) + "\n" + "result: " + guess());
    }
}
