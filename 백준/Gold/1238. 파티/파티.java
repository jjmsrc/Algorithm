import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
		
		int N, M, X;
		int adjMat[][];
		final int MAX_TIME = 1_000_000;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		adjMat = new int[N + 1][N + 1];
		
		for (int i = 1; i < adjMat.length; i++) {
			for (int j = i + 1; j < adjMat.length; j++) {
				adjMat[i][j] = adjMat[j][i] = MAX_TIME;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adjMat[from][to] = t;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {
					if (j == i || j == k) continue;
					if (adjMat[i][j] > adjMat[i][k] + adjMat[k][j]) {
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
					}
				}
			}
		}
		
		int maxTime = 0;
		for (int i = 1; i <= N; i++) {
			int t = adjMat[i][X] + adjMat[X][i];
			if (maxTime < t) {
				maxTime = t;
			}
		}
		
		System.out.println(maxTime);
		
	}

}