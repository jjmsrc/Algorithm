import java.util.*;

class Solution {
    private int n = 0;
    private int m = 0;
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        this.n = maps.length;
        this.m = maps[0].length;
        
        int[][] dist = new int[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        int[] pos;
        int[] dxs = {1, -1, 0 ,0};
        int[] dys = {0, 0, 1, -1};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = 10_000;
            }
        }
        
        dist[0][0] = 1;
        queue.offer(new int[] {0, 0});
        while(!queue.isEmpty()) {
            pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dxs[i];
                int ny = y + dys[i];
                if (!isIn(nx, ny) || maps[nx][ny] == 0 
                        || dist[nx][ny] <= dist[x][y] + 1)
                    continue;
                dist[nx][ny] = dist[x][y] + 1;
                queue.offer(new int[] {nx, ny});
            }
        }
        
        answer = dist[n - 1][m - 1];
        if (answer == 10_000) {
            answer = -1;
        }
        
        return answer;
    }
    
    private boolean isIn(int x, int y) {
        return x >= 0 && x < this.n && y >= 0 && y < this.m;
    }
}