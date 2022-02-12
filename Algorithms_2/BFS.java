import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private int V;
    private int[] dist, father, component, color/*WHITE = 1, GREY = 2, BLACK = 3*/;
    private int[][] graph;
    private int src;
    public static Integer inf = Integer.MAX_VALUE;

    public BFS(int[][] mat) {
        V = mat.length;
        dist = new int[V];
        father = new int[V];
        color = new int[V];
        component = new int[V];
        src = 0;
        graph = new int[mat.length][mat.length];
        copyMat(mat);
    }

    private void copyMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                graph[i][j] = mat[i][j];
            }
        }
    }

    // O(V+E)
    public void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();

        // init graph
        for (int i = 0; i < V; i++) {
            color[i] = 1;
            dist[i] = inf;
            father[i] = -1;
        }
        // update s
        src = s;
        dist[src] = 0;
        color[src] = 2;
        q.add(src);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int ni = 0; ni < V; ni++) {
                if (color[ni] == 1 && graph[u][ni] == 1) {
                    dist[ni] = dist[u] + 1;
                    father[ni] = u;
                    color[ni] = 2;
                    q.add(ni);
                }
            }
            color[u] = 3;
        }
    }

    public String printPath(int s, int t) {
        bfs(s);
        if (t == s) return s + " || ";
        if (father[t] == -1) return "No Path from " + s + " to " + t;
        String ans = "||";
        while (t != s) {
            ans += " -> " + t;
            t = father[t];
        }
        ans += " -> " + s;
        return ans;
    }

    public boolean isComp() {
        bfs(src);
        for (int i = 0; i < V; i++) {
            if (dist[i] == inf) return false;
        }
        return true;
    }

    public int numOfComp() {
        if (V == 0) return 0;
        bfs(src);
        int v = src, numComp = 1;
        while (!isComp()) {
            if (v != src) v = nextWhite();
            bfs(v);
            numComp++;
        }
        return numComp;
    }

    private int nextWhite() {
        for (int i = 0; i < V; i++) {
            if (color[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    public void verInComp() {
        int numComp = numOfComp();
        for (int i = 0; i < numComp; i++) {
            String ans = "Comp " + (i + 1) + ": ";
            for (int j = 0; j < V; j++) {
                if (component[j] == i) ans = ans + j + ", ";
            }
            System.out.println(ans);
        }
    }

    public void distBetweenTwoVar(int s, int t) {
        bfs(s);
        if (dist[t] == inf) System.out.println("Distance: inf");
        else System.out.println("Distance: " + dist[t]);
        System.out.println("Path: " + printPath(s, t));
    }

    // O(V*(V+E))
    public int diam1() {
        int diam = 0, max;
        if (!isComp()) return -1;
        for (int i = 0; i < V; i++) {
            bfs(i);
            max = getMax();
            if (max > diam) diam = max;
        }
        return diam;
    }

    private int getMax() {
        int max = 0;
        for (int i = 0; i < V; i++) {
            max = Math.max(max, dist[i]);
        }
        return max;
    }

    // O(V+E)
    public int diam2() {
        bfs(src);
        int u = getMaxIndex();
        bfs(u);
        return getMax();
    }

    private int getMaxIndex() {
        int max = 0, maxIndex = 0;
        for (int i = 0; i < V; i++) {
            if (max < dist[i]) {
                max = dist[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 1},
                {0, 0, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0}};

        BFS bfs = new BFS(graph);
        bfs.distBetweenTwoVar(5, 0);
        System.out.println("Is Comp? " + bfs.isComp());
        System.out.println("Num of comp num: " + bfs.numOfComp());
        bfs.verInComp();
        System.out.println("Diam : " + bfs.diam1() + " || " + bfs.diam2());

    }
}
