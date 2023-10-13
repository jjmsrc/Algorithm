import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int maxNumClients = 0;
		int[][] promo = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			promo[i][0] = Integer.parseInt(st.nextToken());
			promo[i][1] = Integer.parseInt(st.nextToken());
			if (maxNumClients < promo[i][1])
				maxNumClients = promo[i][1];
		}
		
		int limitC = 1100;
		int[] dp = new int[limitC + 1];
		int ans = Integer.MAX_VALUE >> 2;
		
		for (int ci = 1; ci <= limitC; ci++) {
			dp[ci] = Integer.MAX_VALUE >> 2;
			for (int[] p : promo) {
				int cost = p[0];
				int nClients = p[1];
				if (ci - nClients >= 0) {
					dp[ci] = Math.min(dp[ci], dp[ci - nClients] + cost);
				}
			}
			if (ci >= C && ans > dp[ci])
				ans = dp[ci];
		}
		
		System.out.println(ans);

	}

}