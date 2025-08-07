import java.io.*;
import java.util.*;

public class Main {

	final static int MAX_WIDTH = 100_000;
	final static int MAX_HEIGHT = 1_000_000_000;
	
	private static class MinSegmentTree {
		int n;
		int[] nums = new int[MAX_WIDTH];
		int[] tree = new int[MAX_WIDTH * 4];
		
		public void init(int n, StringTokenizer st) {
			this.n = n;
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			init(1, 0, n - 1);
		}
		
		private int init(int i, int l, int r) {
			if (l == r) return tree[i] = nums[l];
			
			int m = (l + r) / 2;
			
			return tree[i] = Math.min(init(i * 2, l, m),  init(i * 2 + 1, m + 1, r));
		}
		
		public int getMin(int l, int r) {
			return getMin(1, 0, n - 1, l, r);
		}

		private int getMin(int i, int dl, int dr, int l, int r) {
			if (dr < l || r < dl) return MAX_HEIGHT;
			if (l <= dl && dr <= r) return tree[i];
			
			int dm = (dl + dr) / 2;
			
			return Math.min(getMin(i * 2, dl, dm, l, r),  getMin(i * 2 + 1, dm + 1, dr, l, r));
		}
		
		public long findMaxArea() {
			long ans = 0;
			
			ans = findMaxArea(0, n - 1);
			
			return ans;
		}
		
		public long findMaxArea(int l, int r) {
			
			if (l > r) return 0;
			if (l == r) return nums[l];
			
			long max = 0;
			int m = (l + r) / 2;
		
			max = Math.max(findMaxArea(l, m - 1), findMaxArea(m + 1, r));
			
			int dl = m;
			int dr = m;
			
			while(dl >= l && dr <= r) {
				
				max = Math.max(max, calcArea(dl, dr));
				
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
		
		private long calcArea(int l, int r) {
			return (long)(r - l + 1) * getMin(l, r);
		}
		
	}
	
	private static MinSegmentTree tree = new MinSegmentTree();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n;
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;
			tree.init(n, st);
			sb.append(tree.findMaxArea()).append('\n');
		}
		
		System.out.println(sb.toString());

	}
	

}