import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[][] adjMat = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjMat[u][v] = 1;
		}

		for (int i = 1; i <= N; i++) {
			adjMat[i][0] = -1;
		}

		for (int i = 1; i <= N; i++) {
			gtdfs(adjMat, i);
		}
		
		for (int i = 1; i <= N; i++) {
			int cntlt = 0;
			for (int j = 1; j <= N; j++) {
				if (adjMat[j][i] == 1)
					cntlt++;
			}
			sb.append(N - 1 - adjMat[i][0] - cntlt).append("\n");
		}

		System.out.println(sb);

	}

	private static void gtdfs(int[][] adjMat, int curr) {
		if (adjMat[curr][0] != -1)
			return;

		for (int i = 1; i < adjMat.length; i++) {
			if (adjMat[curr][i] == 0)
				continue;
			if (adjMat[i][0] == -1)
				gtdfs(adjMat, i);
			for (int j = 1; j < adjMat.length; j++) {
				if (adjMat[i][j] == 1)
					adjMat[curr][j] = 1;
			}
		}

		int cnt = 0;
		for (int i = 1; i < adjMat.length; i++) {
			if (adjMat[curr][i] == 1)
				cnt++;
		}
		adjMat[curr][0] = cnt;
	}

}