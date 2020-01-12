package backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 36. 有效的数独
 * <p>
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * @author suchao
 * @date 2020/1/11
 * @link https://leetcode-cn.com/problems/valid-sudoku/description/
 */
public class ValidSudoku {

    /*
        1. 遍历棋盘三次：每次检测一个条件，额外空间占用少
        2. 遍历棋盘一次：额外空间占用多
     */

    public boolean isValidSudoku1(char[][] board) {
        // 判断行有没有重复的 1-9
        Set<Character> row = new HashSet<>();
        for (char[] chars : board) {
            row.clear();
            for (char aChar : chars) {
                if (aChar == '.') {
                    continue;
                }
                if (row.contains(aChar)) {
                    return false;
                } else {
                    row.add(aChar);
                }
            }
        }
        // 判断列有没有重复的 1-9
        Set<Character> col = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            col.clear();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                if (col.contains(board[j][i])) {
                    return false;
                } else {
                    col.add(board[j][i]);
                }
            }
        }
        // 在每一个以粗实线分隔的 3x3 宫内只能出现一次
        Set<Character> boxes = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            boxes.clear();
            int r = i / 3 * 3;
            int c = i % 3 * 3;
            for (int a = r; a < r + 3; a++) {
                for (int b = c; b < c + 3; b++) {
                    if (board[a][b] == '.') {
                        continue;
                    }
                    if (boxes.contains(board[a][b])) {
                        return false;
                    } else {
                        boxes.add(board[a][b]);
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        Map<Integer, Integer>[] row = new HashMap[9];
        Map<Integer, Integer>[] col = new HashMap[9];
        Map<Integer, Integer>[] box = new HashMap[9];

        for (int i = 0; i < 9; i++) {
            row[i] = new HashMap<>();
            col[i] = new HashMap<>();
            box[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int number = (int) board[i][j];
                    row[i].put(number, row[i].getOrDefault(number, 0) + 1);
                    col[j].put(number, col[j].getOrDefault(number, 0) + 1);
                    int boxIndex = i / 3 * 3 + j / 3;
                    box[boxIndex].put(number, box[boxIndex].getOrDefault(number, 0) + 1);

                    if (row[i].get(number) > 1 || col[j].get(number) > 1 || box[boxIndex].get(number) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
