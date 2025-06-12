class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] profits1 = new int[n];
        int[] profits2 = new int[n];

        int lowest = prices[0], highest = prices[n-1];
        // profits1 calculate the max profits if a trade is made before i-th day
        for(int i = 1; i < n; i ++) {
            lowest = Math.min(lowest, prices[i]);
            profits1[i] = Math.max(profits1[i-1], prices[i] - lowest);
        }
        // profits2 calculate the max profits if a trade is made after i-th day
        for(int i = n-2; i >= 0; i --) {
            highest = Math.max(highest, prices[i]);
            profits2[i] = Math.max(profits2[i+1], highest -  prices[i]);
        }

        int maxPro = 0;
        for(int i =0; i < n; i ++) {
            maxPro = Math.max(maxPro, profits1[i] + profits2[i]);
        }

        return maxPro;
    }
}