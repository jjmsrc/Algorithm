import java.io.*;
import java.util.*;


public class Main {

	static int N, minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
	static int[] cntOps, numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		cntOps = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cntOps.length; i++) {
			cntOps[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(1, numbers[0]);

		sb.append(maxVal).append('\n');
		sb.append(minVal).append('\n');

		System.out.println(sb.toString());

	}

	private static void perm(int cnt, int result) {
		if (cnt == N) {
			maxVal = Math.max(maxVal, result);
			minVal = Math.min(minVal, result);
		} else {
			for (int i = 0; i < 4; i++) {
				if (cntOps[i] > 0) {
					cntOps[i]--;
					perm(cnt + 1, calc(result, numbers[cnt], i));
					cntOps[i]++;
				}
			}
		}
	}

	private static int calc(int a, int b, int op) {
		int ans = 0;
		switch (op) {
		case 0:
			ans = a + b;
			break;
		case 1:
			ans = a - b;
			break;
		case 2:
			ans = a * b;
			break;
		case 3:
			ans = a / b;
			break;
		}
		return ans;
	}

}