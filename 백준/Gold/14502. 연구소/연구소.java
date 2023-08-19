import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int cntSafeArea;
	static int ans;
	
	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static int[][] grid;
	static int[][] tmpGrid;
	
	static int spread(int[] pos, int[][] tmpGrid) {
		int cntInfected = 0;
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(pos);
		tmpGrid[pos[0]][pos[1]] = 3;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for (int[] d : deltas) {
				int nx = p[0] + d[0];
				int ny = p[1] + d[1];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && tmpGrid[nx][ny] == 0) {
					q.offer(new int[] {nx, ny});
					tmpGrid[nx][ny] = 3;
					cntInfected++;
				}
			}
		}
		return cntInfected;
	}

	static void simulate() {
		int tmpAns = cntSafeArea - 3;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpGrid[i][j] = grid[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpGrid[i][j] == 2) { // 바이러스가 있다면
					tmpAns -= spread(new int[] {i, j}, tmpGrid);
				}
			}
		}
		
		ans = Math.max(ans, tmpAns);
	}

	static void comb(int cnt, int si, int sj, int[][] walls) {
		if (cnt == 3) {
			simulate();
			return;
		}
		
		for (int i = si; i < N; i++) {
			for (int j = (i == si ? sj : 0); j < M; j++) {
				if (grid[i][j] == 0) {
					walls[cnt][0] = i;
					walls[cnt][1] = j;
					grid[i][j] = 1;
					comb(cnt + 1, i, j + 1, walls);
					grid[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] == 0)
					cntSafeArea++;
			}
		}
		tmpGrid = new int[N][M];
		
		comb(0, 0, 0, new int[3][2]);

		sb.append(ans);
		System.out.println(sb);

	}

}