package src;

public class Parking {

    public static class Node {
        Node next, prev;
        int data;

        public Node(int d) {
            data = d;
            next = null;
            prev = null;
        }
    }

    public static class List {
        Node head, tail;

        public List() {
            tail = null;
            head = tail;
        }

        public void add(int d) {
            if (head == null) {
                head = new Node(d);
                head.next = head;
                head.prev = head;
                tail = head;
            } else {
                Node node = new Node(d);
                node.next = head;
                node.prev = tail;
                head.prev = node;
                tail.next = node;
                tail = tail.next;
            }
        }
    }

    // src.Parking problem
    // mark = 1, else = 0
    // Complexity: O(n) - O(n^2)
    public static int numOfCars(List list) {
        if (list == null) return -1;
        int len = 0;
        list.head.data = 1; // mark the start point
        Node node = list.head.next;
        boolean ans = true;
        while (ans) {
            int counter = 1;
            while (node.data != 1) {
                counter++;
                node = node.next;
            }
            len = counter;
            node.data = 0; // change the mark of the car that we suspect as a start point
            while (counter != 0) {
                node = node.prev;
                counter--;
            }
            if (node.data != 1) { //start point
                ans = false;
            } else node = node.next;
        }
        return len;
    }

    public static void main(String[] args) {
        List list = new List();
        list.add(0);
        list.add(1);
        list.add(1);
        list.add(0);
        list.add(1);
        list.add(1);
        System.out.println("Numbers of cars in the parking len is: " + numOfCars(list));
    }
}
