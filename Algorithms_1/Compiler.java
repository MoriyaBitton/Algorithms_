package src;

public class Compiler {

    static class Program {
        String name;
        double len, pro;

        public Program(String name, double len, double pro) {
            this.len = len;
            this.pro = pro;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Program { " +
                    "name= '" + name + '\'' +
                    ", len= " + len +
                    ", pro= " + pro + " " +
                    '}';
        }
    }

    // Complexity: O(n * log(n))
    public static void optimalOrder(Program[] programs) {
        int n = programs.length;
        mergeSort(programs, 0, n);
        int totalTime = 0, totalLen = 0;
        for (int i = 0; i < n; i++) {
            totalTime += (totalLen + programs[i].len) * programs[i].pro;
            totalLen += programs[i].len;
            System.out.println("program number " + i + ": " + programs[i].toString() + " total len: " + totalLen + " pro: " + programs[i].pro);
        }
        System.out.println("Total time: " + totalTime);
    }

    private static void mergeSort(Program[] p, int low, int high) {
        int n = high - low;
        if (n <= 1) return;
        int mid = (low + high) / 2;
        mergeSort(p, low, mid);
        mergeSort(p, mid, high);
        int i = low, j = mid, k = 0;
        Program[] temp = new Program[n];
        while (i < mid && j < high) {
            double t1 = p[i].len / p[i].pro;
            double t2 = p[j].len / p[j].pro;
            if (t1 < t2) temp[k++] = p[i++];
            else temp[k++] = p[j++];
        }
        while (i < mid) temp[k++] = p[i++];
        while (j < high) temp[k++] = p[j++];
        for (int l = 0; l < n; l++) {
            p[low + l] = temp[l];
        }
    }

    public static void main(String[] args) {
        Program a = new Program("a", 5.6, 2.5);
        Program b = new Program("b", 5.6, 2.5);
        Program c = new Program("c", 5.6, 2.5);
        Program d = new Program("d", 5.6, 2.5);
        Program[] pro = {a, b, c, d};
        optimalOrder(pro);
    }
}
