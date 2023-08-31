import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();
		for(int i=0;i<n;i++) {
			int num = Integer.parseInt(br.readLine().trim());
			set.add(num);
		}
		int dp[] = new int[k+1];
		for(int i=0;i<k;i++) {
			for(int j : set) {
				if(i+j<=k) {
					if(dp[i+j]!=0&&dp[i]!=0) {
						dp[i+j] = Math.min(dp[i]+1, dp[i+j]);
					}else if(i==0||dp[i]!=0){
						dp[i+j] = dp[i]+1;
					}
					
				}
			}
//			System.out.println(i+","+Arrays.toString(dp));
		}
		
		if(dp[k]!=0) {
			System.out.println(dp[k]);
		}else {
			System.out.println(-1);
		}
		
	}
}