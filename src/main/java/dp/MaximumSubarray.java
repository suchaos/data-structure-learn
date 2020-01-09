package dp;

/**
 * 53. 最大子序和
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6
 *
 * @author suchao
 * @date 2020/1/9
 * @link https://leetcode-cn.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

    /*
        1. 重复子问题 max_sum(i) = max(max_sum(i-1), 0) + a[i]
        2. 状态数组定义 a[i] 表示如果加上第 i 个元素是的最大子序和是多少
                            其实就是如果前面的最大子序和是正数，那么此时就是加上这个元素，
                            否则就是直接舍弃前面的，直接是 0 加上这个元素
        3. DP 方程  f(i) = max(f(i-1), 0) + a[i]
     */

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxSubArray3(int[] nums) {
        int max = nums[0];
        int[] dp = nums.clone();
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
