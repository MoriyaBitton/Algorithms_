package src;

import java.util.Arrays;

class Median {

    // Complexity: O(64) = O(1)
    public static int median64(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        if (n < 64) {
            int[] a = new int[(int) (n / 2)];
            Arrays.sort(a); // O(log(n))
            max = a[a.length - 1];
        } else {
            for (int i = 0; i < 64; i++) { // O(64) = O(1)
                if (arr[i] > max) max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 0, 8, 0, 8, 908, 2929, 828, 9292, 2, 3, 3, 3, 38, 908, 321, 922, 22,
                2929, 828, 9292, 2, 3, 3, 3, 31, 3, 5, 7, 0, 8, 0, 8, 908, 2929, 828, 9292, 2, 3, 3, 3, 38, 908,
                29, 2132, 33, 590, 8, 908, 2929, 828, 9292, 2, 3, 3, 3, 38, 908, 29, 38, 908, 29, 2132, 33, 590};
        System.out.println("Max number: " + median64(arr));
    }
}
