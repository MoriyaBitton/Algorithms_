package src;

import java.util.ArrayList;
import java.util.Stack;

public class MaxMax {

    public static class Node {
        int data;
        Stack<Integer> stack;

        public Node(int data) {
            this.data = data;
            stack = new Stack<>();
        }
    }

    // Find the two maximum elements in array
    // Complexity: O(n) - 3/2(n-1) comparisons
    public static void maxMax(int[] arr) {
        int comparisons = 0;
        if (arr.length == 1) {
            System.out.println("Max 1 = " + arr[0] + " , Max 2 = " + arr[0] + " , Comparisons = " + comparisons);
        }
        int max1 = arr[0];
        int max2 = arr[1];
        comparisons++;
        if (arr[0] < arr[1]) {
            max1 = arr[1];
            max2 = arr[0];
        }
        for (int i = 2; i < arr.length - 1; i += 2) {
            comparisons++;
            if (arr[i] > arr[i + 1]) {
                comparisons += 2;
                if (arr[i] > max1) {
                    if (arr[i + 1] > max1) {
                        max1 = arr[i];
                        max2 = arr[i + 1];
                    } else {
                        max2 = max1;
                        max1 = arr[i];
                    }
                } else if (arr[i] > max2) {
                    max2 = arr[i];
                }
            } else {
                comparisons += 2;
                if (arr[i + 1] > max1) {
                    if (arr[i] > max1) {
                        max1 = arr[i + 1];
                        max2 = arr[i];
                    } else {
                        max2 = max1;
                        max1 = arr[i + 1];
                    }
                } else if (arr[i + 1] > max2) {
                    max2 = arr[i + 1];
                }
            }
        }
        comparisons++;
        if (arr[arr.length - 1] > max1) {
            max2 = max1;
            max1 = arr[arr.length - 1];
        } else {
            comparisons++;
            if (arr[arr.length - 1] > max2) {
                max2 = arr[arr.length - 1];
            }
        }
        System.out.println("Max 1 = " + max1 + " , Max 2 = " + max2 + " , Comparisons = " + comparisons);
    }

    // Find the 2 maximum elements in array - Recursive
    // Complexity: O(n) - (n + log n) comparisons
    public static void maxMaxRecursive(int[] arr) {
        int comparisons = 0;
        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }
        Node max1 = maxMaxRecursive(nodes, 0, nodes.length - 1);
        int max2 = max1.stack.pop();
        while (!max1.stack.isEmpty()) {
            int x = max1.stack.pop();
            comparisons++;
            if (max2 < x) max2 = x;
        }
        System.out.println("Max 1 = " + max1.data + " , Max 2 = " + max2 + " , Comparisons = " + comparisons);
    }

    private static Node maxMaxRecursive(Node[] nodes, int low, int high) {
        if (high == low) return nodes[low];
        int mid = (low + high) / 2;
        Node maxL = maxMaxRecursive(nodes, low, mid);
        Node maxR = maxMaxRecursive(nodes, mid + 1, high);
        if (maxL.data > maxR.data) {
            maxL.stack.push(maxR.data);
            return maxL;
        } else {
            maxR.stack.push(maxL.data);
            return maxR;
        }
    }

    // Find the 2 maximum elements in array - Inductive
    // Complexity: O(n) - (n + log n) comparisons
    public static void maxMaxInduction(int[] arr) {
        int comparisons = 0;
        ArrayList<Node> list = new ArrayList<>();
        for (int j : arr) {
            list.add(new Node(j));
        }
        int i = 0;
        while (list.size() > 1) {
            Node x = list.get(i);
            Node y = list.get(i + 1);
            comparisons++;
            if (x.data > y.data) {
                x.stack.add(y.data);
                list.remove(i + 1);
            } else {
                y.stack.add(x.data);
                list.remove(i);
            }
            i++;
            if (i == list.size() || i == list.size() - 1) i = 0;
        }
        int max1 = list.get(0).data;
        Stack<Integer> st = list.get(0).stack;
        int max2 = st.pop();
        while (!st.isEmpty()) {
            int x = st.pop();
            comparisons++;
            if (x > max2) {
                max2 = x;
            }
        }
        System.out.println("Max 1 = " + max1 + " , Max 2 = " + max2 + " , Comparisons = " + comparisons);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 66, 23, 568, 748, 465, 23, 45, 23, 465, 23, 45, 232};
        maxMax(arr);
        maxMaxRecursive(arr);
        maxMaxInduction(arr);
    }
}

