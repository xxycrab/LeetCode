class Solution {
    public long maximumSumOfHeights(int[] heights) {
        int n = heights.length;
        if (n == 1) {
            return heights[0];
        }

        long[] leftSum = new long[n], rightSum = new long[n];
        leftSum[0] = heights[0];
        rightSum[n - 1] = heights[n - 1];

        Stack<Integer> towers = new Stack<Integer>();
        towers.push(0);

        for (int i = 1; i < n; i++) {
            // Find the next smaller 
            while (!towers.empty() && heights[towers.peek()] > heights[i]) {
                towers.pop();
            }
            int leftNextSmaller = -1;
            if (!towers.empty()) {
                leftNextSmaller = towers.peek();
                leftSum[i] += leftSum[leftNextSmaller];
            }
            leftSum[i] += heights[i] * (long) (i - leftNextSmaller);
            towers.push(i);
        }

        while (!towers.empty()) {
            towers.pop();
        }
        towers.push(n - 1);

        for (int i = n - 2; i >= 0; i--) {
            int rightNextSmaller = n;
            while (!towers.empty() && heights[towers.peek()] > heights[i]) {
                towers.pop();
            }
            if (!towers.empty()) {
                rightNextSmaller = towers.peek();
                rightSum[i] += rightSum[rightNextSmaller];
            }
            rightSum[i] += heights[i] * (long) (rightNextSmaller - i);
            towers.push(i);
        }

        long maxSumOfHeights = 0;
        for (int i = 0; i < n; i++) {
            maxSumOfHeights = Math.max(maxSumOfHeights, leftSum[i] + rightSum[i] - heights[i]);
        }
        return maxSumOfHeights;

    }
}