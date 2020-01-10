package dp;

/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * @author suchao
 * @date 2020/1/10
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @see BestTimeToBuyAndSellStock2
 */
public class BestTimeToBuyAndSellStockWithCooldown {

        /*
        定义状态：dp[i][o or 1]: n 为天数； 用 1 表示持有，0 表示没有持有
        DP 方程：
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);

                base case:
                    dp[-1][0] = 0;
                    dp[-2][0] = 0;
                    dp[-1][1] = Integer.MIN_VALUE;
     */

    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(-prices[0], -prices[1]);
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /*
        压缩空间，代码如下
     */

    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }


    /*
        重新写 DP 方程：可以画个 0 1 2 三种状态的状态转移图
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
            dp[i][2] = dp[i - 1][0];
     */

    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][3];
        // 初始化
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
            dp[i][2] = dp[i - 1][0];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }
}
