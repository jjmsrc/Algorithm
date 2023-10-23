import java.io.*;
import java.util.*;

public class Main {

	private static int maxDiam;
	private static Edge[] edges;
	private static boolean[] visited;

	private static class Edge {
		int v;
		int w;
		Edge next;

		Edge(int v, int w, Edge next) {
			super();
			this.v = v;
			this.w = w;
			this.next = next;
		}

		private static int dfs(int curr, int distFrom) {

			int[] maxDs = { 0, 0 };
			visited[curr] = true;

			for (Edge edge = edges[curr]; edge != null; edge = edge.next) {
				if (!visited[edge.v]) {
					int d = dfs(edge.v, (maxDs[0] > distFrom ? maxDs[0] : distFrom) + edge.w) + edge.w;
					if (d > maxDs[0]) {
						maxDs[1] = maxDs[0];
						maxDs[0] = d;
					} else if (d > maxDs[1]) {
						maxDs[1] = d;
					}
				}
			}

			if (maxDiam < maxDs[0] + maxDs[1])
				maxDiam = maxDs[0] + maxDs[1];

			return maxDs[0];
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		edges = new Edge[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w;
			while (v != -1) {
				w = Integer.parseInt(st.nextToken());
				edges[u] = new Edge(v, w, edges[u]);
				v = Integer.parseInt(st.nextToken());
			}
		}

		maxDiam = 0;
		visited = new boolean[N + 1];
		Edge.dfs(1, 0);

		System.out.println(maxDiam);

	}

}