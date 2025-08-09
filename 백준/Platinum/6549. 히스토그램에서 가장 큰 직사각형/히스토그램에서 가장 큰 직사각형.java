import java.io.*;
import java.util.*;

public class Main {

	final static int MAX_WIDTH = 100_000;
	final static int MAX_HEIGHT = 1_000_000_000;
	
	static int n;
	static int[] nums = new int[MAX_WIDTH];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(findMaxArea(0, n - 1)).append('\n');
		}
		
		System.out.println(sb.toString());

	}
	
	static long findMaxArea(int l, int r) {
		
		if (l > r) return 0;
		if (l == r) return nums[l];
		
		long max = 0;
		int m = (l + r) / 2;
	
		max = Math.max(findMaxArea(l, m - 1), findMaxArea(m + 1, r));
		
		int dl = m;
		int dr = m;
		int dMinH = nums[m];
		
		while(dl >= l && dr <= r) {
			
			dMinH = Math.min(dMinH, Math.min(nums[dl], nums[dr]));
			max = Math.max(max,  (long)(dr - dl + 1) * dMinH);
			
			if (l < dl && dr < r) {
				if (nums[dl - 1] < nums[dr + 1]) dr++;
				else if (nums[dl - 1] > nums[dr + 1]) dl--;
				else {
					dr++;
					dl--;
				}
			} else {
				if (dl == l) dr++;
				else dl--;
			}
			
		}
		
		return max;
	}

}