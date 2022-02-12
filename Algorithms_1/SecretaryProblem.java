package src;

import java.util.Arrays;

public class SecretaryProblem {

    public static double SecretaryProblem(double[] time) {
        int n = time.length;
        if (n == 0) return 0;
        double Avg = 0;
        Arrays.sort(time);
        System.out.println("new order: " + Arrays.toString(time));
        for (int i = 0; i < n; i++) {
            Avg += time[i];
        }
        return (Avg / n);
    }

    public static void main(String[] args) {
        double[] time = new double[]{10, 16, 12, 15, 0, 0.5, 4.7};
        System.out.println("Avg waiting time: " + SecretaryProblem(time));
    }
}
