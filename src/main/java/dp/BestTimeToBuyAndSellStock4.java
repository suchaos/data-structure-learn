package dp;

/**
 * 188. 买卖股票的最佳时机 IV
 *
 * @author suchao
 * @date 2020/1/10
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class BestTimeToBuyAndSellStock4 {

    /*
        定义状态：dp[i][k][o or 1]: n 为天数；大 K 为最多交易数； 0 代表今天没有持股，1 代表持股
        DP 方程：
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
     */

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        if (k > prices.length / 2) {
            // 相当于 k 无限大
            return max_profit_k_inf(prices);
        } else {
            return max_profit_k(k, prices);
        }
    }

    private int max_profit_k_inf(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }

    private int max_profit_k(int k, int[] prices) {
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
