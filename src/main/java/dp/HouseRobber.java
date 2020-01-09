package dp;

/**
 * 198. 打家劫舍
 * <p>
 * <p>
 * [2,1,1,2] 返回 4
 *
 * @author suchao
 * @date 2020/1/9
 * @link https://leetcode-cn.com/problems/house-robber/
 * @see HouseRobber2
 */
public class HouseRobber {

    /*
        DP1:
         1. 重复子问题
         2. 状态数组定义 dp[i] 表示取 0-i 能偷盗的最大金额
                        dp[i][0,1]： 0 代表这天没有偷，1 代表这天偷了
         3. DP 方程：
                 dp[i][0] = max(dp[i-1][0], dp[i-1][1])
                 dp[i][1] = dp[i-1][0] + nums[i]
     */

    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    /*
        DP2:
         1. 重复子问题
         2. 状态数组定义 dp[i] 表示取 0-i 能偷盗最大金额
         3. DP 方程： dp[i] = max(dp[i-1], dp[i-2] + nums[i])
                    由于不可以在相邻的房屋闯入，所以在当前位置 n 房屋可盗窃的最大值，
                    要么就是 n-1 房屋可盗窃的最大值，要么就是 n-2 房屋可盗窃的最大值加上当前房屋的值，
                    二者之间取最大值
     */

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    /*
        将上一个代码进行简化，就和 斐波那契数列 迭代求解只需要两个变量一样，这里也不需要数组
     */

    public int rob3(int[] nums) {
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


    /*
         下面这个是错误例子：一开始以为只是将奇数或偶数的全部加起来即可，理解错题目了
     */

    public int robError(int[] nums) {
        int oddSum = 0;
        int evenSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                evenSum += nums[i];
            } else {
                oddSum += nums[i];
            }
        }
        return Math.max(oddSum, evenSum);
    }
}
