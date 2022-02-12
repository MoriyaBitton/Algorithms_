package src;

public class SCS {

    // Shortest Common SuperSequence
    int[][] mat;
    int n, m;
    String X, Y;

    public SCS(String X, String Y) {
        this.X = X;
        this.Y = Y;
        buildLCSMatrix();
    }

    // src.LCS
    // Complexity: O(n*m)
    private void buildLCSMatrix() {
        n = X.length() + 1;
        m = Y.length() + 1;
        mat = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }
    }

    // src.SCS(X,Y) = |X| + |Y| - |src.LCS(X,Y)|, length
    public int getSCSLength() {
        return X.length() + Y.length() - mat[n - 1][m - 1];
    }

    // src.SCS - returns the string
    // Complexity: O(n*m)
    public String getSCSString() {
        return getSCSString(n - 1, m - 1);
    }

    private String getSCSString(int i, int j) {
        if (i == 0 && j == 0) return "";
        if (i == 0) {
            return getSCSString(i, j - 1) + Y.charAt(j - 1);
        }
        if (j == 0) {
            return getSCSString(i - 1, j) + X.charAt(i - 1);
        }
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            return getSCSString(i - 1, j - 1) + X.charAt(i - 1);
        } else {
            if (mat[i - 1][j] > mat[i][j - 1]) {
                return getSCSString(i - 1, j) + X.charAt(i - 1);
            } else {
                return getSCSString(i, j - 1) + Y.charAt(j - 1);
            }
        }
    }

    public static void main(String[] args) {
        SCS scs = new SCS("shortest", "string");
        System.out.println("String: " + scs.getSCSString() + " len: " + scs.getSCSLength());
    }
}
