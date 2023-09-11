import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine()); // 3~500
			int[] nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nums[j] = Integer.parseInt(st.nextToken()); // 1~10000
			}
			sb.append(calcMinCost(nums)).append("\n");
		}

		System.out.println(sb.toString());

	}

	private static int calcMinCost(int[] programs) {

		int n = programs.length;
		int[][][] mem = new int[n][n][2]; // 0: cost, 1: total cost

		int[] ans = calcMinCost(programs, mem, 0, n - 1);
		return ans[1];
	}

	private static int[] calcMinCost(int[] p, int[][][] mem, int start, int end) {

		if (start > end)
			return new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };

		if (mem[start][end][0] > 0)
			return mem[start][end];
		if (start == end) {
			mem[start][end][0] = p[start];
			mem[start][end][1] = 0;
			return mem[start][end];
		}
		if (start + 1 == end) {
			mem[start][end][0] = p[start] + p[end];
			mem[start][end][1] = p[start] + p[end];
			return mem[start][end];
		}

		int cost = 0;
		int total = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {
			int[] a = calcMinCost(p, mem, start, i);
			int[] b = calcMinCost(p, mem, i + 1, end);
			int c = a[0] + b[0];
			int t = a[1] + b[1] + c;
			if (total > t) {
				cost = c;
				total = t;
			}
		}

		mem[start][end][0] = cost;
		mem[start][end][1] = total;

		return mem[start][end];
	}

}