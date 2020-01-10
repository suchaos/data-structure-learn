package dp;

import javax.swing.*;

/**
 * 121. 买卖股票的最佳时机
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author suchao
 * @date 2020/1/10
 * @link https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimeToBuyAndSellStock {

    /*
        1. 暴力求解：就是求解 price[j] - price[i] 的最大值, 其中 j > i
                    两层循环就可以解决，但是时间复杂度是 n^2
        2. 双指针一次遍历：两个指针 buy, sell, (sell > buy),
                         求解 price[sell] - price[buy] 的最大值
                         可以画个折线图理解，只要 price[sell] <= price[buy], buy = sell, sell++

        3. DP

     */


    public int maxProfit1(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;
    }

    public int maxProfit2(int[] prices) {
        int max = 0;
        for (int buy = 0, sell = 0; sell < prices.length; sell++) {
            if (prices[sell] <= prices[buy]) {
                buy = sell;
            } else if (prices[sell] - prices[buy] > max) {
                max = prices[sell] - prices[buy];
            }
        }
        return max;
    }
}
