import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 가수의 수
		int m = Integer.parseInt(st.nextToken()); // PD의 수
		
		int[] cntIn = new int[n + 1];
		int[][] edges = new int[n + 1][n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int pre = Integer.parseInt(st.nextToken());
			for (int j = 1; j < len; j++) {
				int curr = Integer.parseInt(st.nextToken());
				if(edges[pre][curr] != 1)
					cntIn[curr]++;
				edges[pre][curr] = 1;
				pre = curr;
			}
		}
		
		int[] ans = solve(n, edges, cntIn);
		
		if (ans == null) {
			System.out.println(0);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ans.length; i++) {
				sb.append(ans[i]).append("\n");
			}
			System.out.println(sb);
			
			System.out.println();
		}
		
	}

	private static int[] solve(int n, int[][] edges, int[] cntIn) {
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for (int i = 1; i <= n; i++) {
			if (cntIn[i] == 0) {
				queue.offer(i);
			}
		}
		
		int[] singers = new int[n];

		int i = 0;
		for (; i < n; i++) {
			if (queue.size() == 0) {
				break;
			}
			int s = queue.poll();
			
			singers[i] = s;
			for (int j = 1; j <= n; j++) {
				if (edges[s][j] == 1 && --cntIn[j] == 0) {
					queue.offer(j);
				}
			}
		}
		
		if (i < n) return null;
		
		return singers;
	}

}