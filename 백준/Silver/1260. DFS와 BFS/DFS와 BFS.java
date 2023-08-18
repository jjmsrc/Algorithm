import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		boolean[][] adjMat = new boolean[N + 1][N + 1];
		boolean[] visited = new boolean[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMat[from][to] = adjMat[to][from] = true;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(V);
		while(!stack.isEmpty()) {
			int from = stack.pop();
			if (visited[from])
				continue;
			sb.append(from).append(" ");
			visited[from] = true;
			for (int to = N; to >= 1; to--) {
				if (adjMat[from][to] && !visited[to]) {
					stack.push(to);
				}
			}
		}
		
		sb.append('\n');
		Arrays.fill(visited, false);
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(V);
		visited[V] = true;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int from = queue.poll();
				sb.append(from).append(" ");
				for (int to = 1; to <= N; to++) {
					if (adjMat[from][to] && !visited[to]) {
						visited[to] = true;
						queue.offer(to);
					}
				}
			}
		}
		System.out.println(sb);

	}

}