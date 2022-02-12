public class Question1 {

    // Complexity: O(l1*l2 + l2) = O(l1*l2)
    public static int lcsIncrease(int[] X, int[] Y) {
        int l1 = X.length, l2 = Y.length;
        int[][] matrix = new int[l1 + 1][l2 + 1];

        // init row 0 and col 0
        for (int i = 0; i < l1 + 1; i++) {
            matrix[1][0] = 0;
        }
        for (int j = 0; j < l2 + 1; j++) {
            matrix[0][j] = 0;
        }

        // init the rest of matrix
        for (int i = 1; i < l1 + 1; i++) { // O(l1 * l2)
            int temp = 0;
            for (int j = 1; j < l2 + 1; j++) {
                if (X[i - 1] > Y[j - 1]) {
                    int max = Math.max(temp, matrix[i - 1][j]);
                    temp = max;
                    matrix[i][j] = temp;
                }
                if (X[i - 1] == Y[j - 1]) matrix[i][j] = temp + 1;
            }
        }

        for (int j = 2; j < l2 + 1; j++) { // O(l2)
            int val = Integer.max(matrix[l1][j], matrix[l1][j - 1]);
            matrix[l1][j] = val;
        }

        int ans = matrix[l1][l2];
        return ans;
    }
}
