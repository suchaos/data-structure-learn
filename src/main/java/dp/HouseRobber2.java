package dp;

import java.util.Arrays;

/**
 * 213. 打家劫舍 II
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 *
 * @author suchao
 * @date 2020/1/9
 * @link https://leetcode-cn.com/problems/house-robber-ii/
 * @see HouseRobber
 */
public class HouseRobber2 {

    /*
         和 HouseRobber 相比，可以直接拆分为两个部分，
            1. 开头偷了，因此最后一个点不能偷，所以直接 robHelper(Arrays.copyOfRange(nums, 0, nums.length - 1))
            2. 开头不偷，robHelper(Arrays.copyOfRange(nums, 1, nums.length))
     */

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int a = robHelper(Arrays.copyOfRange(nums, 0, nums.length - 1));
        int b = robHelper(Arrays.copyOfRange(nums, 1, nums.length));
        return Math.max(a, b);
    }

    public int robHelper(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int preMax = 0;
        int curMax = 0;

        for (int num : nums) {
            int temp = curMax;
            curMax = Math.max(curMax, preMax + num);
            preMax = temp;
        }

        return curMax;
    }
}
