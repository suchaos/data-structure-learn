package backtracking;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * 51. N皇后
 * <p>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * @author suchao
 * @date 2020/1/11
 * @link https://leetcode-cn.com/problems/n-queens/
 */
public class NQueens {

    /*
        回溯问题 进行剪枝
                1. 不在这一列中
                2. 不在右斜线上 row + col = i + c
                3. 不在左斜线上 row - col = i - c
         方法1：比较直接，写了一个方法来进行
         方法2：增加 3 个 set 方便进行剪枝
     */

    /*  ------------------------方法1-------------------------------------------   */

    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        backtrack(0, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int level, int max, List<String> list, List<List<String>> result) {
        // terminator
        if (level == max) {
            result.add(new ArrayList<>(list));
        }
        // process
        for (int i = 0; i < max; i++) {
            // 如果不在被攻击的范围内
            if (!isValid(level, i, max, list)) {
                continue;
            }
            char[] charArray = new char[max];
            Arrays.fill(charArray, '.');
            charArray[i] = 'Q';
            String rowString = new String(charArray);

            list.add(rowString);
            // drill down
            backtrack(level + 1, max, list, result);
            // restore
            list.remove(rowString);
        }
    }

    private boolean isValid(int row, int col, int length, List<String> list) {
        // 因为是从上往下摆棋子，所以不用考虑这行下面
        // 不在这一列中
        for (String s : list) {
            if (s.charAt(col) == 'Q') {
                return false;
            }
        }
        // 不在右斜线上 row + col = i + c
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            int c = row + col - i;
            if (0 <= c && c < length) {
                if (s.charAt(c) == 'Q') {
                    return false;
                }
            }
        }
        // 不在左斜线上 row - col = i - c
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            int c = i - (row - col);
            if (0 <= c && c < length) {
                if (s.charAt(c) == 'Q') {
                    return false;
                }
            }
        }
        return true;
    }


    /*  --------------------------方法2------------------------------------------   */

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        dfs(0, n, result, new ArrayList<String>());
        return result;
    }

    private Set<Integer> col = new HashSet<>();
    private Set<Integer> diag1 = new HashSet<>();
    private Set<Integer> diag2 = new HashSet<>();

    private void dfs(int row, int max, List<List<String>> result, List<String> list) {
        // terminator
        if (row == max) {
            result.add(new ArrayList<>(list));
            return;
        }
        // process and drill down
        for (int c = 0; c < max; c++) {
            // 如果不在被攻击的范围内
            if (col.contains(c) || diag1.contains(row + c) || diag2.contains(row - c)) {
                continue;
            }
            char[] charArray = new char[max];
            Arrays.fill(charArray, '.');
            charArray[c] = 'Q';
            String rowString = new String(charArray);

            list.add(rowString);
            col.add(c);
            diag1.add(row + c);
            diag2.add(row - c);

            dfs(row + 1, max, result, list);

            list.remove(list.size() - 1);
            col.remove(c);
            diag1.remove(row + c);
            diag2.remove(row - c);
        }
    }
}
