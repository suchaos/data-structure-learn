package backtracking;

/**
 * 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @author suchao
 * @date 2020/1/11
 * @link https://leetcode-cn.com/problems/climbing-stairs/
 */
public class ClimbingStairs {

    /*
        这个问题可以看作是动态规划，也可以看作是回溯算法

        公式 f(n) = f(n-1) + f(n-2)
            f(1) = 1, f(2) = 2
     */

    /*
        1. 动态规划，递推形式，压缩空间，使用三个变量即可
     */

    public int climbStairs1(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1, b = 2, c = 3;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /*
        2. 回溯算法 递归中进行剪枝
        公式 climbStairs(i,n)=(i+1,n)+climbStairs(i+2,n)
     */

    public int climbStairs2(int n) {
        int memo[] = new int[n + 1];
        return helper(0, n, memo);
    }

    private int helper(int level, int max, int[] memo) {
        // terminator
        if (level > max) {
            return 0;
        }
        if (level == max) {
            return 1;
        }
        if (memo[level] > 0) {
            return memo[level];
        }
        // process and drill down
        memo[level] = helper(level + 1, max, memo) + helper(level + 2, max, memo);
        return memo[level];
    }
}
