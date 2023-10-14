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
		
		int[] dp = new int[K + 1];
		
		for (int i = 0; i < N; i++) {
			int w = items[i][0];
			int v = items[i][1];
			
			for (int j = K; j >= w; j--) {
				if (dp[j] < dp[j - w] + v)
					dp[j] = dp[j - w] + v;
			}
		}
		
		System.out.println(dp[K]);

	}

}