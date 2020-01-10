package dp;

/**
 * 123. 买卖股票的最佳时机 III
 *
 * @author suchao
 * @date 2020/1/10
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStock3 {

    /*
        定义状态：dp[i][k][o or 1]: n 为天数；大 K 为最多交易数； 0 代表今天没有持股，1 代表持股
        DP 方程：
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
     */

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int k = 2;
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    // base case
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }
}
