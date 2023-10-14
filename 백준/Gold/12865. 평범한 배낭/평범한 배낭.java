import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] items = new int[N][2];
		for (int i = 0; i < items.length; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[2][K + 1];
		
		for (int i = 0; i < N; i++) {
			int curr = i & 1;
			int pre = curr ^ 1;
			
			int w = items[i][0];
			int v = items[i][1];
			
			for (int j = 1; j <= K; j++) {
				if (j >= w) {
					dp[curr][j] = Math.max(dp[pre][j], dp[pre][j - w] + v);
				} else {
					dp[curr][j] = dp[pre][j];
				}
			}
		}
		
		System.out.println(dp[(N - 1) % 2][K]);

	}

}