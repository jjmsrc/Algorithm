import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		long ans = 0;
		int n = Integer.parseInt(br.readLine());
		int[][] nums = new int[4][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				nums[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = solve(n, nums);
		
		System.out.println(ans);
		
	}

	private static long solve(int n, int[][] nums) {
		
		long cntZero = 0;
		
		int[] ABNums = new int[n * n];
		int[] CDNums = new int[n * n];
		
		for (int i = 0, idx = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ABNums[idx] = nums[0][i] + nums[1][j];
				CDNums[idx] = nums[2][i] + nums[3][j];
				idx++;
			}
		}
		
		Arrays.sort(ABNums);
		Arrays.sort(CDNums);
		
		cntZero = countZero(n * n, ABNums, CDNums);
		
		return cntZero;
	}

	private static long countZero(int n, int[] aNums, int[] bNums) {
		long cnt = 0;
		
		int l = 0;
		int r = n - 1;
		
		while(l < n && r >= 0) {
			int s = aNums[l] + bNums[r];
			if (s == 0) {
				int leftCnt = 1, rightCnt = 1;
				while(l + 1 < n && aNums[l] == aNums[l + 1]) {
					l++;
					leftCnt++;
				}
				while(r > 0 && bNums[r] == bNums[r - 1]) {
					r--;
					rightCnt++;
				}
				cnt += leftCnt * (long)rightCnt;
				l++;
			} else if (s < 0) {
				l++;
			} else {
				r--;
			}
		}
		
		return cnt;
	}

}