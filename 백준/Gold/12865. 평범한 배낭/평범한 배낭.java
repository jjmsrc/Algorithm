import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] items = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N + 1][K + 1];
		
		for (int i = 1; i <= N; i++) {
			int w = items[i][0];
			int v = items[i][1];
			
			for (int j = 1; j <= K; j++) {
				for (int ip = 0; ip < i; ip++) {
					dp[i][j] = Math.max(dp[i][j], dp[ip][j]);
					if (j >= w) {
						dp[i][j] = Math.max(dp[i][j], dp[ip][j - w] + v);
					}
				}
			}
		}
		
		System.out.println(dp[N][K]);

	}

}