
import java.util.*;
import java.io.*;

public class Solution {
	
	final static int MAX = 1_000_000_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int ans = 0;
			int maxSum = 0;
			int evenFlag = -1;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int absSum = Math.abs(x) + Math.abs(y);
				maxSum = Math.max(maxSum, absSum);
				if (evenFlag == -1) {
					evenFlag = maxSum % 2;
				}
				if (absSum % 2 != evenFlag) {
					ans = -1;
				}
			}
			
			if (ans != -1) {
				int sum = 0;
				for (int j = 0; ; j++) {
					sum += j;
					if (maxSum <= sum) {
						if (evenFlag != ((j + 1) / 2) % 2)
							j += j % 2 == 1 ? 2 : 1;
						ans = j;
						break;
					}
				}
			}
			sb.append("#" + t + " ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}
