/* dp[i]肯定是其前面的某个数乘以 2 （或乘以 3、乘以 5）得出来的，而dp[i] x 2 、dp[i] x 3、dp[i] x 5 这三个数，肯定也是dp 数组后面某个位置的数。

那就可以推断出来，dp这个数组上的数，每个位置肯定都要x2\x3\x5 一遍，其结果是放在dp 数组后面某个位置。
那我们就可以从这个数组初始的状态，即dp[1]=1 开始，用p2\p3\p5 表示当前该哪个位置该乘以2\3\5 了。我们只要每次取乘以 2、3、5 后的结果中最小的值，那这个最小的值就是最新一个的dp 值，然后相应地移动一下计算出这个新dp 值的 p2（或 p3 或p5）索引，即该下一个数去乘以2（或3 或5）了。按次遍历，计算出第i个数，即为dp[i]
*/

class Solution {
    public int nthUglyNumber(int n) {
        int dp[] = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[p2]*2, dp[p3]*3), dp[p5]*5);
            if(dp[i] == dp[p2]*2) {
                p2 ++; 
            }
            if(dp[i] == dp[p3]*3) {
                p3 ++; 
            }
            if(dp[i] == dp[p5]*5) {
                p5 ++; 
            }
        }
        return dp[n-1];
    }
}