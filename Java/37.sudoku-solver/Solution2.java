/*
 * @lc app=leetcode.cn id=37 lang=java
 *
 * [37] 解数独
 *
 * https://leetcode-cn.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (50.94%)
 * Total Accepted:    5.2K
 * Total Submissions: 10K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 
 * 一个数独的解法需遵循如下规则：
 * 
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 
 * 空白格用 '.' 表示。
 * 
 * 一个数独。
 * 
 * 答案被标成红色。
 * 
 * Note:
 * 
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
class Solution {
    private boolean[][] row = new boolean[9][9];
    private boolean[][] col = new boolean[9][9];
    private boolean[][] box = new boolean[9][9];

    private boolean isValid(char[][] board, int x, int y, char candidate) {
        int n = candidate - '1';
        return !row[x][n] && !col[y][n] && !box[(x / 3) * 3 + y / 3][n];
    }

    private boolean solver(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (isValid(board, i, j, k)) {
                            int n = k - '1';
                            row[i][n] = true;
                            col[j][n] = true;
                            box[(i / 3) * 3 + j / 3][n] = true;
                            board[i][j] = k;
                            if (solver(board)) return true;
                            else {
                                row[i][n] = false;
                                col[j][n] = false;
                                box[(i / 3) * 3 + j / 3][n] = false;
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int n = board[i][j] - '1';
                    row[i][n] = true;
                    col[j][n] = true;
                    box[(i / 3) * 3 + j / 3][n] = true;
                }
            }
        }
        solver(board);
    }
}

