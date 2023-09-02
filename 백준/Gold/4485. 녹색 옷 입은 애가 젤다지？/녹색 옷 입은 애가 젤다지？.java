import java.util.*;
import java.io.*;

public class Main {

	private static int calcMinCost(int[][] grid) {

		int N = grid.length;

		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = -1;
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
		dist[0][0] = grid[0][0];
		pq.offer(new int[] { dist[0][0], 0, 0 });

		int[] dxs = { 1, -1, 0, 0 };
		int[] dys = { 0, 0, 1, -1 };

		while (!pq.isEmpty()) {
			int[] ele = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = ele[1] + dxs[i];
				int ny = ele[2] + dys[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (dist[nx][ny] == -1 || dist[nx][ny] > ele[0] + grid[nx][ny]) {
						dist[nx][ny] = ele[0] + grid[nx][ny];
						pq.offer(new int[] { dist[nx][ny], nx, ny });
					}
				}
			}
		}

		return dist[N - 1][N - 1];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 도시 개수

		int cnt = 1;
		while (N != 0) {
			int[][] grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("Problem " + cnt++ + ": ").append(calcMinCost(grid)).append("\n");

			N = Integer.parseInt(br.readLine());
		}

		System.out.println(sb);
	}
}