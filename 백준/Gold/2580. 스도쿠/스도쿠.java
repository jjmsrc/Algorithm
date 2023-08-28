import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[][] grid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(grid);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(grid[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static int makeFlag(int[][] grid, int r, int c) {

		int res = 0;
		int rr = (r / 3) * 3, cc = (c / 3) * 3;
		for (int i = 0; i < 9; i++) {
			if (grid[r][i] != 0) {
				res |= 1 << grid[r][i];
			}
			if (grid[i][c] != 0) {
				res |= 1 << grid[i][c];
			}
			if (grid[rr + i / 3][cc + i % 3] != 0) {
				res |= 1 << grid[rr + i / 3][cc + i % 3];
			}
		}

		return res;
	}

	private static boolean dfs(int[][] grid) {

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (grid[r][c] == 0) {
					// 행 확인
					int flag = makeFlag(grid, r, c);

					for (int i = 1; i <= 9; i++) {
						if ((flag & (1 << i)) == 0) {
							grid[r][c] = i;
							if (dfs(grid))
								return true;
							else
								grid[r][c] = 0;
						}
					}

					return false;
				}

			}
		}

		return true;
	}

}