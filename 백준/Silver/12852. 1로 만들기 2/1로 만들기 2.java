import java.util.*;

public class Main {

	private final static int MAX_CNT = 1_000_000;
	private static int[] memCnt, memNode;
	private static ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
	private static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int x = sc.nextInt();

		memCnt = new int[x + 1];
		memNode = new int[x + 1];

		init();
		
		findMinTries(x);
		
		int minCnt = memCnt[x];

		System.out.println(minCnt);
		System.out.println(printMinCntNumbers(x));

	}
	
	private static void init() {
		for (int i = 0; i < memCnt.length; i++) {
			memCnt[i] = MAX_CNT;
		}
	}
	
	private static String printMinCntNumbers(int x) {
		StringBuilder sb = new StringBuilder();
		for (int n = x; n >= 1; n = memNode[n]) {
			sb.append(n).append(" ");
		}
		return sb.toString();
	}

	private static void findMinTries(int x) {
		
		final int MAX_CNT = 1_000_000;
		Queue<Integer> queue = new ArrayDeque<>();
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		StringBuilder sb;
		
		memCnt[1] = 0;
		for (int i = 1; i < x; i++) {
			int cnt = memCnt[i] + 1;
			if (i * 3 <= x && memCnt[i * 3] > cnt) {
				memCnt[i * 3] = cnt;
				memNode[i * 3] = i;
			}
			if (i * 2 <= x && memCnt[i * 2] > cnt) {
				memCnt[i * 2] = cnt;
				memNode[i * 2] = i;
			}
			if (memCnt[i + 1] > cnt) {
				memCnt[i + 1] = cnt;
				memNode[i + 1] = i;
			}
		}
	}

}