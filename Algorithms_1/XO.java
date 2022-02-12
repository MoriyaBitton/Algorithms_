package src;

public class XO {

    public static final int X = 1, O = 2;
    private Node root;

    public XO() {
        root = new Node();
        createTree();
    }

    private class Node {
        int[] board;
        Node[] game;
        boolean Xwin, Owin;
        int price, index;

        public Node() {
            board = new int[9];
            game = new Node[9];
            Xwin = Owin = false;
            price = 0;
        }

        public Node(Node n) {
            this();
            for (int i = 0; i < n.board.length; i++) {
                board[i] = n.board[i];
            }
            Xwin = n.Xwin;
            Owin = n.Owin;
            price = n.price;
        }

        @Override
        public String toString() {
            String ans = "";
            for (int i = 0; i < board.length; i++) {
                if (board[i] == X) ans += "X ";
                else if (board[i] == O) ans += "O ";
                else ans += "_ ";
                if ((i + 1) % 3 == 0) {
                }
            }
            return ans;
        }
    }

    public void createTree() {
        root.price = fillTree(root, X);
    }

    private int fillTree(Node node, int turn) {
        if (node == null) return 0;
        int price = 0, index = 0;
        for (int i = 0; i < 9; i++) {
            if (node.board[i] == 0) {
                node.game[i] = new Node(node);
                node.game[i].board[i] = turn;
                if (isWin(node.game[i].board, turn)) {
                    node.game[i].price = turn == X ? 1 : -1;
                } else {
                    int p = fillTree(node.game[i], 3 - turn);
                    node.game[i].price = p;
                }
            }
        }
        int i = 0;
        while (i < node.game.length && node.game[i] == null) i++;
        if (i < node.game.length) {
            index = i;
            price = node.game[i].price;
        }
        for (; i < 9; i++) {
            if (node.game[i] != null) {
                int p = node.game[i].price;
                if (turn == X) {
                    if (p > price) {
                        price = p;
                        index = i;
                    }
                }
                if (turn == O) {
                    if (p < price) {
                        price = p;
                        index = i;
                    }
                }
            }
        }
        node.price = price;
        node.index = index;
        return price;
    }

    private void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null) return;
        for (int i = 0; i < node.board.length; i++) {
            System.out.print(node.board[i] == X ? "X" : node.board[i] == O ? "O" : "_");
            if ((i + 1) % 3 == 0) System.out.println();
        }
        System.out.println();
        print(node.game[node.index]);
    }

    private boolean isWin(int[] b, int t) {
        if (b[0] == t && b[1] == t && b[2] == t) return true;
        if (b[3] == t && b[4] == t && b[5] == t) return true;
        if (b[6] == t && b[7] == t && b[8] == t) return true;
        if (b[0] == t && b[3] == t && b[6] == t) return true;
        if (b[1] == t && b[4] == t && b[7] == t) return true;
        if (b[2] == t && b[5] == t && b[8] == t) return true;
        if (b[0] == t && b[4] == t && b[8] == t) return true;
        if (b[2] == t && b[4] == t && b[6] == t) return true;
        return false;
    }

    public static void main(String[] args) {
        XO tree = new XO();
        tree.print();
    }
}

