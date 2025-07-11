class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(dp[i - j] * j, (i - j) * j));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }

}