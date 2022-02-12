import java.util.Arrays;

public class TreeFromDegreesArray {

    public static boolean isGraph(int[] degrees) {
        boolean ans = true;
        int sumDeg = 0;
        for (int degree : degrees) {
            sumDeg += degree;
        }
        if (sumDeg % 2 == 1) ans = false;
        for (int k = 0; ans && k < degrees.length; k++) {
            int sumLeft = 0;
            for (int i = 0; i <= k; i++) sumLeft += degrees[i];

            int sumRight = k * (k + 1);
            for (int i = k + 1; i < degrees.length; i++) sumRight += Math.min(k + 1, degrees[i]);
            if (sumLeft > sumRight) ans = false;
        }
        return ans;
    }

    public static int[] BuildTreeFromDegreesArray(int[] degrees) {
        int V = degrees.length;

        // sigma of deg(v) || v in V
        int sum = 0;
        for (int deg : degrees) {
            sum += deg;
        }

        // Sum of deg(v in V) = 2 * (|V| - 1)
        if (sum / 2 != (V - 1)) {
            System.out.println("Not a tree degrees array");
            return new int[1];
        }

        // connect leaves to the first vertx who is nor leave
        // as long as this vertx degrees is higher then one
        Arrays.sort(degrees);
        int internalVertex = notLeave(degrees);
        int[] tree = new int[V];
        for (int i = 0; i < V - 2; i++) {
            tree[i] = internalVertex;
            degrees[internalVertex]--;
            if (degrees[internalVertex] == 1) internalVertex++;
        }
        tree[V - 1] = -1;
        return tree;
    }

    private static int notLeave(int[] degrees) {
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] > 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        /* TEST 1 */
        int[] degree = {1, 1, 1, 1, 2, 2, 3, 3};
        System.out.println("\nTEST 1");
        System.out.println("is Graph? " + isGraph(degree));
        System.out.println(Arrays.toString(BuildTreeFromDegreesArray(degree)));

        /* TEST 2 */
        degree = new int[]{4, 3, 2, 1, 1, 1, 1, 1};
        System.out.println("\nTEST 2");
        System.out.println("is Graph? " + isGraph(degree));
        System.out.println(Arrays.toString(BuildTreeFromDegreesArray(degree)));

        /* TEST 3 */
        degree = new int[]{1, 1, 3, 3, 1, 4, 1, 1, 1};
        System.out.println("\nTEST 3");
        System.out.println("is Graph? " + isGraph(degree));
        System.out.println(Arrays.toString(BuildTreeFromDegreesArray(degree)));

        /* TEST 4 */
        /*                  (6)
         *              (4) '
         *               | '
         *        (1)---(0)---(2)
         *             ` |
         *       (5) `  (3)
         */
        degree = new int[]{6, 0, 0, 0, 0, 0, 0, 0};
        System.out.println("\nTEST 4");
        System.out.println("is Graph? " + isGraph(degree));
        System.out.println(Arrays.toString(BuildTreeFromDegreesArray(degree)));
    }
}
