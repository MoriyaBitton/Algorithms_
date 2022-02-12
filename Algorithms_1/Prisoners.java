package src;

public class Prisoners {

    // lamp on
    public static int prisonerLampOn(int n) {
        boolean[] visited = new boolean[n]; // if the prisoner was in the room
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        boolean lamp = true;
        int counter = 0, step = 0;
        while (counter < n) {
            step++;
            int p = (int) (Math.random() * n); // random prisoner that walk into the room
            int prisoner_ = 0; // the one who turn on the lamp
            if (p == prisoner_) {
                if (!visited[prisoner_]) {
                    visited[prisoner_] = true;
                    counter++;
                }
                if (!lamp) {
                    lamp = true;
                    counter++;
                }
            } else { // prisoners 1 to (n-1) walk into the room
                if (!visited[p] && lamp) {
                    lamp = false; // turn off the lamp at the first time p walk in
                    visited[p] = true;
                }
            }
        }
        return step;
    }

    // lamp off or on
    public static boolean prisonerLampUnknow(int n) {
        int[] visited = new int[n]; // how many times the prisoner was in the room
        for (int i = 0; i < n; i++) {
            visited[i] = 0;
        }
        int lamp = (int) (Math.random() * 2); // 0 - off, 1 - on
        int counter = 0;
        while (counter < (2 * n)) {
            int p = (int) (Math.random() * n); // random prisoner that walk into the room
            int prisoner_ = 0; // the one who turn on the lamp
            if ((p == prisoner_) && (lamp == 0)) { // lamp off
                lamp = 1;
                counter++;
                if (visited[prisoner_] == 0) {
                    counter++;
                    visited[prisoner_]++;
                }
            } else { // prisoners 1 to (n-1) walk into the room
                if ((visited[p] < 2) && (lamp == 1)) {
                    lamp = 0; // turn off the lamp at the first time p walk in
                    visited[p]++;
                }
            }
        }
        boolean allVisit = true;
        for (int i = 0; i < n && allVisit; i++) {
            if (visited[i] == 0) {
                allVisit = false;
            }
        }
        return allVisit;
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println("all prisoner walk into the room in " + prisonerLampOn(n) + " steps!");
        System.out.println("all prisoner visit in the room? " + prisonerLampUnknow(n));

    }
}
