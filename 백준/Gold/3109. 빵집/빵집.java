import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int R, C;
	static int ans;
	static boolean[][] grid;

	static final int E = 0, W = 1, P = 2; // 빈 공간, 벽, 파이프

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		grid = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == 'x')
					grid[i][j] = true;
			}
		}

		ans = 0;
		
		for (int i = 0; i < R; i++) {
			find(i, 0);
		}

		sb.append(ans);

		System.out.println(sb);

	}
	
	static boolean find(int row, int col) {
		if (col == C - 1) {
			ans++;
			return true;
		}
		
		int nc = col + 1;
		
		for (int i = -1; i <= 1; i++) {
			int nr = row + i;
			if (nr >= 0 && nr < R && !grid[nr][nc]) {
				grid[nr][nc] = true;
				if (find(nr, nc)) 
					return true;
			}
		}
		
		return false;
	}

}