import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if (N < 10) {
			System.out.println(0);
			return;
		}
		
		int[][][] dp = new int[10][N + 1][1 << 10];
		
		for (int i = 1; i < 10; i++) {
			dp[i][1][(1 << i)] = 1;
		}
		
		for (int j = 2; j <= N; j++) {
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < (1 << 10); k++) {
					if (i > 0) {
						dp[i][j][k | (1 << i)] += dp[i - 1][j - 1][k];
					}
					if (i < 9) {
						dp[i][j][k | (1 << i)] += dp[i + 1][j - 1][k];
					}
					dp[i][j][k | (1 << i)] %= 1_000_000_000;
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < 10; i++) {
			ans = (ans + dp[i][N][(1 << 10) - 1]) % 1_000_000_000;
		}
		
		System.out.println(ans);
		
	}

}