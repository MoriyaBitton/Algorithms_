public class MaxSubMatrix {

    // Complex: O(n^6)
    public static void maxSumSubMatrixFullSearch(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int maxSum = 0, rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0;
        // all matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // sub matrix to compare to max
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        int sum = 0;
                        // sum matrix in range [i, k], [j, l]
                        for (int x = i; x <= k; x++) {
                            for (int y = j; y <= l; y++) {
                                sum += mat[x][y];
                            }
                        }
                        // update max
                        if (maxSum < sum) {
                            maxSum = sum;
                            rowStart = i;
                            rowEnd = k;
                            colStart = j;
                            colEnd = l;
                        }
                    }
                }
            }
        }
        System.out.println("Max Sub Matrix: " + maxSum + "\nIndexes: (" + rowStart + ", " + colStart + " : " + rowEnd + ", " + colEnd + ")\n");
    }

    // Complex: O(n^4)
    public static void maxSumSubMatrixHelpArr(int[][] mat) {
        int n /*row num*/ = mat.length, m /*col num*/ = mat[0].length;
        int maxSum = 0, rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0;
        // Sub matrix from row i to row j
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] helpArr = new int[m];
                // init helpArr by sum rows sub matrix
                for (int k = i; k <= j; k++) { /*rows range: (i,j)*/
                    for (int l = 0; l < m; l++) { /*rows range: (1,m)*/
                        helpArr[l] += mat[k][l];
                    }
                }
                // Best Algorithm on helpArr
                int[] res = best(helpArr);
                if (maxSum < res[0]) {
                    maxSum = res[0];
                    rowStart = i;
                    rowEnd = j;
                    colStart = res[1];
                    colEnd = res[2];
                }
            }
        }
        System.out.println("Max Sub Matrix: " + maxSum + "\nIndexes: (" + rowStart + ", " + colStart + " : " + rowEnd + ", " + colEnd + ")\n");
    }

    // Complex: O(n^4)
    public static void maxSumSubMatrixHelpMatrix(int[][] mat) {
        int n /*row num*/ = mat.length, m /*col num*/ = mat[0].length;
        int maxSum = 0, rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0, sum;
        // init mat
        int[][] helpMatrix = new int[n][m];
        initHelpMatrix(helpMatrix, mat);
        // get sum of bounds
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // fill helpMatrix
                for (int k = i; k < n; k++) {
                    for (int l = j; l < m; l++) {
                        if (i == 0 && j == 0) sum = helpMatrix[k][l];
                        else if (i == 0) sum = helpMatrix[k][l] - helpMatrix[k][j - 1];
                        else if (j == 0) sum = helpMatrix[k][l] - helpMatrix[i - 1][l];
                        else
                            sum = helpMatrix[k][l] - helpMatrix[k][j - 1] - helpMatrix[i - 1][l] + helpMatrix[i - 1][j - 1];
                        // update max and range indexes of max sum sub matrix
                        if (maxSum < sum) {
                            maxSum = sum;
                            rowStart = i;
                            rowEnd = k;
                            colStart = j;
                            colEnd = l;
                        }
                    }
                }
            }
        }
        System.out.println("Max Sub Matrix: " + maxSum + "\nIndexes: (" + rowStart + ", " + colStart + " : " + rowEnd + ", " + colEnd + ")\n");
    }

    private static void initHelpMatrix(int[][] help, int[][] mat) {
        int n /*row num*/ = mat.length, m /*col num*/ = mat[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) help[i][j] = mat[i][j];
                else if (i == 0) help[0][j] = help[0][j - 1] + mat[0][j];
                else if (j == 0) help[i][0] = help[i - 1][0] + mat[i][0];
                else help[i][j] = mat[i][j] + help[i][j - 1] + help[i - 1][j] - help[i - 1][j - 1];
            }
        }
    }

    // Complex: O(n*m^2) ~ O(n^3)
    public static void maxSumSubMatrixSuperBest(int[][] mat) {
        int n /*row num*/ = mat.length, m /*col num*/ = mat[0].length;
        int maxSum = Integer.MIN_VALUE, rowStart = 0, rowEnd = 0, colStart = 0, colEnd = 0;

        for (int i = 0; i < m; i++) {
            // init helpArr with zero
            int[] helpArr = new int[n];
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    helpArr[k] += mat[k][j];
                }
                // Best Algorithm on helpArr
                int[] res = best(helpArr);
                if (maxSum < res[0]) {
                    maxSum = res[0];
                    rowStart = res[1];
                    rowEnd = res[2];
                    colStart = i;
                    colEnd = j;
                }
            }
        }

        System.out.println("Max Sub Matrix: " + maxSum + "\nIndexes: (" + rowStart + ", " + colStart + " : " + rowEnd + ", " + colEnd + ")\n");
    }

    private static int[] best(int[] arr) {
        int[] help = new int[arr.length];
        int sumMax = arr[0], start = 0, end = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                help[i] = arr[i];
                start = i;
            } else if (help[i - 1] <= 0) {
                help[i] = arr[i];
                start = i;
            } else {
                help[i] = help[i - 1] + arr[i];
            }
            if (sumMax < help[i]) {
                sumMax = help[i];
                end = i;
            }
        }
        return new int[]{sumMax, start, end};
    }

    public static void main(String[] args) {
        int[][] mat1 = {{2, 10, 8, 3},
                {-8, 14, -1, 4},
                {-6, -1, 8, -2},
                {1, 8, 7, 3},
                {8, 2, -10, -8}};
        // sumMax=61, iStart=0, jStart=1, iEnd=3, jEnd=3

        int[][] mat2 = {{1, 2, -1},
                {-1, 0, 1},
                {1, -5, -2}};
        // sumMax=3, iStart=0, jStart=0, iEnd=0, jEnd=1

        int[][] mat3 = {{2, -8, -6, 1, 8},
                {10, 14, -1, 8, 2},
                {8, -1, 8, 7, -10},
                {3, 4, -2, 3, -8}};
        // sumMax=61, iStart=1, jStart=0, iEnd=3, jEnd=3

        int[][] mat4 = {{1, 2, -1},
                {-1, 0, 1},
                {1, -5, -2},
                {4, -1, -1}};
        // sumMax=5, iStart=0||2, jStart=0, iEnd=3, jEnd=0

        int[][] mat5 = {{-10, 5, -4, 3, 4},
                {4, -100, 10, -30, 5},
                {3, 2, 8, 1, 6},
                {-5, 2, -20, 3, 1}};
        // sumMax=20, iStart=2, jStart=0, iEnd=2, jEnd=4

        int[][] mat6 = {{2,1,-3,-4,5},
                        {0,6,3,4,1},
                        {2,-2,-1,4,-5},
                        {-3,3,1,0,3}};
        // sumMax=18, iStart=1, jStart=1, iEnd=3, jEnd=3

        int[][][] tests = {mat1, mat2, mat3, mat4, mat5, mat6};
        for (int i = 0; i < tests.length; i++) {
            maxSumSubMatrixFullSearch(tests[i]);
            maxSumSubMatrixHelpArr(tests[i]);
            maxSumSubMatrixHelpMatrix(tests[i]);
            maxSumSubMatrixSuperBest(tests[i]);
        }
    }
}
