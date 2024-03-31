import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static int N = 0, M = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				int a = map[i][j] = Integer.parseInt(st.nextToken());
				if (a == 2) {
					queue.offer(new int[] {i, j});
					visited[i][j] = true;
				}
			}
		}
		
		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		
		int dist = 0;
		while(!queue.isEmpty()) {
			for (int i = 0, size = queue.size(); i < size; i++) {
				int[] pos = queue.poll();
				int x = pos[0];
				int y = pos[1];
				map[x][y] = dist;
				for (int j = 0; j < 4; j++) {
					int nx = x + dxs[j];
					int ny = y + dys[j];
					if (isIn(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
						queue.offer(new int[] {nx, ny});
						visited[nx][ny] = true;
					}
				}
			}
			dist++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j])
					map[i][j] = -1;
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());

	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}