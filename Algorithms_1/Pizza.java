package src;

public class Pizza {

    // returns the optimal division for the faster man
    // Complexity: O(1)
    public static int getNumberOfPieces(double eatSpeed, int pieces) {
        eatSpeed = (int) eatSpeed + 1;
        int p = (int) (pieces / (eatSpeed + 1)), r = (int) (pieces % (eatSpeed + 1));
        int ans = -1; // r = 1 forbidden state
        if (r != 1) {
            double t = (eatSpeed * p + r - 1) / ((eatSpeed + 1) * p + r);
            if (t < (eatSpeed / (eatSpeed + 1))) {
                ans = 1;
            } else ans = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int k = 3, n = 5;
        System.out.println("optimal division for the faster man: " + getNumberOfPieces(k, n));
    }
}
