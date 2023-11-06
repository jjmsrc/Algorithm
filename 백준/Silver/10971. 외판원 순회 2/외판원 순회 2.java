import java.util.*;
import java.io.*;

public class Main {
	
	private static final int MAX_DIST = 10_000_000;
	private static int N;
	private static int[][] adjMat;

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
		
		int minDist = MAX_DIST;
		
		for (int i = 0; i < N; i++) {
			minDist = Math.min(minDist, findMinDist(i, i, 0, new boolean[N]));
		}
		
		System.out.println(minDist);
	}
	
	private static int findMinDist(int start, int curr, int cnt, boolean[] visited) {
		if (cnt == N) {
			if (curr == start)
				return 0;
			else
				return MAX_DIST;
		} else if (cnt > 0 && curr == start)
			return MAX_DIST;
		int min = MAX_DIST;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && adjMat[curr][i] > 0) {
				visited[i] = true;
				min = Math.min(min, findMinDist(start, i, cnt + 1, visited) + adjMat[curr][i]);
				visited[i] = false;
			}
		}
		return min;
	}
}