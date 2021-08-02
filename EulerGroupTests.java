import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EulerGroupTests {

    // -----------------------------------
    // Checking correctness
    // -----------------------------------
    @Test
    public void Test10() {
        boolean[][] graph0 =
                {{false, true, false, false, true},
                        {true, false, true, true, false},
                        {false, true, false, true, false},
                        {false, true, true, false, false},
                        {true, false, false, false, false}};
        Euler e = new Euler(graph0);
        System.out.println("Test 0\t" + e.has_euler_path() + " " + e.euler_path());
    }

    @Test
    public void Test11() {
        boolean[][] graph1 =
                {{false, true, false, true, true},
                        {true, false, true, false, true},
                        {false, true, false, true, true},
                        {true, true, true, false, false},
                        {true, false, true, false, false}};
        Euler e = new Euler(graph1);
        System.out.println("Test 1\t" + e.has_euler_path() + " " + e.euler_path());
    }

    @Test
    public void Test12() {
        boolean[][] graph2 =
                {{false, true, false, false, true},
                        {true, false, true, true, true},
                        {false, true, false, true, false},
                        {false, true, true, false, true},
                        {true, true, false, true, false}};
        Euler e = new Euler(graph2);
        System.out.println("Test 2\t" + e.has_euler_path() + " " + e.euler_path());
    }

    @Test
    public void Test13() {
        boolean[][] graph3 =
                {{false, true, true, false},
                        {true, false, true, false},
                        {true, true, false, true},
                        {false, false, true, false}};
        Euler e = new Euler(graph3);
        System.out.println("Test 3\t" + e.has_euler_path() + " " + e.euler_path());
    }

    @Test
    public void Test14() {
        boolean[][] graph4 =
                {{false, true, true},
                        {true, false, true},
                        {true, true, false}};
        Euler e = new Euler(graph4);
        System.out.println("Test 4\t" + e.has_euler_path() + " " + e.euler_path());
    }

    @Test
    public void Test15() {
        boolean[][] graph5 =
                {{false, true, true, true, false},
                        {true, false, true, true, false},
                        {true, true, false, true, true},
                        {true, true, true, false, false},
                        {false, false, true, true, false}};
        Euler e = new Euler(graph5);
        System.out.println("Test 5\t" + e.has_euler_path() + " " + e.euler_path());
    }

    @Test
    public void Test16() {
        boolean[][] graph7 = {{false}};
        Euler e = new Euler(graph7);
        System.out.println("Test 6\t" + e.has_euler_path() + " " + e.euler_path());
    }

    // -----------------------------------
    // Check running times
    // -----------------------------------
    @Test
    public void Test20() {
        //test 1 - Graph with 10000 Nodes , ~200000 Edges:
        boolean[][] graph_10000 = Create_10000_Graph();
        long start = System.currentTimeMillis();
        Euler euler_10000 = new Euler(graph_10000);
        for (int i = 0; i < 10; i++) {
            String A = euler_10000.euler_path();
        }
        long end = System.currentTimeMillis();
        System.out.println("time for 10000 nodes graph is : " + (end - start) / 1000);
    }

    @Test
    public void Test21() {
        //test 2 - Graph with 30000 Nodes , ~500000 Edges:
        boolean[][] graph_30000 = Create_30000_Graph();
        long start_1 = System.currentTimeMillis();
        Euler euler_30000 = new Euler(graph_30000);
        String A = euler_30000.euler_path();
        long end_1 = System.currentTimeMillis();
        System.out.println("time for 30000 nodes graph is : " + (end_1 - start_1) / 1000);

    }

    @Test
    public void Test22() {
        //test 3 - Graph with 50000 Nodes , ~1000000 Edges
        boolean[][] graph_50000 = Create_50000_Graph();
        long start_2 = System.currentTimeMillis();
        Euler euler_50000 = new Euler(graph_50000);
        String B = euler_50000.euler_path();
        long end_2 = System.currentTimeMillis();
        System.out.println("time for 50000 nodes graph is : " + (end_2 - start_2) / 1000);
    }

    @Test
    public void Test23() {
        //test 4
        boolean[][] big_one = new boolean[60002][60002];
        for (int i = 1; i < 11; i++) {
            big_one[0][i] = true;
            big_one[i][0] = true;
        }
        for (int i = 1; i < 59991; i++) {
            big_one[i][i + 10] = true;
            big_one[i + 10][i] = true;
        }
        for (int i = 59991; i <= 60000; i++) {
            big_one[i][60001] = true;
            big_one[60001][i] = true;
        }
        big_one[1][12] = true;
        big_one[12][1] = true;
        long start_3 = System.currentTimeMillis();
        Euler big = new Euler(big_one);
        for (int i = 0; i < 1; i++) {
            String C = big.euler_path();
        }
        long end_3 = System.currentTimeMillis();
        System.out.println("time for 60000 nodes graph is : " + (end_3 - start_3) / 1000);
    }

    public static boolean[][] Create_10000_Graph() {
        boolean[][] ans = new boolean[10000][10000];
        int[] degrees = new int[10000];
        for (int k = 0; k < 200000; k++) {
            int i = (int) (Math.random() * 10000);
            int j = (int) (Math.random() * 10000);
            if (i != j && !ans[i][j]) {
                ans[i][j] = true;
                ans[j][i] = true;
                degrees[i]++;
                degrees[j]++;
            }
        }
        //if the graph isn't connected - add 1000 edges
        while (is_connected(ans)) {
            for (int k = 0; k < 1000; k++) {
                int i = (int) (Math.random() * 10000);
                int j = (int) (Math.random() * 10000);
                if (i != j && !ans[i][j]) {
                    ans[i][j] = true;
                    ans[j][i] = true;
                    degrees[i]++;
                    degrees[j]++;
                }
            }
        }
        //make sure that the graph contains exactly 2 nodes with odd degree
        int odd_nodes = 0;
        Stack<Integer> odd_deg = new Stack<>();
        for (int i = 0; i < 10000; i++) {
            if (degrees[i] % 2 != 0) {
                odd_nodes++;
                odd_deg.push(i);
            }
        }
        while (odd_nodes > 2) {
            int v = odd_deg.pop();
            int u = odd_deg.pop();
            odd_nodes -= 2;
            if (!ans[v][u]) {
                ans[v][u] = true;
                ans[u][v] = true;
                degrees[u]++;
                degrees[v]++;
            } else {
                ans[v][u] = false;
                ans[u][v] = false;
                degrees[u]--;
                degrees[v]--;
            }
        }
        return ans;
    }

    public static boolean[][] Create_30000_Graph() {
        boolean[][] ans = new boolean[30000][30000];
        int[] degrees = new int[30000];
        for (int k = 0; k < 500000; k++) {
            int i = (int) (Math.random() * 30000);
            int j = (int) (Math.random() * 30000);
            if (i != j && !ans[i][j]) {
                ans[i][j] = true;
                ans[j][i] = true;
                degrees[i]++;
                degrees[j]++;
            }
        }
        //if the graph isn't connected - add 1000 edges
        while (is_connected(ans)) {
            for (int k = 0; k < 3000; k++) {
                int i = (int) (Math.random() * 30000);
                int j = (int) (Math.random() * 30000);
                if (i != j && !ans[i][j]) {
                    ans[i][j] = true;
                    ans[j][i] = true;
                    degrees[i]++;
                    degrees[j]++;
                }
            }
        }
        //make sure that the graph contains exactly 2 nodes with odd degree
        int odd_nodes = 0;
        Stack<Integer> odd_deg = new Stack<>();
        for (int i = 0; i < 30000; i++) {
            if (degrees[i] % 2 != 0) {
                odd_nodes++;
                odd_deg.push(i);
            }
        }
        while (odd_nodes > 2) {
            int v = odd_deg.pop();
            int u = odd_deg.pop();
            odd_nodes -= 2;
            if (!ans[v][u]) {
                ans[v][u] = true;
                ans[u][v] = true;
                degrees[u]++;
                degrees[v]++;
            } else {
                ans[v][u] = false;
                ans[u][v] = false;
                degrees[u]--;
                degrees[v]--;
            }
        }
        return ans;
    }

    public static boolean[][] Create_50000_Graph() {
        boolean[][] ans = new boolean[50000][50000];
        int[] degrees = new int[50000];
        for (int k = 0; k < 1000000; k++) {
            int i = (int) (Math.random() * 50000);
            int j = (int) (Math.random() * 50000);
            if (i != j && !ans[i][j]) {
                ans[i][j] = true;
                ans[j][i] = true;
                degrees[i]++;
                degrees[j]++;
            }
        }
        //if the graph isn't connected - add 1000 edges
        while (is_connected(ans)) {
            for (int k = 0; k < 3000; k++) {
                int i = (int) (Math.random() * 50000);
                int j = (int) (Math.random() * 50000);
                if (i != j && !ans[i][j]) {
                    ans[i][j] = true;
                    ans[j][i] = true;
                    degrees[i]++;
                    degrees[j]++;
                }
            }
        }
        //make sure that the graph contains exactly 2 nodes with odd degree
        int odd_nodes = 0;
        Stack<Integer> odd_deg = new Stack<>();
        for (int i = 0; i < 50000; i++) {
            if (degrees[i] % 2 != 0) {
                odd_nodes++;
                odd_deg.push(i);
            }
        }
        while (odd_nodes > 2) {
            int v = odd_deg.pop();
            int u = odd_deg.pop();
            odd_nodes -= 2;
            if (!ans[v][u]) {
                ans[v][u] = true;
                ans[u][v] = true;
                degrees[u]++;
                degrees[v]++;
            } else {
                ans[v][u] = false;
                ans[u][v] = false;
                degrees[u]--;
                degrees[v]--;
            }
        }
        return ans;
    }

    public static boolean is_connected(boolean[][] mat) {
        int n = mat.length;
        int[] visit = new int[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int counter = 1;
        visit[0] = 1;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int u = 0; u < n; u++) {
                if (visit[u] == 0 && u != v && mat[v][u]) {
                    counter++;
                    visit[u] = 1;
                    q.add(u);
                }
            }
        }
        return counter != n;
    }
}
