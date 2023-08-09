import java.io.*;
import java.util.*;


public class Solution {

	static StringBuilder sb;
	static int N;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			grid = new int[N][N];

			for (int r = 0; r < grid.length; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < grid.length; c++) {
					grid[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[] max = { -1, 0 };
			for (int r = 0; r < grid.length; r++) {
				for (int c = 0; c < grid.length; c++) {
					int moved = check(r, c);
					if (moved > max[1] || (moved == max[1] && grid[r][c] < max[0])) {
						max[0] = grid[r][c];
						max[1] = moved;
					}
				}
			}

			sb.append("#" + i + " " + max[0] + " " + max[1] + "\n");
		}

		System.out.println(sb);
	}

	private static int check(int r, int c) {

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { r, c, 1 });

		int bredth = 0;
		while (!q.isEmpty()) {

			int size = q.size();
			
			while (--size >= 0) {
				int[] pos = q.poll();
				int x = pos[0], y = pos[1];
				int xx, yy;
				for (int i = 0; i < 4; i++) {
					xx = x + dx[i];
					yy = y + dy[i];
					if (0 <= xx && xx < N && 0 <= yy && yy < N && grid[xx][yy] == grid[x][y] + 1)
						q.offer(new int[] { xx, yy, pos[2] + 1 });
				}
			}
			bredth++;
		}

		return bredth;
	}

}