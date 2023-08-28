import java.io.*;
import java.util.*;

public class Main {

	static int N, M, dist[][][];
	static char[][] grid;
	
	private static void bfs() {

		Queue<int[]> q = new ArrayDeque<int[]>();
		
		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		q.offer(new int[] {0, 0, 1, 1}); // x, y, 거리, 부술 수 있는지(1/0)
		dist[0][0][0] = dist[0][0][1] = 1;
		
		while(!q.isEmpty()) {
			
			int[] ele = q.poll();
			int nDist = ele[2] + 1;
			
			for (int i = 0; i < 4; i++) {
				int nx = ele[0] + dxs[i];
				int ny = ele[1] + dys[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (grid[nx][ny] == '0') {
						if (ele[3] == 1 && dist[nx][ny][1] > nDist) {
							dist[nx][ny][1] = nDist;
							q.offer(new int[] {nx, ny, nDist, 1});
						} else if (ele[3] == 0 && dist[nx][ny][0] > nDist) {
							dist[nx][ny][0] = nDist;
							q.offer(new int[] {nx, ny, nDist, 0});
						}
					} else if (grid[nx][ny] == '1' && ele[3] == 1) {
						if (dist[nx][ny][0] > nDist) {
							q.offer(new int[] {nx, ny, nDist, 0});
							dist[nx][ny][0] = nDist;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new char[N][];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			grid[i] = line.toCharArray();
		}
		dist = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j][0] = 
				dist[i][j][1] = Integer.MAX_VALUE;
			}
		}

		bfs();
		
		int min = Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
		int ans = min == Integer.MAX_VALUE ? -1 : min;

		System.out.println(ans);
	}

}