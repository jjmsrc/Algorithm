import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n, k;
			int[][] orders;
			int[] delay;
			int w;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			orders = new int[n + 1][n + 1];
			delay = new int[n + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				delay[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				orders[x][y] = 1;
				++orders[0][y];
			}
			
			w = Integer.parseInt(br.readLine());
			
			int ans = solve(n, orders, delay, w);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

	private static int solve(int n, int[][] orders, int[] delay, int w) {
		
		int[] totalDelay = new int[n + 1];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for (int i = 1; i <= n; i++) {
			if (orders[0][i] == 0) {
				queue.offer(i);
				totalDelay[i] = delay[i];
			}
		}
		
		while(!queue.isEmpty()) {
			int i = queue.poll();
			for (int j = 1; j <= n; j++) {
				if (orders[i][j] == 1) {
					if (totalDelay[j] < totalDelay[i]) {
						totalDelay[j] = totalDelay[i];
					}
					if (--orders[0][j] == 0) {
						totalDelay[j] += delay[j];
						queue.offer(j);
					}
				}
			}
		}
		
		return totalDelay[w];
	}

}