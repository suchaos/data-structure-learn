package dp;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * <p>
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * @author suchao
 * @date 2020/1/9
 * @link https://leetcode-cn.com/problems/triangle/description/
 */
public class Triangle {

    /*
        1. 找到重复子问题（分治） problem(i,j) = a[i][j] + min(problem(i+1, j), problem(i+i, j+1));
        2. 定义状态数组：a[i][j]
        3. DP 方程：f(i,j) = a[i][j] + min(f(i+1, j), f(i+i, j+1))
     */


    /*
        1. bottom-up 自底向上，只需要一维数组即可
     */

    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] dp = new int[row + 1];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    /*
        2. top-down 自顶向下，需要二维数组
     */

    /*
        3. 递归 + 记忆化搜索
     */
}
