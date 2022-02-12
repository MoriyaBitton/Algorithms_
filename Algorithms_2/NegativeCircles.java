public class NegativeCircles {

    public static int[][] FwNegative(int[][] mat) {
        int n = mat.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }
        return mat;
    }

    public static String[][] pathMatrix(int[][] mat) {
        String[][] pathMat = initPath(mat);
        int n = mat.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE && (mat[i][k] + mat[k][j] < mat[i][j])) {
                        pathMat[i][j] = pathMat[i][k] + pathMat[k][j].substring(1);
                    }
                }
            }
        }
        return pathMat;
    }

    private static String[][] initPath(int[][] mat) {
        int n = mat.length;
        String[][] pathMat = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pathMat[i][j] = i + "->" + j;
            }
        }
        return pathMat;
    }

    // ----------------------------------------------
    // Undirected graph with negative-weight edges
    // ----------------------------------------------
    public static boolean negativeCirclesUndirectedGraph(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = i + 1; j < mat.length; j++) {
                if (mat[i][j] < 0) return true;
            }
        }
        return false;
    }

    // ----------------------------------------------
    // Directed graph with negative-weight edges
    // ----------------------------------------------
    public static boolean negativeCirclesDirectedGraph(int[][] mat) {
        int[][] newMat = FwNegative(mat);
        for (int i = 0; i < mat.length; i++) {
            if (newMat[i][i] < 0) return true;
        }
        return false;
    }

    public static void printMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == Integer.MAX_VALUE) {
                    if (i == j) System.out.print("0, ");
                    else System.out.print("*, ");
                } else
                    System.out.print(mat[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Undirected graph
        int[][] mat0 = {{0, 4, -20}, {4, 0, 8}, {-20, 8, 0}};
        System.out.println("\nUndirected graph with negative-weight edges: " + negativeCirclesUndirectedGraph(mat0));

        System.out.println("\nBefore FW:");
        printMatrix(mat0);

        System.out.println("\nAfter FW:");
        printMatrix(FwNegative(mat0));

        int[][] mat1 = {{0, 4, -2}, {4, 0, 8}, {-2, 8, 0}};
        System.out.println("\nUndirected graph with negative-weight edges: " + negativeCirclesUndirectedGraph(mat1));

        System.out.println("\nBefore FW:");
        printMatrix(mat1);

        System.out.println("\nAfter FW:");
        printMatrix(FwNegative(mat1));

        // Directed graph
        int[][] mat2 = {{0, 4, -10}, {0, 0, 2}, {0, 0, 0}};
        System.out.println("\nDirected graph with negative-weight edges: " + negativeCirclesDirectedGraph(mat2));

        System.out.println("\nBefore FW:");
        printMatrix(mat2);

        System.out.println("\nAfter FW:");
        printMatrix(mat2);
    }
}
