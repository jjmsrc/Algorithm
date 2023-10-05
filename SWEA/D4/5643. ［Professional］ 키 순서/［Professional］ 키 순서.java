import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			
			boolean[][] adjMat = new boolean[N + 1][N + 1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int sm = Integer.parseInt(st.nextToken());
				int lg = Integer.parseInt(st.nextToken());
				adjMat[sm][lg] = true;
			}
			
			int ans = solve(N, adjMat);
			
			sb.append("#" + t + " " + ans + "\n");
		}
		
		System.out.println(sb);
		
	}

	private static int solve(int N, boolean[][] adjMat) {
		
		int cntOrdered = 0;
		
		int[] cntSmall = new int[N + 1];
		int[] cntTall = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			cntSmall[i] = countSmall(i,  N, adjMat, new boolean[N + 1]);
			cntTall[i] = countTall(i,  N, adjMat, new boolean[N + 1]);
		}
		
		for (int i = 1; i <= N; i++) {
			if (cntSmall[i] + cntTall[i] == N - 1)
				cntOrdered++;
		}
		
		return cntOrdered;
	}

	private static int countSmall(int idx, int N, boolean[][] adjMat, boolean[] visited) {
		int cnt = 0;
		visited[idx] = true;
		
		for (int i = 1; i <= N; i++) {
			if (adjMat[i][idx] && !visited[i]) {
				cnt += countSmall(i, N, adjMat, visited) + 1;
			}
		}
		
		return cnt;
	}
	
	private static int countTall(int idx, int N, boolean[][] adjMat, boolean[] visited) {
		int cnt = 0;
		visited[idx] = true;
		
		for (int i = 1; i <= N; i++) {
			if (adjMat[idx][i] && !visited[i]) {
				cnt += countTall(i, N, adjMat, visited) + 1;
			}
		}
		
		return cnt;
	}
}