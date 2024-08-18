import java.util.*;
import java.io.*;

public class Main {

	private static class Edge {
		int v;
		int cost;
		Edge next;

		Edge(int v, int cost, Edge next) {
			super();
			this.v = v;
			this.cost = cost;
			this.next = next;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int numV = Integer.parseInt(st.nextToken());
		int numE = Integer.parseInt(st.nextToken());
		int answer = 0;

		Edge[] edges = new Edge[numV + 1];

		for (int i = 1; i <= numE; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[u] = new Edge(v, c, edges[u]);
			edges[v] = new Edge(u, c, edges[v]);
		}

		answer = findMSTCost(numV, numE, edges, 1);
		System.out.println(answer);
	}

	private static int findMSTCost(int numV, int numE, Edge[] edges, int start) {
		
		boolean[] visit = new boolean[numV + 1];
		
		int costSum = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		
		pq.offer(new int[] {start, 0});
		while(!pq.isEmpty()) {
			int[] dat = pq.poll();
			int v = dat[0];
			if (visit[v]) continue;
			visit[v] = true;
			costSum += dat[1];
			for (Edge e = edges[v]; e != null; e = e.next) {
				if (visit[e.v]) continue;
				pq.offer(new int[] {e.v, e.cost});
			}
		}
		
		return costSum;
	}

}