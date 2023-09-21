import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] matNums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		matNums[0] = Integer.parseInt(st.nextToken());
		matNums[1] = Integer.parseInt(st.nextToken());
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			matNums[i] = Integer.parseInt(st.nextToken());
		}

		int[][] memCnt = new int[N + 1][N + 1];
		int ans = calcMinMul(0, N, matNums, memCnt);
		System.out.println(ans);
	}

	static int calcMinMul(int l, int r, int[] nums, int[][] mem) {

		if (mem[l][r] > 0)
			return mem[l][r];

		if (l + 2 == r) {
			return mem[l][r] = nums[l] * nums[l + 1] * nums[r];
		}

		if (l + 1 >= r) {
			return 0;
		}

		int min = Integer.MAX_VALUE;

		for (int mid = l + 1; mid < r; mid++) {
			int midVal = nums[l] * nums[mid] * nums[r];
			int leftVal = calcMinMul(l, mid, nums, mem);
			int rightVal = calcMinMul(mid, r, nums, mem);
			min = Math.min(min, leftVal + midVal + rightVal);
		}

		return mem[l][r] = min;
	}

}