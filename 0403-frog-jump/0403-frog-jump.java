class Solution {
    public boolean canCross(int[] stones) {

        int n = stones.length;

        // dp[i][k] = can we reach stone i
        // with the previous jump being k?
        boolean[][] dp = new boolean[n][n];

        dp[0][0] = true;

        for (int i = 0; i < n; i++) {

            for (int k = 0; k < n; k++) {

                if (!dp[i][k]) {
                    continue;
                }

                for (int jump = k - 1; jump <= k + 1; jump++) {

                    if (jump <= 0) {
                        continue;
                    }

                    int nextPosition = stones[i] + jump;

                    for (int j = i + 1; j < n; j++) {

                        if (stones[j] == nextPosition) {
                            dp[j][jump] = true;

                            if (j == n - 1) {
                                return true;
                            }

                            break;
                        }

                        if (stones[j] > nextPosition) {
                            break;
                        }
                    }
                }
            }
        }

        return n == 1;
    }
}