class Solution {
    // public int longestCommonSubsequence(String text1, String text2) {
    // int m = text1.length(), n = text2.length();
    // int[][] lcs = new int[m][n];

    // lcs[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
    // for (int i = 1; i < m; i++) {
    // lcs[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : lcs[i-1][0];
    // }
    // for (int i = 1; i < n; i++) {
    // lcs[0][i] = text1.charAt(0) == text2.charAt(i) ? 1 : lcs[0][i-1];
    // }

    // for (int i = 1; i < m; i++) {
    // for (int j = 1; j < n; j++) {
    // if (text1.charAt(i) == text2.charAt(j)) {
    // lcs[i][j] = lcs[i - 1][j - 1] + 1;
    // } else {
    // lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
    // }
    // }
    // }

    // return lcs[m - 1][n - 1];
    // }

    // Optimize space
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] lcs = new int[n];
        int[] last = new int[n];
        last[0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        lcs[0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            last[i] = text1.charAt(0) == text2.charAt(i) ? 1 : last[i - 1];
        }

        for (int i = 1; i < m; i++) {
            lcs = new int[n];
            lcs[0] = text1.charAt(i) == text2.charAt(0) ? 1 : last[0];
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    lcs[j] = last[j - 1] + 1;
                } else {
                    lcs[j] = Math.max(last[j], lcs[j - 1]);
                }
            }
            for (int j = 0; j < n; j++) {
                last[j] = lcs[j];
            }

        }

        return lcs[n - 1];
    }
}