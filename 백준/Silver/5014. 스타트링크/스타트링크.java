import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int[] floors = new int[F + 1];
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(S);
		
		int cnt = 1;
		floors[S] = 1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int curr = queue.poll();
				if (curr == G) {
					System.out.println(cnt - 1);
					return;
				}
				int cu = curr + U;
				int cd = curr - D;
				if (cu <= F && floors[cu] == 0) {
					floors[cu] = cnt;
					queue.offer(cu);
				}
				if (cd > 0 && floors[cd] == 0) {
					floors[cd] = cnt;
					queue.offer(cd);
				}
			}
			++cnt;
		}
		
		System.out.println("use the stairs");

	}

}