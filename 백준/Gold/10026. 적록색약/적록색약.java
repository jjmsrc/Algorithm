import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N, M;
	static char[][] grid, gridSY;
	static int ans, ansSY;
	static int[] dxs = {1, -1, 0, 0};
	static int[] dys = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		grid = new char[N][];
		gridSY = new char[N][];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			grid[i] = line.toCharArray();
			gridSY[i] = line.toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] != '-') {
					ans++;
					dfs(i, j, grid[i][j]);
				}
				if (gridSY[i][j] != '-') {
					ansSY++;
					dfsSY(i, j, gridSY[i][j]);
				}
			}
		}

		System.out.println(ans + " " + ansSY);

	}

	static void dfs(int x, int y, int color) {
		grid[x][y] = '-';

		for (int i = 0; i < 4; i++) {
			int nx = x + dxs[i];
			int ny = y + dys[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && grid[nx][ny] == color) {
				dfs(nx, ny, color);
			}
		}
	}

	static void dfsSY(int x, int y, int color) {
		gridSY[x][y] = '-';

		if (color == 'R')
			color = 'G';
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dxs[i];
			int ny = y + dys[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (gridSY[nx][ny] == 'R')
					gridSY[nx][ny] = 'G';
				if (gridSY[nx][ny] == color)
					dfsSY(nx, ny, color);
			}
		}
	}

}