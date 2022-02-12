package src;

public class Max_Min {

    // max
    // Complexity: O(n)
    public static void getMaxFromArr(int[] arr) {
        if (arr == null || arr.length == 0) System.out.println("No Max value");
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        System.out.println("Max = " + max);
    }

    // min
    // Complexity: O(n)
    public static void getMinFromArr(int[] arr) {
        if (arr == null || arr.length == 0) System.out.println("No Max value");
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        System.out.println("Min = " + min);
    }

    // max & min
    // Complexity: O(n) - 2n
    public static void minMax_oneByOne(int[] arr) {
        if (arr == null || arr.length == 0) System.out.println("No Max/ Min value");
        int comparisons = 0;
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            comparisons++;
            if (arr[i] > max) max = arr[i];
            else {
                comparisons++;
                if (arr[i] < min) min = arr[i];
            }
        }
        System.out.println("Max = " + max + " , Min = " + min + " , Comparisons = " + comparisons);
    }

    // max & min
    // Complexity: O(n) - 3/2(n) comparisons
    public static void minMax_twoForTime(int[] arr) {
        if (arr == null || arr.length == 0) System.out.println("No Max/ Min value");
        int comparisons = 0;
        if (arr.length == 1) {
            System.out.println("Max = " + arr[0] + " , Min = " + arr[0] + " , Comparisons = " + comparisons);
        }
        int max, min;
        comparisons++;
        if (arr[0] > arr[1]) {
            max = arr[0];
            min = arr[1];
        } else {
            max = arr[1];
            min = arr[0];
        }
        for (int i = 3; i < arr.length; i += 2) {
            comparisons++;
            if (arr[i] > arr[i - 1]) {
                comparisons += 2;
                if (arr[i] > max) max = arr[i];
                if (arr[i - 1] < min) min = arr[i - 1];
            } else {
                comparisons += 2;
                if (arr[i - 1] > max) max = arr[i - 1];
                if (arr[i] < min) min = arr[i];
            }
        }

        if (arr[arr.length - 1] > max) {
            comparisons++;
            max = arr[arr.length - 1];
        } else {
            comparisons++;
            if (arr[arr.length - 1] < min) min = arr[arr.length - 1];
        }
        System.out.println("Max = " + max + " , Min = " + min + " , Comparisons = " + comparisons);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 66, 23, 568, 748, 465, 23, 45, 23, 465, 23, 45, 232};
        getMaxFromArr(arr);
        getMinFromArr(arr);
        minMax_oneByOne(arr);
        minMax_twoForTime(arr);
    }
}
