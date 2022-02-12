public class BestAlgorithm {

    // -----------------------------------------------------
    // Solution A - Matrix || O(N^2)
    // bottom up: mat[i][j] = mat[i+1][j] + mat[i][i]
    // -----------------------------------------------------
    public static void bestAlgorithmMatrix(int[] arr) {
        int n = arr.length, sumMax = arr[0], start = 0, end = 0;
        int[][] mat = new int[n][n];
        // init matrix && get maximum arr[i]
        for (int i = 0; i < n; i++) {
            mat[i][i] = arr[i];
            if (arr[i] > sumMax) {
                sumMax = arr[i];
                start = end = i;
            }
        }
        // build matrix
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                mat[i][j] = mat[i + 1][j] + mat[i][i];
                if (sumMax < mat[i][j]) {
                    sumMax = mat[i][j];
                    start = i;
                    end = j;
                }
            }
        }
        System.out.println("Max Sum: " + sumMax + "\nInterval: [" + (start + 1) + ", " + (end + 1) + "]");
    }

    // -----------------------------------------------------
    // Solution B - helper sum array || O(N)
    // -----------------------------------------------------
    public static void bestCompN(int[] arr) {
        int[] help = new int[arr.length];
        int sumMax = arr[0], start = 0, end = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                help[i] = arr[i];
                start = i;
            } else if (help[i - 1] <= 0) {
                help[i] = arr[i];
                start = i;
            } else {
                help[i] = help[i - 1] + arr[i];
            }

            if (sumMax < help[i]) {
                sumMax = help[i];
                end = i;
            }
        }
        System.out.println("Max Sum: " + sumMax + "\nInterval: [" + (start + 1) + ", " + (end + 1) + "]");
    }

    // -----------------------------------------------------
    // Cycle Best - Solution A (Matrix)
    // -----------------------------------------------------
    public static void cycleBestMat(int[] arr) {
        int n = arr.length, sumMax = arr[0], start = 0, end = 0;
        int[][] mat = new int[n][n];
        // init matrix && get maximum arr[i]
        for (int i = 0; i < n; i++) {
            mat[i][i] = arr[i];
            if (arr[i] > sumMax) {
                sumMax = arr[i];
                start = end = i;
            }
        }
        // build matrix - (i<j)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                mat[i][j] = mat[i + 1][j] + mat[i][i];
                if (sumMax < mat[i][j]) {
                    sumMax = mat[i][j];
                    start = i;
                    end = j;
                }
            }
        }
        int sum = mat[0][n - 1];
        // build matrix - (i>j)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                /*(i>j)*/
                mat[i][j] = sum - mat[j + 1][i - 1];
                if (sumMax < mat[i][j]) {
                    sumMax = mat[i][j];
                    start = i;
                    end = j;
                }
            }
        }
        System.out.println("Max Sum: " + sumMax + "\nInterval: [" + (start + 1) + ", " + (end + 1) + "]");
    }

    // -----------------------------------------------------
    // Cycle Best - Solution B (Duplicate Array) || O(N^2)
    // -----------------------------------------------------
    public static void cycleBestDuplicateArray(int[] arr) {
        int n = arr.length, sumMax = arr[0], start = 0, end = 0;
        int[] arrDup = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arrDup[i] = arr[i % n];
        }
        for (int k = 0; k < n; k++) {
            int tempMax = arrDup[k], tempStart = 0, tempEnd = 0;
            int[] help = new int[n];
            for (int i = 0; i < n; i++) {
                int index = i + k;
                if (i == 0) {
                    help[i] = arrDup[index];
                    tempStart = i;
                } else if (help[i - 1] <= 0) {
                    help[i] = arrDup[index];
                    tempStart = i;
                } else {
                    help[i] = help[i - 1] + arrDup[index];
                }
                if (tempMax < help[i]) {
                    tempMax = help[i];
                    tempEnd = i;
                }
            }
            if (sumMax < tempMax) {
                sumMax = tempMax;
                start = tempStart;
                end = tempEnd;
            }
        }
        System.out.println("Max Sum: " + sumMax + "\nInterval: [" + (start + 1) + ", " + (end + 1) + "]");
    }

    // -----------------------------------------------------
    // Cycle Best - Solution C (Mix) || O(N)
    // Max(Best(A), S-(-Best(-A)))
    // -----------------------------------------------------
    public static int cycleBestMix(int[] arr) {
        int maxRegular = best(arr)[0];

        int sum = sum(arr);
        int[] help = minus(arr);
        int maxCycle = sum + best(help)[0];

        return Math.max(maxRegular, maxCycle);
    }

    private static int[] best(int[] arr) {
        int[] help = new int[arr.length];
        int sumMax = arr[0], start = 0, end = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                help[i] = arr[i];
                start = i;
            } else if (help[i - 1] <= 0) {
                help[i] = arr[i];
                start = i;
            } else {
                help[i] = help[i - 1] + arr[i];
            }
            if (sumMax < help[i]) {
                sumMax = help[i];
                end = i;
            }
        }
        return new int[]{sumMax, start, end};
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return sum;
    }

    private static int[] minus(int[] arr) {
        int[] help = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            help[i] = (-1) * arr[i];
        }
        return help;
    }

    // -----------------------------------------------------
    // Gas Station
    // -----------------------------------------------------
    public static int[] gasStation(int[] station, int[] cost) {
        if (sum(station) >= sum(cost)) {
            int n = station.length;
            int[] help = new int[n];
            // init help array
            for (int i = 0; i < n; i++) {
                help[i] = station[i] - cost[i];
            }
            int[] way = best(help);
            System.out.println("Max: " + way[0] + " Start station: " + (way[1] + 1) + " End station: " + (way[2] + 1));
            return way;
        }
        System.out.println(" Sum of Station smaller then Sum of cost ");
        return new int[]{-1};
    }

    public static void main(String[] args) {

        // -----------------------------------
        // Best - Arr
        // -----------------------------------
        System.out.println("Regular Best - Test 1");
        int[] a1 = {1, 2, 3, -50, 2, 4, -34, 4}; // sum=6
        int[] a2 = {1, 2, 3, -50, 2, 4, -34, 6}; // sum=6
        int[] a3 = {3, 3, -50, 1, 2, 3, -34, 4}; // sum=6
        int[] a4 = {1, 2, 2, 1, -50, 2, 4, -34, 1, 2, 3}; //sum=6
        int[] a5 = {-1, -2, -2, -1, -50}; // sum=-1
        int[] a6 = {6, -50, 1, 2, 3, -34, 3, 3};//sum=6
        int[] a7 = {10, 2, -5, 8, -100, 3, 50, -80, 1, 2, 3}; // sum=53
        int[] a8 = {3, -2, 5, 1}; // sum = 7

        int[][] tests = {a1, a2, a3, a4, a5, a6, a7, a8};
        for (int i = 0; i < tests.length; i++) {
            System.out.println("\nTest 1." + (i + 1));
            bestAlgorithmMatrix(tests[i]); // Matrix || O(N^2)
            System.out.println("\t");
            bestCompN(tests[i]); // helper sum array || O(N)
        }

        // -----------------------------------
        // Best - Cycle
        // -----------------------------------
        System.out.println("\nCycle Best - Test 2");
        int[] a11 = {10, 2, -5, 8, -100, 3, 50, -80, 1, 2, 3}; // cycle sum = 53
        int[] a12 = {10, 2, -5, 8, -100, 3, 50, -80, 1, 2, 3, 88}; // cycle sum = 109
        int[] a13 = {10, 2, -5, 8, -100, 3, 150, -180, 1, 2, 3, 88}; // cycle sum = 164
        int[] a14 = {90, 2, -5, 8, -100, 3, 50, -80, 1, 2, 3}; // cycle sum = 101

        int[][] tests2 = {a11, a12, a13, a14};
        for (int i = 0; i < tests2.length; i++) {
            System.out.println("\nTest 2." + (i + 1));
            cycleBestMat(tests2[i]); // Matrix - A
            System.out.println("\t");
            cycleBestDuplicateArray(tests2[i]); // Double Array - B
            System.out.println("\t");
            System.out.println(cycleBestMix(tests2[i]));; // Mix - C
        }

        // -----------------------------------
        // Gas Station
        // -----------------------------------
        System.out.println("\nGas Station - Test 3\n");
        int[] A = {3, 6, 2, 8};
        int[] B = {5, 4, 3, 4};
        gasStation(A, B);

    }
}
