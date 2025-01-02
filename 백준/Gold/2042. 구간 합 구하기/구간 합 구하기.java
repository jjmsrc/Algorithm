import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static class SegmentTree {
		
		private int n;
		private long[] nums;
		private long[] tree;
		
		void init(int n, long[] nums) {
			this.n = n;
			this.nums = nums;
			// 데이터의 개수가 N일때, N보다 크고 가장 가까운 2의 제곱수의 2배 값이 필요함. 
			// 가장 가깝고 큰 2의 제곱수를 k라 하면, k/2 < n < k 이므로, n의 최솟값인 k/2가 2*k가 되기 위해서 4를 곱해야 함.
			tree = new long[n * 4]; 
			init(1, n, 1);
			this.nums = null;
		}
		
		private long init(int left, int right, int ti) {
			if (left == right) return tree[ti] = nums[left];
			int mid = (left + right) / 2;
			return tree[ti] = init(left, mid, ti * 2) + init(mid + 1, right, ti * 2 + 1);
		}
		
		long sum(int start, int end) {
			return sum(1, n, 1, start, end);
		}
		
		private long sum(int left, int right, int ti, int start, int end) {
			if (left >= start && right <= end) return tree[ti];
			if (left > end || right < start) return 0;
			int mid = (left + right) / 2;
			return sum(left, mid, ti * 2, start, end) + sum(mid + 1, right, ti * 2 + 1, start, end);
		}
		
		void update(int idx, long num) {
			update(1, n, 1, idx, num);
		}
		
		private long update(int left, int right, int ti, int idx, long num) {
			if (left == right) {
				long diff = tree[ti] - num;
				tree[ti] = num;
				return diff;
			}
			int mid = (left + right) / 2;
			long diff;
			if (idx <= mid) diff = update(left, mid, ti * 2, idx, num);
			else diff = update(mid + 1, right, ti * 2 + 1, idx, num);
			tree[ti] -= diff;
			return diff;
		}
		
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
		
		long[] nums = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		// 세그먼트 트리 초기화
		SegmentTree segmentTree = new SegmentTree();
		segmentTree.init(N, nums);
		
		// 명령어 입력
		for (int i = 0, cnt = M + K; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken()); // 명령어
			int arg1 = Integer.parseInt(st.nextToken());
			long arg2 = Long.parseLong(st.nextToken());
			if (cmd == 1) {
				segmentTree.update(arg1, arg2);
			} else if (cmd == 2) {
				sb.append(segmentTree.sum(arg1, (int)arg2)).append('\n');
			}
		}
		
		System.out.println(sb);

	}

}