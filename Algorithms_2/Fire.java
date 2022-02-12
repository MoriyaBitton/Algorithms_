import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Fire {

    private int size;
    private int[] deg;
    private int center1, center2, diam, radius;

    public Fire(ArrayList<ArrayList<Integer>> tree) {
        size = tree.size();
        deg = new int[size];
        center1 = -1;
        center2 = -1;
        diam = 0;
        radius = 0;
        fire(tree);
    }

    private void fire(ArrayList<ArrayList<Integer>> tree) {
        // init help array and insert leaves to queue
        Queue<Integer> leaves = initDeg(tree);

        // burn leaves
        while (size > 2) {
            // the "radius" time of burning the leave in the queue
            int leavesToBorn = leaves.size();
            for (int i = 0; i < leavesToBorn; i++) {
                int toBurn = leaves.remove();
                deg[toBurn] = 0;
                size--;
                for (int j = 0; j < tree.get(toBurn).size(); j++) {
                    int ni = tree.get(toBurn).get(j);
                    if (deg[ni] > 0) deg[ni]--;
                    if (deg[ni] == 1) leaves.add(ni);
                }
            }
            radius++;
        }

        // Update parameters
        if (size == 1) { /* one center */
            diam = 2 * radius;
            center1 = leaves.remove();
        } else { /* two center */
            radius += 1;
            diam = 2 * radius - 1;
            center1 = leaves.remove();
            center2 = leaves.remove();
        }
    }

    private Queue<Integer> initDeg(ArrayList<ArrayList<Integer>> tree) {
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            deg[i] = tree.get(i).size();
            if (deg[i] == 1) leaves.add(i);
        }
        return leaves;
    }

    public static void main(String[] args) {
        int inf = Integer.MAX_VALUE;

        /*
         * (0)---(1)---(2)---(3)---(4)
         *              |
         *       (6)---(5)---(7)
         */
        int[][] matrix1 = {
                {inf, 1, inf, inf, inf, inf, inf, inf},
                {0, inf, 2, inf, inf, inf, inf, inf},
                {inf, 1, inf, 3, inf, 5, inf, inf},
                {inf, inf, 2, inf, 4, inf, inf, inf},
                {inf, inf, inf, 3, inf, inf, inf, inf},
                {inf, inf, 2, inf, inf, inf, 6, 7},
                {inf, inf, inf, inf, inf, 5, inf, inf},
                {inf, inf, inf, inf, inf, 5, inf, inf}};
        Fire fireAlgorithm = new Fire(initGraph(matrix1));
        System.out.print("Test 1 ----> ");
        System.out.print(" Radius: " + fireAlgorithm.radius);
        System.out.print(" Diameter: " + fireAlgorithm.diam); // 4
        System.out.print(" Center1: " + fireAlgorithm.center1);
        if (fireAlgorithm.center2 != -1) System.out.print(" Center2: " + fireAlgorithm.center2);
        // Test 1 ----> radius = 2, diameter = 4, centers: 2

        /*
         *  (0)---(1)---(2)---(3)
         *         |
         *        (4)---(5)---(6)
         */
        int[][] matrix2 = new int[][]{
                {inf, 1, inf, inf, inf, inf, inf},
                {1, inf, 1, inf, 4, inf, inf},
                {inf, 1, inf, 1, inf, inf, inf},
                {inf, inf, 1, inf, inf, inf, inf},
                {inf, 1, inf, inf, inf, 1, inf},
                {inf, inf, inf, inf, 1, inf, 1},
                {inf, inf, inf, inf, inf, 1, inf}
        };
        fireAlgorithm = new Fire(initGraph(matrix2));
        System.out.print("\nTest 2 ----> ");
        System.out.print(" Radius: " + fireAlgorithm.radius);
        System.out.print(" Diameter: " + fireAlgorithm.diam); // 5
        System.out.print(" Center1: " + fireAlgorithm.center1);
        if (fireAlgorithm.center2 != -1) System.out.print(" Center2: " + fireAlgorithm.center2);
        // Test 2 ----> radius = 3, diameter = 5, centers: 1 & 4

        /*
         *  (0)---(1)---(2)---(3)---(4)
         */
        int[][] matrix3 = new int[][]{
                {inf, 1, inf, inf, inf},
                {1, inf, 1, inf, inf},
                {inf, 1, inf, 1, inf},
                {inf, inf, 1, inf, 1},
                {inf, inf, inf, 1, inf}
        };
        fireAlgorithm = new Fire(initGraph(matrix3));
        System.out.print("\nTest 3 ----> ");
        System.out.print(" Radius: " + fireAlgorithm.radius);
        System.out.print(" Diameter: " + fireAlgorithm.diam); // 4
        System.out.print(" Center1: " + fireAlgorithm.center1);
        if (fireAlgorithm.center2 != -1) System.out.print(" Center2: " + fireAlgorithm.center2);
        // Test 3 ----> radius = 2, diameter = 4, centers: 2

        /*
         *  (0)---(1)---(2)---(3)---(4)---(5)---(6)---(7)
         */
        int[][] matrix4 = new int[][]{
                {inf, 1, inf, inf, inf, inf, inf, inf},
                {1, inf, 1, inf, inf, inf, inf, inf},
                {inf, 1, inf, 1, inf, inf, inf, inf},
                {inf, inf, 1, inf, 1, inf, inf, inf},
                {inf, inf, inf, 1, inf, 1, inf, inf},
                {inf, inf, inf, inf, 1, inf, 1, inf},
                {inf, inf, inf, inf, inf, 1, inf, 1},
                {inf, inf, inf, inf, inf, inf, 1, inf}
        };
        fireAlgorithm = new Fire(initGraph(matrix4));
        System.out.print("\nTest 4 ----> ");
        System.out.print(" Radius: " + fireAlgorithm.radius);
        System.out.print(" Diameter: " + fireAlgorithm.diam); // 7
        System.out.print(" Center1: " + fireAlgorithm.center1);
        if (fireAlgorithm.center2 != -1) System.out.print(" Center2: " + fireAlgorithm.center2);
        // Test 4 ----> radius = 4, diameter = 7, centers: 3 & 4

        /*
         *  (0)---(1)---(2)---(6)
         *               |
         *              (4)---(5)---(3)
         */
        int[][] matrix5 = new int[][]{
                {inf, 1, inf, inf, inf, inf, inf},
                {1, inf, 1, inf, inf, inf, inf},
                {inf, 1, inf, inf, 1, inf, 1},
                {inf, inf, inf, inf, inf, 1, inf},
                {inf, inf, 1, inf, inf, 1, inf},
                {inf, inf, inf, 1, 1, inf, inf},
                {inf, inf, 1, inf, inf, inf, inf}
        };
        fireAlgorithm = new Fire(initGraph(matrix5));
        System.out.print("\nTest 5 ----> ");
        System.out.print(" Radius: " + fireAlgorithm.radius);
        System.out.print(" Diameter: " + fireAlgorithm.diam); // 5
        System.out.print(" Center1: " + fireAlgorithm.center1);
        if (fireAlgorithm.center2 != -1) System.out.print(" Center2: " + fireAlgorithm.center2);
        // Test 5 ----> radius = 3, diameter = 5, centers: 2 & 4

        /*                  (6)
         *              (4) '
         *               | '
         *        (1)---(0)---(2)
         *             ` |
         *       (5) `  (3)
         */
        int[][] matrix6 = new int[][]{
                {inf, 1, 1, 1, 1, 1, 1},
                {1, inf, inf, inf, inf, inf, inf},
                {1, inf, inf, inf, inf, inf, inf},
                {1, inf, inf, inf, inf, inf, inf},
                {1, inf, inf, inf, inf, inf, inf},
                {1, inf, inf, inf, inf, inf, inf},
                {1, inf, inf, inf, inf, inf, inf}
        };
        fireAlgorithm = new Fire(initGraph(matrix6));
        System.out.print("\nTest 6 ----> ");
        System.out.print(" Radius: " + fireAlgorithm.radius);
        System.out.print(" Diameter: " + fireAlgorithm.diam); // 2
        System.out.print(" Center1: " + fireAlgorithm.center1);
        if (fireAlgorithm.center2 != -1) System.out.print(" Center2: " + fireAlgorithm.center2);
        // Test 6 ----> radius = 1, diameter = 2, centers: 0
    }

    public static ArrayList<ArrayList<Integer>> initGraph(int[][] matrix) {
        ArrayList<ArrayList<Integer>> G = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j == 0) G.add(new ArrayList<>());
                if (matrix[i][j] != Integer.MAX_VALUE) G.get(i).add(j);
            }
        }
        return G;
    }
}
