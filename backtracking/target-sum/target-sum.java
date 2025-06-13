class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || -target > sum)
            return 0;
        int[] dp = new int[2 * sum + 1];
        dp[nums[0] + sum] += 1;
        dp[sum - nums[0]] += 1;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int[] tmp = new int[2 * sum + 1];
            for (int k = -sum; k <= sum; k++) {
                if (sum + k - num >= 0)
                    tmp[sum + k] += dp[sum + k - num];
                if (k + num <= sum)
                    tmp[sum + k] += dp[sum + (k + num)];
            }
            dp = tmp;
        }
        return dp[target + sum];
    }
}