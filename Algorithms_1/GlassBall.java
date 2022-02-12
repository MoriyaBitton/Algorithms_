package src;

public class GlassBall {

    // b > 2; f(n,b) = min[1<=1<=n] (max(f(n-i,b) + 1, f(i-1,b-1)))
    // Complexity: O(64) = O(1)
    public static int numOfChech2(int n) {
        int[] f = new int[n + 1];
        if (isMeshulashi(n)) {
            return Meshulashi_an(n);
        }
        for (int i = 0; i < 3; i++) {
            f[i] = i;
        }
        for (int i = 3; i < (n + 1); i++) {
            int min = n;
            for (int j = 1; j < i; j++) {
                int broke = j, unBroke = f[i - j] + 1;
                int x = Math.max(broke, unBroke);
                if (x < min) min = x;
            }
            f[i] = min;
        }
        return f[n];
    }

    public static int Meshulashi_an(int n) {
        double k = 0;
        if (isMeshulashi(n)) {
            k = (-0.5) + Math.pow(0.25 + (n + n), 0.5);
        }
        return (int) k;
    }

    private static boolean isMeshulashi(int n) {
        int Sn = 0;
        for (int i = 0; (i < n) || (n == 1); i++) {
            Sn += i;
            if (Sn == n) return true;
        }
        return false;
    }

    // Returns the minimum floor that causes breaking ball - using 2 balls and dividing the building into different parts
    // Complexity: O(sqrt(n)) - sqrt(2*n)
    public static int glassBall2(int[] floors, int ball) {
        int n = floors.length;
        int step = 0;
        if (isMeshulashi(n)) step = Meshulashi_an(n);
        int currentFloor = step;
        while(true) {
            if(floors[currentFloor] > ball) {
                currentFloor = currentFloor - step + 1;
                while(true) {
                    if(floors[currentFloor] > ball) {
                        return currentFloor;
                    }
                    currentFloor++;
                }
            }
            if(currentFloor == n-1) break;
            step--;
            currentFloor += step;
            if(currentFloor > n-1) currentFloor = n-1;
        }
        return n;
    }

    public static void main(String[] args) {
        int n = 15;
        System.out.println("num check: " + numOfChech2(n));
        int[] arr = {1, 20, 23, 56, 54, 45};
        System.out.println("minimum floor that causes breaking ball: " + glassBall2(arr, 2));
    }
}
