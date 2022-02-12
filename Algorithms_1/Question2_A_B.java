public class Question2_A_B {

    // question 2 - A
    static class Node {
        int x, y, distance, num;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = 0;
            this.num = 0;
        }
    }

    // Complexity: O(n + m + n*m) = O(n*m)
    public static int numOfPaths(Node[][] nodes) {
        int ans, n, m;
        n = nodes.length; // n = num of row
        m = nodes[0].length; // m = num of col
        nodes[0][0].distance = 0;
        nodes[0][0].num = 1;

        // init the first col
        for (int i = 1; i < n; i++) { // O(n)
            nodes[i][0].distance = nodes[i - 1][0].distance + nodes[i - 1][0].y;
            nodes[i][0].num = 1;
        }

        // init the first row
        for (int i = 1; i < m; i++) { // O(m)
            nodes[0][i].distance = nodes[0][i - 1].distance + nodes[0][i - 1].x;
            nodes[0][i].num = 1;
        }

        // init the rest of matrix
        for (int i = 1; i < n; i++) { // O(m*n)
            for (int j = 1; j < m; j++) {
                int up, right;
                up = nodes[i - 1][j].distance + nodes[i - 1][j].y;
                right = nodes[i][j - 1].distance + nodes[i][j - 1].x;
                int min = Math.min(up, right);
                nodes[i][j].distance = min;
                if (up < right) {
                    nodes[i][j].num = nodes[i - 1][j].num;
                } else if (up > right) {
                    nodes[i][j].num = nodes[i][j - 1].num;
                } else { // up == right
                    nodes[i][j].num = nodes[i - 1][j].num + nodes[i][j - 1].num;
                }
            }
        }
        ans = nodes[n - 1][m - 1].num;
        return ans;
    }

    // question 2 - B
    static class NodePQ {
        int x, y, distance, numPQ;

        public NodePQ(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = 0;
            this.numPQ = 0;
        }
    }

    // Complexity: O(n + m + n*m) = O(n*m)
    public static int numOfPathsPQ(NodePQ[][] nodes, int P, int Q) {
        int ans = 0, n, m, extremeCase = 0;

        n = nodes.length; // n = num of row
        m = nodes[0].length; // m = num of col
        nodes[0][0].distance = 0;
        nodes[0][0].numPQ = 1;

        if ((P == 0 && Q == 0) || (P == n && Q == m)) {
            extremeCase = 1;
        }

        // init the first col
        for (int i = 1; i < n; i++) { // O(n)
            nodes[i][0].distance = nodes[i - 1][0].distance + nodes[i - 1][0].y;
            nodes[i][0].numPQ = 1;
        }

        // init the first row
        for (int i = 1; i < m; i++) { // O(m)
            nodes[0][i].distance = nodes[0][i - 1].distance + nodes[0][i - 1].x;
            nodes[0][i].numPQ = 1;
        }

        // init the rest of matrix
        for (int i = 1; i < n; i++) { // O(m*n)
            for (int j = 1; j < m; j++) {
                int up, right;
                up = nodes[i - 1][j].distance + nodes[i - 1][j].y;
                right = nodes[i][j - 1].distance + nodes[i][j - 1].x;
                int min = Math.min(up, right);
                nodes[i][j].distance = min;
                if (up < right) {
                    if ((i - 1) == P && j == Q) ans++;
                    nodes[i][j].numPQ = nodes[i - 1][j].numPQ;
                } else if (up > right) {
                    if (i == P && (j - 1) == Q) ans++;
                    nodes[i][j].numPQ = nodes[i][j - 1].numPQ;
                } else { // up == right
                    if (i == P && j == Q) ans++;
                    nodes[i][j].numPQ = nodes[i - 1][j].numPQ + nodes[i][j - 1].numPQ;
                }
            }
        }
        if(extremeCase == 1) ans = nodes[n - 1][m - 1].numPQ;
        return ans;
    }

