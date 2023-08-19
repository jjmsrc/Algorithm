import java.io.*;
import java.util.*;

public class Main {

	static int R, C;
	static int ans;
	static int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static char[][] board;
	static boolean[] visited;

	static void dfs(int r, int c, int cnt) {

		ans = Math.max(ans, cnt);

		for (int[] is : deltas) {
			int nx = r + is[0];
			int ny = c + is[1];
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[board[nx][ny] - 'A']) {
				visited[board[nx][ny] - 'A'] = true;
				dfs(nx, ny, cnt + 1);
				visited[board[nx][ny] - 'A'] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][];
		visited = new boolean[26];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			board[i] = line.toCharArray();
		}

		ans = 0;
		visited[board[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		
		sb.append(ans);

		System.out.println(sb);

	}

}