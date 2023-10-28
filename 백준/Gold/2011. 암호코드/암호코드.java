import java.io.*;
import java.util.*;

public class Main {

	private static char[] code;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		code = br.readLine().toCharArray();
		int N = code.length;
		int[] dp = new int[N];

		if (N == 1) {
			System.out.println(code[0] != '0' ? 1 : 0);
			return;
		}

		// 초항 검사
		if (code[N - 1] == '0' && code[N - 2] != '1' && code[N - 2] != '2') {
			System.out.println(0);
			return;
		}
		
		// 초항 초기화
		if (code[N - 1] != '0')
			dp[N - 1] = 1;
		if (code[N - 2] != '0')
			dp[N - 2] = dp[N - 1] + (isL2Alpha(N - 2) ? 1 : 0);

		for (int i = N - 3; i >= 0; i--) {
			if (code[i] == '0') 
				continue;
			if (isL2Alpha(i))
				dp[i] = (dp[i + 1] + dp[i + 2]) % 1000000;
			else
				dp[i] = dp[i + 1];
		}

		System.out.println(dp[0]);

	}

	private static boolean isCode(char c) {
		return c >= '1' && c <= '9';
	}

	private static boolean isL2Alpha(int i) {
		return code[i] == '1' || (code[i] == '2' && code[i + 1] <= '6');
	}

}