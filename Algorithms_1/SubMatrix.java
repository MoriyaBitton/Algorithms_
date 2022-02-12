package src;

import java.util.Stack;

public class SubMatrix {

    // Biggest square of "1"
    // Complexity: O(n*m)
    public static int getBiggestSquare(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] help = new int[n][m];
        int max = 0, imax = 0, jmax = 0;
        for (int i = 0; i < n; i++) {
            help[i][0] = matrix[i][0];
        }
        for (int j = 0; j < m; j++) {
            help[0][j] = matrix[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    help[i][j] = min(matrix[i - 1][j], matrix[i][j - 1], matrix[i - 1][j - 1]) + 1;
                    if (help[i][j] > max) {
                        max = help[i][j];
                        imax = i;
                        jmax = j;
                    }
                }
            }
        }
        return max;
    }

    private static int min(int matrix, int matrix1, int matrix2) {
        int min = Math.min(matrix, matrix1);
        int ans = Math.min(min, matrix2);
        return ans;
    }

    //The biggest rectangle of ones in matrix (m,n) of ones and zeros.
    // Complexity: O(n*m)
    public static int maxOnesRec(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] sumOfCols = new int[row][col];
        for (int i = 0; i < col; i++) {
            sumOfCols[0][i] = mat[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sumOfCols[i][j] = mat[i][j] == 0 ? 0 : (sumOfCols[i - 1][j] + 1);
            }
        }
        int max = 0;
        for (int i = 0; i < row; i++) {
            int area = maxHist(sumOfCols[i]);
            if (area > max) {
                max = area;
            }
        }
        return max;
    }

    public static int maxHist(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int top, area, max = 0;
        int i = 0;
        while (i < n) {
            if (st.isEmpty() || arr[st.peek()] <= arr[i]) {
                st.push(i++);
            } else {
                top = st.pop();
                area = arr[top] * (st.isEmpty() ? i : i - st.peek() - 1);
                if (area > max) {
                    max = area;
                }
            }
        }
        while (!st.isEmpty()) {
            top = st.pop();
            area = arr[top] * (st.isEmpty() ? i : i - st.peek() - 1);
            if (area > max) {
                max = area;
            }
        }
        return max;
    }

    // largest Plus
    // Complexity: O(n*m)
    public static int largestPlus(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] u = new int[n][m];
        int[][] d = new int[n][m];
        int[][] l = new int[n][m];
        int[][] r = new int[n][m];
        for (int i = 0; i < m; i++) {
            u[0][i] = mat[0][i];
        }
        for (int i = 0; i < m; i++) {
            d[n - 1][i] = mat[n - 1][i];
        }
        for (int i = 0; i < n; i++) {
            l[i][0] = mat[i][0];
        }
        for (int i = 0; i < n; i++) {
            r[i][m - 1] = mat[i][m - 1];
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != 0) {
                    if (i != 0) {
                        u[i][j] = u[i - 1][j] + 1;
                    }
                    if (j != 0) {
                        l[i][j] = l[i][j - 1] + 1;
                    }
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    if (i != n - 1) {
                        d[i][j] = d[i + 1][j] + 1;
                    }
                    if (j != m - 1) {
                        r[i][j] = r[i][j + 1] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (Math.min(Math.min(u[i][j], d[i][j]), Math.min(l[i][j], r[i][j])) > max) {
                    max = Math.min(Math.min(u[i][j], d[i][j]), Math.min(l[i][j], r[i][j]));
                }
            }
        }
        return (max - 1) * 4 + 1;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 0, 0}};
        System.out.println("Biggest square of '1': " + getBiggestSquare(mat));
        System.out.println("Biggest square of '1': " + maxOnesRec(mat));
        System.out.println("Largest plus of '1': " + largestPlus(mat));
    }
}
