package src;

public class LDS {

    // src.LDS dynamic search
    // Complexity: len: O(n * log(n)), string: O(n^2)
    public static int[] LDSdynamic(int[] arr) {
        int n = arr.length;
        int[][] matrix = new int[n][n];
        matrix[0][0] = arr[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            int index = binarySearch(matrix, len, arr[i]);
            matrix[index][index] = arr[i];
            if (index == len) len++;
            for (int j = 0; j < index; j++) {
                matrix[index][j] = matrix[index - 1][j];
            }
        }
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = matrix[len - 1][i];
        }
        return ans;
    }

    private static int binarySearch(int[][] matrix, int end, int i) {
        if (i < matrix[end - 1][end - 1]) return end;
        if (i > matrix[0][0]) return 0;
        int low = 0;
        while (low < end + 1) {
            if (low == end) return low;
            int mid = (end + low) / 2;
            if (matrix[mid][mid] == i) return mid;
            if (matrix[mid][mid] > i) {
                low = mid + 1;
            } else {
                end = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 100, 101, 2, 3, 4, 5, 6, 70, 13};
        int[] aLIS = LDSdynamic(a);
        System.out.print(aLIS[0]);
        for (int i = 1; i < aLIS.length; i++) {
            System.out.print(", " + aLIS[i]);
        }
    }
}
