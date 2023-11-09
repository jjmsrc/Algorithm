import java.util.*;
import java.io.*;

public class Main {

	private static class Edge {
		int v;
		int w;
		Edge next;

		public Edge(int v, int w, Edge next) {
			this.v = v;
			this.w = w;
			this.next = next;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[u] = new Edge(v, w, edges[u]);
			edges[v] = new Edge(u, w, edges[v]);
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		boolean[] visited = new boolean[N + 1];
		int[] minDist = new int[N + 1];

		for (int i = 0; i < minDist.length; i++) {
			minDist[i] = 100_000_000;
		}

		minDist[1] = 0;
		pq.offer(new int[] { 1, minDist[1] });
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int u = curr[0];
			if (visited[u])
				continue;
			visited[u] = true;
			for (Edge edge = edges[curr[0]]; edge != null; edge = edge.next) {
				if (visited[edge.v])
					continue;
				if (minDist[edge.v] > edge.w) {
					minDist[edge.v] = edge.w;
					pq.offer(new int[] { edge.v, edge.w });
				}
			}
		}
		
		int sum = 0;
		int max = 0;
		for (int i = 1; i < minDist.length; i++ ) {
			sum += minDist[i];
			if (max < minDist[i])
				max = minDist[i];
		}

		System.out.println(sum - max);
	}
}