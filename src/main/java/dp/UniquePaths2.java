package dp;

/**
 * 63. 不同路径 II
 *
 * @author suchao
 * @date 2020/1/8
 * @link https://leetcode-cn.com/problems/unique-paths-ii/
 */
public class UniquePaths2 {

    /*
        方法1：使用递归
     */

    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] grid = new int[m][n];
        return uniquePathsRec(grid, m - 1, n - 1, obstacleGrid);
    }

    public int uniquePathsRec(int[][] grid, int m, int n, int[][] obstacleGrid) {
        if (!valid(m, n, obstacleGrid)) {
            return 0;
        }
        if (atEnd(m, n)) {
            return 1;
        }
        if (grid[m][n] == 0) {
            grid[m][n] = uniquePathsRec(grid, m - 1, n, obstacleGrid) +
                    uniquePathsRec(grid, m, n - 1, obstacleGrid);
        }
        return grid[m][n];
    }

    private boolean atEnd(int row, int col) {
        return row == 0 && col == 0;
    }

    private boolean valid(int row, int col, int[][] obstacleGrid) {
        return row >= 0 && col >= 0 && obstacleGrid[row][col] == 0;
    }

    /*
        方法2：就像斐波那契数列的求解一样，可以转换为迭代求解，以用递推公式
    */

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
        方法3：注意，斐波那契数列的迭代求解中，只需要两个变量，不需要像递推方法中，
        为了记录中间值，需要使用一维数组，同样的，在这个迭代求解中，不需要二维数组，只需要一维数组即可
    */

    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] cur = new int[width];
        // 代表终点处为 1
        cur[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) {
                    cur[j] = 0;
                } else if (j > 0) {
                    cur[j] += cur[j - 1];
                }
            }
        }
        return cur[width - 1];
    }
}
