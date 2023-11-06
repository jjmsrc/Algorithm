import java.util.*;
import java.io.*;

public class Main {
	
	private static final int MAX_DIST = 10_000_000;
	private static int N;
	private static int[][] adjMat;
	private static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adjMat = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = MAX_DIST;
		for (int i = 0; i < N; i++) {
			findMinDist(i, i, 0, 0, new boolean[N]);
		}
		System.out.println(ans);
	}
	
	private static void findMinDist(int start, int curr, int cnt, int sum, boolean[] visited) {
		if (cnt == N) {
			if (curr == start) {
				if (ans > sum)
					ans = sum;
				return;
			}
			else
				return;
		} else if (cnt > 0 && curr == start)
			return;
		int min = MAX_DIST;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && adjMat[curr][i] > 0 && ans > sum + adjMat[curr][i]) {
				visited[i] = true;
				findMinDist(start, i, cnt + 1, sum + adjMat[curr][i], visited);
				visited[i] = false;
			}
		}
	}
}