class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || -target > sum)
            return 0;
        // dp[i][j] represents number of solutions that i elements can result in j. 0 <= j <= 2*sum, here dp[i][0] represents the case of elements resulting in -sum, etc.
        // dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]], as when considering nums[i] we can either add or substract
        // We need to find dp[n][target+sum(nums)], i.e. the solutions that result in target+sum
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