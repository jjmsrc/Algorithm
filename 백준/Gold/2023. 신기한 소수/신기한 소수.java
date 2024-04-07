import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int MAX = 100_000_000;
		final int MAX_SQ = (int)Math.sqrt(MAX);
		
		boolean[] mem = new boolean[MAX_SQ];
		boolean[] visited = new boolean[MAX_SQ];
		
		for (int i = 2; i < MAX_SQ; i++) {
			if (visited[i])
				continue;
			mem[i] = true;
			for (int j = i * 2; j < MAX_SQ; j += i) {
				visited[j] = true;
			}
		}
		
		
		// 100_000_000
		final int N = Integer.parseInt(br.readLine());

		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(2);
		queue.offer(3);
		queue.offer(5);
		queue.offer(7);
		
		int[] nums = {1, 3, 5, 7, 9};
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0, size = queue.size(); j < size; j++) {
				int a = queue.poll();
				for (int n : nums) {
					int np = a * 10 + n;
					if (isPrime(np, mem))
						queue.offer(np);
				}
			}
		}
		
		for (Integer n : queue) {
			sb.append(n).append('\n');
		}
		
		System.out.println(sb.toString());
		
	}

	private static boolean isPrime(int np, boolean[] mem) {
		for (int i = 2; i <= Math.sqrt(np); i++) {
			if (mem[i] && (np % i == 0))
				return false;
		}
		return true;
	}

}