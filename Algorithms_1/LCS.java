public class LCS {

    // greedy
    // Complexity: O(n*m)
    public static String GreedyLCS(String l1, String l2) { //O(n*m) n=l1.len, m=l2.len
        String ans = "";
        int start = 0;
        for (int i = 0; i < l1.length(); i++) {
            int index = l2.indexOf(l1.charAt(i), start);
            if (index != -1) {
                ans = ans + l1.charAt(i);
                start = index + 1;
            }
        }
        return ans;
    }

    // dynamic search
    // Complexity: O(n*m)
    public static int LCSsize(String l1, String l2) {
        int n = l1.length() + 1;
        int m = l2.length() + 1;
        int[][] matrix = new int[n][m];
        for (int i = 0; i < l1.length() + 1; i++) matrix[i][0] = 0;
        for (int j = 0; j < l2.length() + 1; j++) matrix[0][j] = 0;
        for (int i = 1; i < l1.length() + 1; i++) {
            for (int j = 1; j < l2.length() + 1; j++) {
                if (l1.charAt(i - 1) == l2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[n - 1][m - 1];
    }

    public static String LCSstring(String l1, String l2) {
        int len = LCSsize(l1, l2);
        int i = l1.length();
        int j = l2.length();
        String ans = "";
        while (len > 0) {
            if (l1.charAt(i - 1) == l2.charAt(j - 1)) {
                ans = l1.charAt(i - 1) + ans;
                i--;
                j--;
                len--;
            } else {
                if (LCSsize(l1.substring(0, i - 1), l2) > LCSsize(l1, l2.substring(0, j - 1))) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String a = "abcdooeu";
        String b = "abdadu";
        String ans_greedy = GreedyLCS(a, b);
        System.out.println("LCS_greedy: " + ans_greedy);
        int ans_size = LCSsize(a, b);
        String ans_string = LCSstring(a, b);
        System.out.println("LCS_size: " + ans_size);
        System.out.println("LCS_string: " + ans_string);
    }
}

