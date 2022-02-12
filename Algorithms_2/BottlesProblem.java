import java.util.Arrays;

class bottlesProblem {

    //-------------------------------------------
    // bottles Algorithm
    //-------------------------------------------
    public static int getIndex(int i, int j, int n) {
        return ((n + 1) * i) + j;
    }

    public static boolean[][] bottlesActions(int m, int n) {
        int dim = (m + 1) * (n + 1);
        boolean[][] mat = new boolean[dim][dim];

        /* ---Actions--- */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int index = getIndex(i, j, n);
                /* empty M */
                mat[index][getIndex(0, j, n)] = true;
                /* empty N */
                mat[index][getIndex(i, 0, n)] = true;
                /* fill M */
                mat[index][getIndex(m, j, n)] = true;
                /* fill N */
                mat[index][getIndex(i, n, n)] = true;
                /* M --> N */
                int m2n_out = Math.max(0, i + j - n);
                int m2n_in = Math.min(n, i + j);
                mat[index][getIndex(m2n_out, m2n_in, n)] = true;
                /* N --> M */
                int n2m_out = Math.max(0, i + j - m);
                int n2m_in = Math.min(m, i + j);
                mat[index][getIndex(n2m_in, n2m_out, n)] = true;
            }
        }
        return mat;
    }

    //-------------------------------------------
    // Floyd-Warshall Algorithm
    //-------------------------------------------
    public static String[][] pathMatrixFW(boolean[][] mat) {
        int n = mat.length;
        String[][] pathMat = new String[n][n];

        // init
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j]) {
                    pathMat[i][j] = i + "->" + j;
                } else {
                    pathMat[i][j] = " ";
                }
            }
        }

        // found path
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((!mat[i][j]) && (mat[i][k] && mat[k][j])) {
                        pathMat[i][j] = pathMat[i][k] + pathMat[k][j].substring(1);
                        mat[i][j] = true;
                    }
                }
            }
        }

        return pathMat;
    }

    //-------------------------------------------
    // num of connect components
    //-------------------------------------------
    public static void connectComponentsOfGraphBoolean(boolean[][] mat) {
        int[] numComponents = new int[mat.length];
        int counter = 0;

        for (int i = 0; i < mat.length; i++) {
            if (numComponents[i] == 0) {
                // init new connect components 
                counter++;
                numComponents[i] = counter;

                // add verticals to the current connect components 
                for (int j = i + 1; j < mat.length; j++) {
                    if (mat[i][j]/* connect */ && numComponents[j] == 0) {
                        numComponents[j] = counter;
                    }
                }
            }
        }
        listOfConnectComponents(counter, numComponents);
    }

    private static void listOfConnectComponents(int counter, int[] numComponents) {
        System.out.println("Connect Components Of Graph:");
        // build list's
        String[] connectVar = new String[counter];
        Arrays.fill(connectVar, "");
        for (int i = 0; i < numComponents.length; i++) {
            connectVar[numComponents[i] - 1] += (i + 1) + " -> ";
        }
        // print list's
        for (int i = 0; i < counter; i++) {
            System.out.println("Connect Component number " + (i + 1) + " : " + connectVar[i] + "||");
        }
        System.out.println();
    }

    //-------------------------------------------
    // connect graph
    //-------------------------------------------
    public static boolean isConnected(boolean[][] mat) { //O(n^2)
        for (boolean[] row : mat) {
            for (int col = 0; col < mat.length; col++) {
                if (!row[col]) return false;
            }
        }
        return true;
    }

    public static boolean isConnectedComplexN(boolean[][] mat) { //O(n)
        for (int i = 0; i < mat.length; i++) {
            if (!mat[0][i]) return false;
        }
        return true;
    }

    //-------------------------------------------
    // print Matrix
    //-------------------------------------------
    public static void printMat(boolean[][] mat) {
        System.out.println();
        for (boolean[] row : mat) {
            for (boolean col : row) {
                System.out.print(col + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printPath(String[][] pathMat) {
        System.out.println();
        for (String[] row : pathMat) {
            for (int j = 0; j < pathMat[0].length; j++) {
                System.out.printf("%25s", row[j] + "; ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // bottles Problem
        boolean[][] mat = bottlesActions(2, 1);
        System.out.println("Bottles Problem");
        printMat(mat);

        // path matrix
        boolean[][] mat2 = {
                {true, true, false, false, true},
                {true, true, true, false, false},
                {false, true, true, true, false},
                {false, false, true, true, true},
                {true, false, false, true, true}};
        System.out.println("Path Matrix + Connected Test");
        String[][] pathMat = pathMatrixFW(mat2);
        printPath(pathMat);

        System.out.println("O(n^2) || isConnected: " + isConnected(mat2)); // O(n**2)
        System.out.println("O(n) || isConnected: " + isConnectedComplexN(mat2)); // O(n)

        // Number Of Connect Components && Print
        boolean[][] mat3 = {
                {true, false, false, true, false, true, false},
                {false, true, true, false, true, false, true},
                {false, true, true, false, true, false, true},
                {true, false, false, true, false, true, false},
                {false, true, true, false, true, false, true},
                {true, false, false, true, false, true, false},
                {false, true, true, false, true, false, true}};
        System.out.println("\nNumber Of Connect Components\n");
        connectComponentsOfGraphBoolean(mat3);
    }
}