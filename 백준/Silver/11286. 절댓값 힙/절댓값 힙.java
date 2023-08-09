import java.util.*;
import java.io.*;


public class Main {
	
	private static int comp(int a, int b) {
		return Math.abs(a) - Math.abs(b);
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> comp(a, b) == 0 ? a - b : comp(a, b));

		// 선언 및 초기화
		final int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(br.readLine());
			if (p == 0) {
				if (pq.isEmpty())
					sb.append(0).append("\n");
				else
					sb.append(pq.poll()).append("\n");
			} else {
				pq.add(p);
			}
		}

		System.out.println(sb);
	}

}