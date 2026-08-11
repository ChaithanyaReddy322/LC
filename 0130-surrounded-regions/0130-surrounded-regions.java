class Solution {
    public void solve(char[][] board) {

        int r = board.length;
        int c = board[0].length;

        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            if (board[i][0] == 'O')
                dfs(board, visited, i, 0);

            if (board[i][c - 1] == 'O')
                dfs(board, visited, i, c - 1);
        }

        for (int j = 0; j < c; j++) {
            if (board[0][j] == 'O')
                dfs(board, visited, 0, j);

            if (board[r - 1][j] == 'O')
                dfs(board, visited, r - 1, j);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, boolean[][] visited, int i, int j) {

        if (i < 0 || j < 0 ||
            i >= board.length || j >= board[0].length ||
            board[i][j] == 'X' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        dfs(board, visited, i - 1, j);
        dfs(board, visited, i + 1, j);
        dfs(board, visited, i, j - 1);
        dfs(board, visited, i, j + 1);
    }
}