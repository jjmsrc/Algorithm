import java.util.Scanner;

public class Main {
	static long[] dp;
	static char result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long len=3;
		int idx=1;
		while(len<N) {
			len = (len*2)+idx+3;
			idx++;
		}
		dp = new long[idx+1];
		dp[0] = 3;
		for(int i=1;i<=idx;i++) {
			dp[i] = (dp[i-1]*2)+(i+3);
		}
		dfs(N);
		System.out.println(result);
	}
	private static void dfs(int N) {
//		System.out.println(N);
		int idx=0;
		while(N>dp[idx]) {
			idx++;
		}
		if(idx>0) {
			N-=dp[idx-1];
			if(N<=idx+3) {
				if(N==1) {
					result='m';
					return;
				}else {
					result='o';
					return;
				}
			}else {
				N-=(idx+3);
				dfs(N);
				return;
			}
		}else {
			if(N==1) {
				result='m';
				return;
			}else {
				result='o';
				return;
			}
		}
		
		
	}
}