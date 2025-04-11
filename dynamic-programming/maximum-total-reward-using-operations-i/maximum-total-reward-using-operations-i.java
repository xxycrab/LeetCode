class Solution {
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);

        int[][]dp = new int[rewardValues.length][4000];
        for (int i = 0; i < rewardValues.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return traverse(rewardValues, 0, 0, dp);
    }

    private int traverse(int[] rewardValues, int i, int totalReward, int[][] dp) {
        if (i == rewardValues.length) {
            return totalReward;
        }
        if (dp[i][totalReward] != -1) {
            return dp[i][totalReward];
        }

        int take = rewardValues[i] > totalReward ? traverse(rewardValues, i+1, totalReward + rewardValues[i], dp):0;
        int skip = traverse(rewardValues, i+1, totalReward, dp);

        dp[i][totalReward] = Math.max(take, skip);

        return dp[i][totalReward];
    }
}