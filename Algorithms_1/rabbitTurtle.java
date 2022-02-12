package src;

public class rabbitTurtle {

    public static int Id = 0;

    public static class Node {
        Node next;
        int data;

        public Node() {
            data = Id;
            next = null;
            Id++;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static class List {
        Node head;

        public List() {
            head = null;
        }

        public Node getHead() {
            return head;
        }

        public void add(Node node) {
            if (head == null) {
                head = node;
                head.next = null;
            } else {
                node.next = head;
                head = node;
            }
        }
    }

    // Rabbit and turtle - cycle list
    public static boolean isCycle(List cars) {
        if (cars.head == null) return true;
        Node rabbit = cars.getHead(), turtle = cars.getHead();
        boolean flag = true, ans = false;

        while (flag) {
            if (turtle.next == null || rabbit.next == null || rabbit.next.next == null) {
                flag = false; // no cycle
            } else {
                turtle = turtle.next;
                rabbit = rabbit.next.next;
                if (turtle.data == rabbit.data) { // there is cycle
                    flag = false; // out of while, found point meeting
                    ans = true; // is cycle
                }
            }
        }
        return ans;
    }

    // Rabbit and turtle - cycle list
    public static int armLength(List cars) {
        int ans = -1;
        boolean flag = true, toRun = true;
        Node rabbit = cars.getHead(), turtle = cars.getHead();

        while (flag) {
            if (turtle.next == null || rabbit.next == null || rabbit.next.next == null) {
                flag = false; // no cycle
                toRun = false;
            } else {
                turtle = turtle.next;
                rabbit = rabbit.next.next;
                if (turtle.data == rabbit.data) {
                    flag = false; // out of while, found point meeting
                }
            }
        }
        rabbit = cars.getHead(); // put rabbit back to the head of the list
        while (toRun) {
            ans++;
            if (turtle.data == rabbit.data) {
                toRun = false;
            } else {
                turtle = turtle.next;
                rabbit = rabbit.next;
            }
        }
        System.out.println("cycle size: " + (cars.head.data - ans + 1));
        return ans;
    }

    public static void main(String[] args) {
        List list = new List();
        Node node_0 = new Node();
        Node node_1 = new Node();
        Node node_2 = new Node();
        Node node_3 = new Node();
        Node node_4 = new Node();
        list.add(node_0);
        list.add(node_1);
        list.add(node_2);
        list.add(node_3);
        list.add(node_4);
        node_0.setNext(node_1);

        System.out.println("Cars parking list is cycle? " + isCycle(list));
        System.out.println("Arm len is: " + armLength(list));
    }
}
