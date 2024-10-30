import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		int[] minIdx = {0, 1, 2};
		long zeroSum = 1_000_000_000L * 3;
		int left = 0;
		int right = N - 1;
		int mid = left + 1;
		while(left + 1 < right) {
			mid = left + 1;
			while(mid < right) {
				long sum = (long)nums[left] + nums[mid] + nums[right];
				if (zeroSum > Math.abs(sum)) {
					zeroSum = Math.abs(sum);
					minIdx[0] = left;
					minIdx[1] = mid;
					minIdx[2] = right;
				}
				if (sum < 0) {
					mid++;
				} else if (sum > 0) {
					right--;
				} else {
					left = N;
					break;
				}
			}
			left++;
			right = N - 1;
		}
		
		System.out.println(nums[minIdx[0]] + " " + nums[minIdx[1]] + " " + nums[minIdx[2]]);

	}

}