import java.io.*;
import java.util.*;

public class Main {
	
	private static class Graph {
		
		Map<Integer, int[]> minDistMap = new HashMap<>();
		
		private static class Edge {
			int v;
			int c;
			Edge next;
			
			Edge(int v, int c, Edge next) {
				this.v = v;
				this.c = c;
				this.next = next;
			}
			
		}
		
		int n;
		Edge[] edges;
		
		Graph(int n) {
			this.n = n;
			this.edges = new Edge[n];
		}
		
		void add(int u, int v, int c) {
			edges[u] = new Edge(v, c, edges[u]);
			edges[v] = new Edge(u, c, edges[v]);
		}
		
		int findMinCost1ToNVia(int v1, int v2) {
			
			int min = Integer.MAX_VALUE;
			
			int[][] dvs = {
					{1, v1, v2, n - 1},
					{1, v2, v1, n - 1}
			};
			
			for (int i = 0; i < 2; i++) {
				int distSum = 0;
				for (int j = 0; j < 3; j++) {
					int d = findMinCost(dvs[i][j], dvs[i][j + 1]);
					if (d == -1) return -1;
					distSum += d;
				}
				if (min > distSum) min = distSum;
			}
			
			return min;
		}
		
		int findMinCost(int u, int v) {
			
			int[] minDist = minDistMap.get(u);
			if (!Objects.isNull(minDist)) {
				return minDist[v];
			}
			
			minDist = new int[n];
			boolean[] isVisited = new boolean[n];
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
			
			Arrays.fill(minDist, -1);
			minDist[u] = 0;
			pq.offer(new int[] {u, minDist[u]});
			
			while(!pq.isEmpty()) {
				int[] edge = pq.poll();
				int cv = edge[0];
				
				if (isVisited[cv]) continue;
				isVisited[cv] = true;
				
				for (Edge e = edges[cv]; e != null; e = e.next) {
					if (isVisited[e.v]) continue;
					if (minDist[e.v] == -1 || minDist[e.v] > minDist[cv] + e.c) {
						minDist[e.v] = minDist[cv] + e.c;
						pq.offer(new int[] {e.v, minDist[e.v]});
					}
				}
				
			}
			
			minDistMap.put(u, minDist);
			return minDist[v];
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		Graph graph = new Graph(N + 1);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.add(u, v, c);
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int ans = graph.findMinCost1ToNVia(v1, v2);
		
		System.out.println(ans);

	}

}