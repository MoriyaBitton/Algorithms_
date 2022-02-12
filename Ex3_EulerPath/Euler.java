import java.util.*;

public class Euler {
    private final int V;
    private final int[] degree;
    private final LinkedList<Edge>[] adj;
    private int noNeighbors;
    private int oddVar;
    private int start;


    private static class Edge {
        private final int v;
        private final int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int other(int vertex) {
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    // Complexity: O(V^2)
    public Euler(boolean[][] graph) {
        this.V = graph.length;
        this.noNeighbors = V;
        this.oddVar = 0;
        this.start = 0;

        this.degree = new int[V];
        this.adj = new LinkedList[V];

        /* Init: adj */
        for (int v = 0; v < V; v++) { // O(V)
            adj[v] = new LinkedList<>(); /* create V lists */
            degree[v] = 0; /* init degree to 0 */
        }

        for (int v = V - 1; v >= 0; v--) { // O((V^2)/2)
            for (int w = 0; w <= v; w++) {
                if (graph[v][w]) addEdge(v, w);
            }
            if (degree[v] % 2 == 1) {
                oddVar++;
                start = v;
            }
        }
    }

    // Complexity: O(1)
    private void addEdge(int v, int w) {
        // add edge to vertices queue: v,w
        Edge e = new Edge(v, w);
        adj[v].add(e);
        adj[w].add(e);
        // update vertices (v, w) degree and the number of vertices with no neighbors
        if (degree[v] == 0) noNeighbors--;
        if (degree[w] == 0) noNeighbors--;
        degree[v]++;
        degree[w]++;
    }

    // Complexity: O(V + E)
    public boolean has_euler_path() {
        if (V == 1) return true;
        if (noNeighbors == 0 && (oddVar == 2 || oddVar == 0)) return isComponentGraph(); // O(V + E)
        return false;
    }

    // Complexity: O(V)
    public String euler_path() {
        StringBuilder eulerPath = new StringBuilder(" ");
        if (!has_euler_path()) return eulerPath.toString();

        Stack<Integer> stack = new Stack<>();
        Vector<Integer> path = new Vector<>();
        int cur = start;
        stack.push(cur);

        while (!stack.isEmpty() || adj[cur].size() != 0) {
            if (adj[cur].isEmpty()) {
                path.add(cur);
                cur = stack.pop();
            } else {
                Edge e = adj[cur].remove();
                assert e != null;
                int ni = e.other(cur);
                stack.add(cur);
                /* remove Edge */
                adj[cur].remove(e);
                adj[ni].remove(e);
                cur = ni;
            }
        }

        for (int node : path) eulerPath.append(node).append(" -> ");
        eulerPath.append("||");
        return eulerPath.toString();
    }

    // Complexity: O(V + E)
    public boolean isComponentGraph() {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        int isComponent = 1;
        queue.add(start);
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Edge e : adj[v]) {
                int w = e.other(v);
                if (!visited[w]) {
                    visited[w] = true;
                    isComponent++;
                    queue.add(w);
                }
            }
        }
        return (isComponent == V);
    }
}
