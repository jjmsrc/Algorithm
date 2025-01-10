import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static final int MAX = 1_000_000_000;
	private static final int MIN = 1;
	
	private static int N;
	
	private static int[] nums;
	private static int[] maxTree;
	private static int[] minTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		nums = new int[N + 1];
		maxTree = new int[N * 4];
		minTree = new int[N * 4];
		
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		initMaxTree(1, 1, N);
		initMinTree(1, 1, N);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb	.append(getMin(1, 1, N, start, end)).append(' ')
				.append(getMax(1, 1, N, start, end)).append('\n');
		}
		
		System.out.println(sb);
	}
	
	private static int initMaxTree(int i, int left, int right) {
		if (left == right) return maxTree[i] = nums[left];
		int mid = (left + right) / 2;
		return maxTree[i] = Math.max(initMaxTree(i * 2, left, mid), initMaxTree(i * 2 + 1, mid + 1, right));
	}
	
	private static int initMinTree(int i, int left, int right) {
		if (left == right) return minTree[i] = nums[left];
		int mid = (left + right) / 2;
		return minTree[i] = Math.min(initMinTree(i * 2, left, mid), initMinTree(i * 2 + 1, mid + 1, right));
	}
	
	private static int getMax(int i, int left, int right, int start, int end) {
		if (right < start || end < left) return MIN;
		if (start <= left && right <= end) return maxTree[i];
		int mid = (left + right) / 2;
		return Math.max(getMax(i * 2, left, mid, start, end), getMax(i * 2 + 1, mid + 1, right, start, end));
	}
	
	private static int getMin(int i, int left, int right, int start, int end) {
		if (right < start || end < left) return MAX;
		if (start <= left && right <= end) return minTree[i];
		int mid = (left + right) / 2;
		return Math.min(getMin(i * 2, left, mid, start, end), getMin(i * 2 + 1, mid + 1, right, start, end));
	}

}