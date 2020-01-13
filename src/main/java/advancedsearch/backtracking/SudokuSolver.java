package advancedsearch.backtracking;

/**
 * 37. 解数独
 *
 * @author suchao
 * @date 2020/1/12
 * @link https://leetcode-cn.com/problems/sudoku-solver/
 */
public class SudokuSolver {

    char[] sudo = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /*
          回溯1：return false 的地方很不好理解
          回溯2：安装标准递归程序来写的样子
          回溯3：模仿 n 皇后问题，使用三个 set 来存储 1 - 9 ，如果有了，就直接删去 这样判断比较方便
     */

    /*----------------------回溯1--------------------------------------------------*/

    public void solveSudoku1(char[][] board) {
        dfs(board);
    }

    private boolean dfs(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c : sudo) {
                        if (isValid(i, j, c, board)) {
                            board[i][j] = c;
                            if (dfs(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                            ;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char c, char[][] board) {
        for (int i = 0; i < 9; i++) {
            //check row
            if (board[i][col] != '.' && board[i][col] == c) {
                return false;
            }
            //check column
            if (board[row][i] != '.' && board[row][i] == c) {
                return false;
            }
            //check 3*3 block
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }
        return true;
    }

    /*----------------------回溯2 TODO--------------------------------------------------*/
    /*
        https://leetcode.com/problems/sudoku-solver/discuss/15911/Less-than-30-line-clean-java-solution-using-DFS
     */



    /*----------------------回溯3 TODO--------------------------------------------------*/
}
