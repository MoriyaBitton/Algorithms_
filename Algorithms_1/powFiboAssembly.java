package src;

public class powFiboAssembly {

    private static int[][] F = {{1, 1}, {1, 0}};
    private static int action_rec = 0;
    private static int action_loop = 0;

    // pow
    // Complexity: O(log(n))
    public static int powLogN(int a, int p) { // O(log(n))
        if (p == 0) return 1;
        if (p % 2 == 0) return powLogN(a * a, p / 2);
        else return (a * powLogN(a * a, (p - 1) / 2));
    }

    // fibo by matrix
    // Complexity: O(log(n))
    public static int fiboN(int n) { // O(log(n))
        int[][] ans = fiboRecursive(F, n - 1);
        return ans[0][0];
    }

    private static int[][] fiboRecursive(int[][] matrix, int n) {
        int[][] zeroN = {{1, 0}, {0, 1}};
        if (n == 0) return zeroN;
        if (n % 2 == 0) return fiboRecursive(kefelMatrix(matrix, matrix), n / 2);
        else return kefelMatrix(matrix, fiboRecursive(kefelMatrix(matrix, matrix), (n - 1) / 2));
    }

    private static int[][] kefelMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] matrixAns = new int[2][2];
        matrixAns[0][0] = matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0];
        matrixAns[0][1] = matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1];
        matrixAns[1][0] = matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0];
        matrixAns[1][1] = matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1];
        return matrixAns;
    }

    // num of nodes fibo tree
    // Complexity: O(n)
    public static int fiboTreeNumOfNodes_rec(int n) {
        action_rec++;
        int numOfNodes = 0;
        if (n < 2) return 1;
        numOfNodes = fiboTreeNumOfNodes_rec(n - 1) + fiboTreeNumOfNodes_rec(n - 2) + 1;
        return numOfNodes;
    }

    // Complexity: O(n) (rec = O(2^n))
    public static int fiboTreeNumOfNodes_loop(int n) {
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            action_loop++;
            f[i] = f[i - 1] + f[i - 2] + 1;
        }
        return f[n];
    }

    // assembly
    // Complexity: O(n)
    public static int assembly(int n) {
        if (n < 2) return 1;
        int ans = n * (assembly(n - 1));
        return ans;
    }

    public static void main(String[] args) {
        int a = 5, p = 2;
        System.out.println(a + "^" + p + " = " + powLogN(a, p));
        int n = 15;
        System.out.println("f(" + n + ") = " + fiboN(n));
        System.out.println(n + "! = " + assembly(n));
        int _n = 4;
        System.out.println("the recurs tree of f(" + _n + "), contains " + fiboTreeNumOfNodes_rec(_n) + " nodes." + '\n' + "rec- num of action: " + action_rec);
        System.out.println("the recurs tree of f(" + _n + "), contains " + fiboTreeNumOfNodes_loop(_n) + " nodes." + '\n' + "loop- num of action: " + action_loop);

    }
}
