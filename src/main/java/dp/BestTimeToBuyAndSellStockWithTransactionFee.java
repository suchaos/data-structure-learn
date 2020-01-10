package dp;

/**
 * 714. 买卖股票的最佳时机含手续费
 *
 * @author suchao
 * @date 2020/1/10
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    /*
        定义状态：dp[i][o or 1]: n 为天数； 用 1 表示持有，0 表示没有持有
        DP 方程：
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
     */

    public int maxProfit1(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1] - fee);
        dp[1][1] = Math.max(-prices[0], -prices[1]);
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /*
        在上一个的基础上，压缩空间
     */

    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = -prices[0];
        for (int i = 1; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i] - fee);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }
}
