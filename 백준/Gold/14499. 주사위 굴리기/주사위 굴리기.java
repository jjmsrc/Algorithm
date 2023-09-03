import java.io.*;
import java.util.*;

public class Main {

	private static class Dice {
		int[] nums;
		int[] n = { 2, 1, 5, 6 }; // 1 5 6 2
		int[] s = { 6, 5, 1, 2 }; // 5 1 2 6
		int[] e = { 3, 1, 4, 6 }; // 1 4 6 3
		int[] w = { 6, 4, 1, 3 }; // 4 1 3 6

		Dice() {
			nums = new int[7];
		}

		public int[] move(int dir, int num) {

			int[] swapi = null;
			if (dir == 1)
				swapi = e;
			else if (dir == 2)
				swapi = w;
			else if (dir == 3)
				swapi = n;
			else if (dir == 4)
				swapi = s;

			int tmp = nums[swapi[0]];
			for (int i = 0; i < 3; i++) {
				nums[swapi[i]] = nums[swapi[i + 1]];
			}
			nums[swapi[3]] = tmp;

			if (num != 0)
				nums[6] = num;

			return new int[] { nums[1], nums[6] }; // {윗면, 뒷면}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] grid = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Dice dice = new Dice();
		int[] dxs = { 0, 0, 0, -1, 1 }; // 동 서 북 남
		int[] dys = { 0, 1, -1, 0, 0 };

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken());
			int nx = x + dxs[d];
			int ny = y + dys[d];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			int[] ans = dice.move(d, grid[nx][ny]);
			grid[nx][ny] = grid[nx][ny] == 0 ? ans[1] : 0;
			x = nx;
			y = ny;
			sb.append(ans[0]).append("\n");
		}

		System.out.println(sb.toString());
	}
}