//    public static int numOfPathsPQ(NodePQ[][] nodes, int P, int Q) {
//        String[] all = minPriceBetween(nodes,0, 0,nodes.length - 1, nodes[0].length - 1);
//        int allPrice = Integer.parseInt(all[0]);// all distance
//        if ((P== Q && P == 0) || (P == nodes.length && Q == nodes[0].length)){
//            return Integer.parseInt(all[1]);
//        }
//        String[] a = minPriceBetween(nodes, 0, 0, P, Q);
//        int start2Point = Integer.parseInt(a[0]);// distance from start to point
//        String[] b = minPriceBetween(nodes, P, Q, nodes.length - 1, nodes[0].length - 1);
//        int Point2end = Integer.parseInt(b[0]); // distance from point to end
//        if (allPrice == start2Point + Point2end) {
//            return Integer.parseInt(a[1]) + Integer.parseInt(b[1]) -1;
//        } else return 0;
//    }
//
//    public static String[] minPriceBetween(NodePQ[][] matrix,int x1, int y1, int x2, int y2) {
//        int n = y2 - y1 + 1;
//        int m = x2 - x1 + 1;
//        matrix[x1][y1].distance = 0;
//        for (int i = y1 + 1; i < y1 + n; i++) { // set the coulombs
//            matrix[i][x1].distance = matrix[i - 1][x1].distance + matrix[i - 1][x1].y;
//            matrix[i][x1].numPQ = 1;
//        }
//        for (int i = x1 + 1; i < x1 + m; i++) { // set the raw
//            matrix[y1][i].distance = matrix[y1][i - 1].distance + matrix[y1][i - 1].x;
//            matrix[y1][i].numPQ = 1;
//        }
//        for (int i = y1 + 1; i < y1 + n; i++) {
//            for (int j = x1 + 1; j < x1 + m; j++) {
//                int fromDown = matrix[i - 1][j].distance + matrix[i - 1][j].y;
//                int fromLeft = matrix[i][j - 1].distance + matrix[i][j - 1].x;
//                matrix[i][j].distance = Math.min(fromDown, fromLeft);
//                if (fromDown < fromLeft) {
//                    matrix[i][j].numPQ = matrix[i - 1][j].numPQ;
//                } else if (fromDown > fromLeft) {
//                    matrix[i][j].numPQ = matrix[i][j - 1].numPQ;
//                } else matrix[i][j].numPQ = matrix[i - 1][j].numPQ + matrix[i][j - 1].numPQ;
//            }
//        }
//        int a = matrix[y2][y2].distance;
//        int b = matrix[y2][x2].numPQ;
//        return new String[]{Integer.toString(a), Integer.toString(b)};
//    }

    public static void main(String[] args) {
        Node[][] mat = new Node[4][4];
        mat[0][0] = new Node(1, 2);
        mat[0][1] = new Node(1, 1);
        mat[0][2] = new Node(1, 3);
        mat[0][3] = new Node(0, 1);
        mat[1][0] = new Node(2, 3);
        mat[1][1] = new Node(5, 1);
        mat[1][2] = new Node(6, 3);
        mat[1][3] = new Node(0, 1);
        mat[2][0] = new Node(2, 4);
        mat[2][1] = new Node(7, 1);
        mat[2][2] = new Node(2, 3);
        mat[2][3] = new Node(0, 1);
        mat[3][0] = new Node(2, 0);
        mat[3][1] = new Node(1, 0);
        mat[3][2] = new Node(1, 0);
        mat[3][3] = new Node(0, 0);
        System.out.println("num of shortest path: " + numOfPaths(mat));

        NodePQ[][] matPQ = new NodePQ[4][4];
        matPQ[0][0] = new NodePQ(1, 2);
        matPQ[0][1] = new NodePQ(1, 1);
        matPQ[0][2] = new NodePQ(1, 3);
        matPQ[0][3] = new NodePQ(0, 1);
        matPQ[1][0] = new NodePQ(2, 3);
        matPQ[1][1] = new NodePQ(5, 1);
        matPQ[1][2] = new NodePQ(6, 3);
        matPQ[1][3] = new NodePQ(0, 1);
        matPQ[2][0] = new NodePQ(2, 4);
        matPQ[2][1] = new NodePQ(7, 1);
        matPQ[2][2] = new NodePQ(2, 3);
        matPQ[2][3] = new NodePQ(0, 1);
        matPQ[3][0] = new NodePQ(2, 0);
        matPQ[3][1] = new NodePQ(1, 0);
        matPQ[3][2] = new NodePQ(1, 0);
        matPQ[3][3] = new NodePQ(0, 0);
        System.out.println("num of shortest path (P, Q): " + numOfPathsPQ(matPQ, 0, 0));
    }
}

