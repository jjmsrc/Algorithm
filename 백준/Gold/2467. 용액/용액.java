import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);

		int l = 0, r = n - 1;
		int sum = 2_000_000_000, numA = nums[l], numB = nums[r];
		
		while(l < r) {
			int s = nums[l] + nums[r];
			if (sum > Math.abs(s)) {
				sum = Math.abs(s);
				numA = nums[l];
				numB = nums[r];
			}
			if (s < 0) {
				l++;
			} else {
				r--;
			}
		}
		
		System.out.println(numA + " " + numB);
		
	}
}