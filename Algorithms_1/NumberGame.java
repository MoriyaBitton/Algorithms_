package src;

public class NumberGame {

    // f(i,j) = max (ai - f(i+1,j), aj - f(i,j-1))
    // Complexity: O(n^2)
    public static int[][] buildMatrix (int[] game){
        int n = game.length;
        int[][] matGame = new int[n][n];
        for (int i = 0; i < n; i++) {
            matGame[i][i] = game[i];
        }
        for (int i = (n-2); i > (-1) ; i--) {
            for (int j = (i+1); j < (n-1); j++) {
                matGame[i][j] = Math.max(game[i] - matGame[i+1][j], game[j] - matGame[i][j-1]);
            }
        }
        return matGame;
    }

    public static int maxUtility (int[] game){
        int i =0, n = game.length, j = (n-1), first = 0, second = 0, sumOfFirst = 0, sumOfSecond = 0;
        int[][] matGame = buildMatrix(game);
        for (int k = 0; k < (n/2); k++) {
            if ((game[i] - matGame[i+1][j]) > (game[j] - matGame[i][j-1])){ //first
                sumOfFirst += game[i];
                first = i++;
            } else {
                sumOfFirst += game[j];
                first = j--;
            }

            if(i!=j){
                if((game[i] - matGame[i+1][j]) > (game[j] - matGame[i][j-1])){
                    sumOfSecond += game[i];
                    second = i++;
                } else {
                    sumOfSecond += game[j];
                    second = j--;
                }
            } else { //j=0 || i=(n-1)
                second = j;
                sumOfSecond += game[j];
            }
            System.out.println("first: " + first + " second: " + second);
        }
        System.out.println("sum of first: " + sumOfFirst + " sum of second: " + sumOfSecond);
    return (sumOfFirst - sumOfSecond);
    }

    public static void main(String[] args) {
        int[] game = new int[] {1, 3, 5, 7, 0, 8, 0, 8};
        System.out.println("Biggest Utility: " + maxUtility(game));
    }
}
