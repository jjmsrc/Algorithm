import java.io.*;
import java.util.*;

public class Main {
	
	private static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static int[] dxs = {1, -1, 0, 0};
	private static int[] dys = {0, 0, 1, -1};
	private static Queue<Point> queue;
	private static Queue<Point> markedQueue;
	private static int n, m;
	private static int[][] grid, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		grid = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				grid[i][j] = line.charAt(j) - '0';
			}
		}
		
		queue = new ArrayDeque<>();
		markedQueue = new ArrayDeque<>();
		answer = new int[n][m];
		boolean[][] isVisited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 비어있는 공간을 먼저 훑고 인접한 벽에 해당 개수를 더하기.
				if (!isVisited[i][j] && grid[i][j] == 0) {
					bfs(new Point(i, j), isVisited);
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(grid[i][j] == 0 ? 0 : (answer[i][j] + 1) % 10);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
	}
	
	private static void bfs(Point p, boolean[][] isVisited) {
		
		int cnt = 1;
		
		queue.offer(p);
		isVisited[p.x][p.y] = true;
		
		while(!queue.isEmpty()) {
			Point cp = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cp.x + dxs[i];
				int ny = cp.y + dys[i];
				
				if (!isIn(nx, ny) || isVisited[nx][ny]) continue;
				
				isVisited[nx][ny] = true;
				
				if (grid[nx][ny] == 0) {
					cnt++;
					queue.offer(new Point(nx, ny));
				} else { // grid[nx][ny] = 1
					markedQueue.offer(new Point(nx, ny));
				}
				
			}
			
		}
		
		while(!markedQueue.isEmpty()) {
			Point cp = markedQueue.poll();
			answer[cp.x][cp.y] += cnt;
			isVisited[cp.x][cp.y] = false;
		}
		
		markedQueue.clear();
		queue.clear();
	}
	
	private static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

}