import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static class SegmentTree {
		int n;
		int[] nums;
		int[] tree;
		
		public SegmentTree(int[] nums) {
			this.n = nums.length;
			this.tree = new int[n * 4];
			this.nums = nums;
			init(1, 0, n - 1);
			this.nums = null;
		}
		
		private int init(int i, int l, int r) {
			if (l == r) return tree[i] = nums[l];
			
			int m = (l + r) / 2;
			
			return tree[i] = (int)((long)init(i * 2, l, m) * init(i * 2 + 1, m + 1, r) % 1_000_000_007);
		}

		public void update(int ni, int num) {
			update(1, 0, n - 1, ni - 1, num);
		}
		
		private int update(int i, int sl, int sr, int ni, int num) {
			if (ni < sl || sr < ni) return tree[i];
			
			if (sl == sr) return tree[i] = num;
			
			int m = (sl + sr) / 2;
			
			return tree[i] = (int)((long)update(i * 2, sl, m, ni, num) * update(i * 2 + 1, m + 1, sr, ni, num) % 1_000_000_007);
		}

		public int rangeMul(int l, int r) {
			return rangeMul(1, 0, n - 1, l - 1, r - 1);
		}
		
		private int rangeMul(int i, int sl, int sr, int tl, int tr) {
			
			if (sr < tl || tr < sl) return 1;
			
			if (tl <= sl && sr <= tr) return tree[i];
			
			int m = (sl + sr) / 2;
			
			return (int)((long)rangeMul(i * 2, sl, m, tl, tr) * rangeMul(i * 2 + 1, m + 1, sr, tl, tr) % 1_000_000_007);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[N];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		SegmentTree segmentTree = new SegmentTree(nums);
		
		for (int i = 0, nCmds = M + K; i < nCmds; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				segmentTree.update(a, b);
			} else if (cmd == 2) {
				sb.append(segmentTree.rangeMul(a, b)).append('\n');
			}
		}
		
		System.out.println(sb.toString());

	}

}