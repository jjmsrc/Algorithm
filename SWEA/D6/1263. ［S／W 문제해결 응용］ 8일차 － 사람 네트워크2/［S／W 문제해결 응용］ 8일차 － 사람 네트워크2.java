import java.util.*;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[][] adjMat = new int[1000][1000];
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int d = Integer.parseInt(st.nextToken());
					if (d == 1)
						adjMat[i][j] = 1;
					else if (i == j) 
						adjMat[i][j] = 0;
					else 
						adjMat[i][j] = 1000;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k)
						continue;
					for (int j = 0; j < N; j++) {
						if (j == i || j == k)
							continue;
						if (adjMat[i][j] > adjMat[i][k] + adjMat[k][j])
							adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
					}
				}
			}
			
			int minSum = 1000 * 1000;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				sum = 0;
				for (int j = 0; j < N; j++) {
					sum += adjMat[i][j];
				}
				minSum = Math.min(minSum, sum);
			}
			
			sb.append("#" + t + " ").append(minSum).append("\n");
		}
		
		System.out.println(sb);
	}
}