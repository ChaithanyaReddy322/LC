class Solution {
    private record State(int x, int y, int mask, int energy) {}; 
    public int minMoves(String[] c, int e) {
        // Min operations - BFS 
        // litter are cleared once visted - need to store this only 10 - bitmask 
        // visited array to avoid TLE 
        // visited depends on: x, y, mask, energy 

        int x = 0, y = 0, pos = 0; // litter start & cnt

        int row = c.length, col = c[0].length(); 
        int lit[][] = new int[21][21]; 
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(c[i].charAt(j) == 'L') {
                    lit[i][j] = pos++; 
                }

                if(c[i].charAt(j) == 'S') {
                    x = i; 
                    y = j; 
                }
            }
        }
        if(pos == 0) return 0; // no litter

        int mask = (1 << pos) - 1; // set all bits, lit = 3, pos = 4, mask = 111

        Queue<State> q = new ArrayDeque<>(); 
        q.offer(new State(x, y, mask, e)); 
        int steps = 1; 

        int dir[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; 
        boolean vis[][][][] = new boolean[21][21][(1 << pos)][e + 1]; 
        vis[x][y][mask][e] = true; 

        while(q.size() > 0) {
            int sz = q.size(); 
            while(sz-- > 0) {
                State cur = q.poll(); 
                int curMask = cur.mask; 
                int curEn = cur.energy; 

                if(curEn == 0) continue; // no energy 

                for(int d[]: dir) {
                    int nx = cur.x + d[0], ny = cur.y + d[1]; 

                    if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue; // invalid 
                    if(c[nx].charAt(ny) == 'X') continue; // blocked

                    int nwMask = curMask, nwEn = curEn; 

                    if(c[nx].charAt(ny) == 'L') {
                        // means we have litter 
                        nwMask &= ~(1 << lit[nx][ny]); // unset the bit 
                    }
                    if(nwMask == 0) return steps; 
                    nwEn -= 1; 

                    if(c[nx].charAt(ny) == 'R') nwEn = e; //reset 
                    // System.out.println(nx + " " + ny + " " + nwMask + " " + nwEn); 
                    if(!vis[nx][ny][nwMask][nwEn] && nwEn > 0) {
                        // not already visited 
                        vis[nx][ny][nwMask][nwEn] = true; 
                        q.offer(new State(nx, ny, nwMask, nwEn)); 
                    }
                }
            }
            steps++; 
        }

        return -1; 
    }
}