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
		
//		for (int i = 0; i < adjMat.length; i++) {
//			for (int j = 0; j < adjMat.length; j++) {
//				adjMat[i][j] = MAX_TIME;
//			}
//		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adjMat[from][to] = t;
		}
		
		int[] minDistToX = new int[N + 1];
		int[] minDistFromX = new int[N + 1];
		boolean[] visitedToX = new boolean[N + 1];
		boolean[] visitedFromX = new boolean[N + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
		
		for (int i = 0; i < minDistToX.length; i++) {
			minDistToX[i] = MAX_TIME;
			minDistFromX[i] = MAX_TIME;
		}
		minDistToX[X] = 0;
		minDistFromX[X] = 0;
		
		// From X
		
		pq.offer(new int[] {0, X});
		while(!pq.isEmpty()) {
			int[] p = pq.poll();
			int minT = p[0];
			int minV = p[1];
			
			if (visitedFromX[minV])
				continue;
			visitedFromX[minV] = true;
			
			for (int i = 1; i <= N; i++) {
				if (adjMat[minV][i] > 0 && !visitedFromX[i] && minDistFromX[i] > minT + adjMat[minV][i]) {
					minDistFromX[i] = minT + adjMat[minV][i];
					pq.offer(new int[] {minDistFromX[i], i});
				}
			}
		}
		
		// To X
		
		pq.offer(new int[] {0, X});
		while(!pq.isEmpty()) {
			int[] p = pq.poll();
			int minT = p[0];
			int minV = p[1];
			
			if (visitedToX[minV])
				continue;
			visitedToX[minV] = true;
			
			for (int i = 1; i <= N; i++) {
				if (adjMat[i][minV] > 0 && !visitedToX[i] && minDistToX[i] > minT + adjMat[i][minV]) {
					minDistToX[i] = minT + adjMat[i][minV];
					pq.offer(new int[] {minDistToX[i], i});
				}
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			if (ans < minDistFromX[i] + minDistToX[i])
				ans = minDistFromX[i] + minDistToX[i];
		}
		
		System.out.println(ans);
		
	}

}