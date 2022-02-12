public class FloydWarshall{

    //-------------------------------------------
    // Floyd-Warshall Algorithm - Weighted Edge
    //-------------------------------------------
    public static int[][] distMatWeightedEdges(int[][] mat) {
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }
        return mat;
    }

    public static String[][] pathMatWeightedEdges(int[][] mat) {
        // init
        String[][] pathMat = initPath(mat);
        // build
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE) {
                        if (mat[i][k] + mat[k][j] < mat[i][j])
                            pathMat[i][j] = pathMat[i][k] + pathMat[k][j].substring(1);
                    }
                }
            }
        }
        return pathMat;
    }

    //-------------------------------------------
    // Floyd-Warshall Algorithm - Weighted Var
    //-------------------------------------------
    public static int[][] distMatWeightedVar(int[][] mat, int[] varWeight) {
        initMatWeightDist(mat, varWeight);
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[i][k] != 0
                            && mat[k][j] != Integer.MAX_VALUE && mat[k][j] != 0) {
                        mat[i][j] = Math.min(mat[i][j], (varWeight[i] + mat[i][k] + mat[k][j] + varWeight[j]) / 2);
                    }
                }
            }
        }
        return mat;
    }

    public static String[][] pathMatWeightedVar(int[][] mat, int[] varWeight) {
        // init
        String[][] pathMat = initPath(mat);
        initMatWeightDist(mat, varWeight);
        // build
        for (int k = 0; k < mat.length; k++) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (mat[i][k] != Integer.MAX_VALUE && mat[i][k] != 0
                            && mat[k][j] != Integer.MAX_VALUE && mat[k][j] != 0) {
                        int varPath = (varWeight[i] + mat[i][k] + mat[k][j] + varWeight[j]) / 2;
                        if (varPath < mat[i][j]) {
                            pathMat[i][j] = pathMat[i][k] + pathMat[k][j].substring(1);
                            mat[i][j] = varPath;
                        }
                    }
                }
            }
        }
        return pathMat;
    }

    public static void initMatWeightDist(int[][] mat, int[] varWeight) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = varWeight[i] + varWeight[j];
                }
            }
        }
    }

    public static String[][] initPath(int[][] mat) {
        String[][] pathMat = new String[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] == Integer.MAX_VALUE)
                    pathMat[i][j] = "";
                else pathMat[i][j] = i + "->" + j;
            }
        }
        return pathMat;
    }

    //-------------------------------------------
    // print Matrix
    //-------------------------------------------
    public static void printMat(int[][] mat) {
        System.out.println();
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                System.out.print(mat[row][col] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printPath(String[][] pathMat) {
        System.out.println();
        for (int i = 0; i < pathMat.length; i++) {
            for (int j = 0; j < pathMat[0].length; j++) {
                System.out.printf("%25s", pathMat[i][j] + "; ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 4, Integer.MAX_VALUE, 3},
                {4, 0, 15, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 15, 0, 1},
                {3, Integer.MAX_VALUE, 1, 0}};

        printPath(pathMatWeightedEdges(mat)); // path matrix - weighted edge
        printMat(distMatWeightedEdges(mat)); // dist matrix - weighted edge

        int[][] mat2 = {{0, 1, Integer.MAX_VALUE, 1},
                {1, 0, 1, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 1, 0, 1},
                {1, Integer.MAX_VALUE, 1, 0}};
        int[] var = {4, 3, 2, 5};

        printPath(pathMatWeightedVar(mat2, var)); // dist matrix - weighted vars
        printMat(distMatWeightedVar(mat2, var)); // dist matrix - weighted vars
    }
}
