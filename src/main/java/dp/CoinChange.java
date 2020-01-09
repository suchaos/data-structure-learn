package dp;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * @author suchao
 * @date 2020/1/9
 * @link https://leetcode-cn.com/problems/coin-change/
 */
public class CoinChange {

    /*
          1. 暴力递归，全部路径全部输出
          2. BFS 广度优先，也就是按层遍历，找到第一个结点为 0 的 level
          3. DP
            a, subProblem(n) = min(subProblem(n-k)) + 1, (k in coins)
            b. DP array
            c. DP function: f(n) = min(f(n-1), f(n-2), f(n-5)) + 1
     */

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
