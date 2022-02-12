import java.util.ArrayList;

public class Dijkstra {
    static int inf = Integer.MAX_VALUE;

    private static int DijkstraAlgorithm(ArrayList<ArrayList<Integer>> graph, int s, int t, int[][] matWeight) {
        int verticals = graph.size();
        int[] dist = new int[verticals];
        int[] path = new int[verticals];
        boolean[] visited = new boolean[verticals];

        // init dest && path
        for (int i = 0; i < verticals; i++) {
            path[i] = -1; // null
            visited[i] = false;
            if (i == s) dist[i] = 0;
            else dist[i] = inf;
        }

        // init PriorityQueue with s and his ni
        for (int ni : graph.get(s)) {
            dist[ni] = matWeight[s][ni];
            path[ni] = s;
        }
        visited[s] = true;

        while (!visited[t]) {
            int u = getMin(dist, visited);
            visited[u] = true;
            for (int ni : graph.get(u)) {
                if (dist[u] != inf && matWeight[u][ni] != inf && dist[ni] > dist[u] + matWeight[u][ni]) {
                    dist[ni] = dist[u] + matWeight[u][ni];
                    path[ni] = u;
                }
            }
        }
        printPath(s, t, path);
        return dist[t];
    }

    private static int BiDijkstraAlgorithm(ArrayList<ArrayList<Integer>> graph, ArrayList<ArrayList<Integer>> graphRevers, int s, int t, int[][] matWeight) {
        int verticals = graph.size();
        int[] dist_g = new int[verticals];
        int[] path_g = new int[verticals];
        boolean[] visited_g = new boolean[verticals];
        int[] dist_gR = new int[verticals];
        int[] path_gR = new int[verticals];
        boolean[] visited_gR = new boolean[verticals];

        // init dest && path
        for (int i = 0; i < verticals; i++) {
            path_g[i] = -1; // null
            path_gR[i] = -1; // null
            visited_g[i] = false;
            visited_gR[i] = false;
            dist_g[i] = inf;
            dist_gR[i] = inf;
        }
        dist_g[s] = 0;
        dist_gR[t] = 0;

        // init queue with s and his ni
        for (int ni : graph.get(s)) {
            dist_g[ni] = matWeight[s][ni];
            path_g[ni] = s;
        }
        visited_g[s] = true;

        // init queue with t and his ni
        for (int ni : graph.get(t)) {
            dist_gR[ni] = matWeight[t][ni];
            path_gR[ni] = t;
        }
        visited_gR[t] = true;

        while (verComment(visited_g, visited_gR) == -1) {
            int u = getMin(dist_g, visited_g);
            visited_g[u] = true;
            for (int ni : graph.get(u)) {
                if (dist_g[u] != inf && matWeight[u][ni] != inf && dist_g[ni] > dist_g[u] + matWeight[u][ni]) {
                    dist_g[ni] = dist_g[u] + matWeight[u][ni];
                    path_g[ni] = u;
                }
            }

            int w = getMin(dist_gR, visited_gR);
            visited_gR[w] = true;
            for (int ni : graph.get(w)) {
                if (dist_gR[w] != inf && matWeight[w][ni] != inf && dist_gR[ni] > dist_gR[w] + matWeight[w][ni]) {
                    dist_gR[ni] = dist_gR[w] + matWeight[w][ni];
                    path_gR[ni] = w;
                }
            }
        }

        // connect
        int minIndex = inf;
        for (int i = 0; i < verticals; i++) {
            if (dist_gR[i] != inf && dist_g[i] != inf) {
                dist_g[i] += dist_gR[i];
                if (dist_g[i] < minIndex) minIndex = i;
            }
        }

        printPath(s, minIndex, path_g);
        printPath(minIndex, t, path_gR);
        return dist_g[minIndex];
    }

    private static int verComment(boolean[] visited_g, boolean[] visited_gR) {
        for (int i = 0; i < visited_g.length; i++) {
            if (visited_g[i] && visited_gR[i]) return i;
        }
        return -1;
    }

    private static void printPath(int s, int t, int[] path) {
        String ans = t + " || ";
        int i = path[t];
        while (i != s && i != -1) {
            ans = i + " -> " + ans;
            i = path[i];
        }
        ans = s + " -> " + ans;
        System.out.println(ans);
    }

    private static int getMin(int[] dist, boolean[] visited) {
        int minNi = -1, minVal = inf;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && dist[i] < minVal) {
                minVal = dist[i];
                minNi = i;
            }
        }
        return minNi;
    }

    private static ArrayList<ArrayList<Integer>> initGraph() {
        ArrayList<ArrayList<Integer>> G = new ArrayList<>();
        int numOfVertices = 8;

        for (int i = 0; i < numOfVertices; i++) {
            G.add(new ArrayList<>());
        }

        G.get(0).add(1);
        G.get(0).add(2);

        G.get(1).add(0);
        G.get(1).add(2);
        G.get(1).add(4);

        G.get(2).add(0);
        G.get(2).add(1);
        G.get(2).add(3);
        G.get(2).add(5);

        G.get(3).add(2);
        G.get(3).add(4);
        G.get(3).add(5);

        G.get(4).add(1);
        G.get(4).add(3);
        G.get(4).add(6);
        G.get(4).add(7);

        G.get(5).add(2);
        G.get(5).add(3);
        G.get(5).add(6);

        G.get(6).add(4);
        G.get(6).add(5);
        G.get(6).add(7);

        G.get(7).add(4);
        G.get(7).add(6);

        return G;
    }

    private static ArrayList<ArrayList<Integer>> initGraphRevers() {
        ArrayList<ArrayList<Integer>> G = new ArrayList<>();
        int numOfVertices = 8;

        for (int i = 0; i < numOfVertices; i++) {
            G.add(new ArrayList<>());
        }

        G.get(1).add(0);
        G.get(2).add(0);

        G.get(0).add(1);
        G.get(2).add(1);
        G.get(4).add(1);

        G.get(0).add(2);
        G.get(1).add(2);
        G.get(3).add(2);
        G.get(5).add(2);

        G.get(2).add(3);
        G.get(4).add(3);
        G.get(5).add(3);

        G.get(1).add(4);
        G.get(3).add(4);
        G.get(6).add(4);
        G.get(7).add(4);

        G.get(2).add(5);
        G.get(3).add(5);
        G.get(6).add(5);

        G.get(4).add(6);
        G.get(5).add(6);
        G.get(7).add(6);

        G.get(4).add(7);
        G.get(6).add(7);

        return G;
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 1, 2, inf, inf, inf, inf, inf},
                {1, 0, 4, inf, 5, inf, inf, inf},
                {2, 4, 0, 7, inf, 3, inf, inf},
                {inf, inf, 7, 0, 3, 8, inf, inf},
                {inf, 5, inf, 3, 0, inf, 1, 4},
                {inf, inf, 3, 8, inf, 0, 2, inf},
                {inf, inf, inf, inf, 1, 2, 0, 5},
                {inf, inf, inf, inf, 4, inf, 5, 0}};
        ArrayList<ArrayList<Integer>> graph = initGraph();
        ArrayList<ArrayList<Integer>> graphRevers = initGraphRevers();
        int shortestPath = DijkstraAlgorithm(graph, 0, 7, mat);
        System.out.println("Graph: " + shortestPath);

        int shortestPathBi = BiDijkstraAlgorithm(graph, graphRevers, 0, 7, mat);
        System.out.println("Graph Revers: " + shortestPathBi);

    }
}
