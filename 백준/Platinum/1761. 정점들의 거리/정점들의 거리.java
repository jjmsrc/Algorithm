import java.io.*;
import java.util.*;

public class Main {
	
	private static class Tree {
		
		private static class Edge {
			int v;
			int dist;
			Edge next;
			
			Edge(int v, int d, Edge next) {
				this.v = v;
				this.dist = d;
				this.next = next;
			}
		}
		
		private Edge[] edges;
		private int[] distSum;
		private int[] depth;
		private int[][] parent;
		
		Tree(int n) {
			this.edges = new Edge[n];
			this.distSum = new int[n];
		}
		
		void add(int u, int v, int d) {
			edges[u] = new Edge(v, d, edges[u]);
			edges[v] = new Edge(u, d, edges[v]);
		}
		
		int distBetween(int u, int v) {
			
			
			return distSum[u] + distSum[v] - 2 * distSum[findLCA(u, v)];
		}

		private int findLCA(int u, int v) {
			
			int ui = u;
			int vi = v;
			
			int du = depth[ui];
			int dv = depth[vi];
			
			if (du != dv) {
				if (du < dv) {
					int tmp = ui;
					ui = vi;
					vi = tmp;
					du = depth[ui];
					dv = depth[vi];
				}
				
				// u의 depth를 낮춰서 v와 맞추기
				while(du > dv) {
					int i = 0;
					int ndu = du - 1;
					while(ndu > dv) {
						ndu = du - (1 << ++i);
					}
					i = Math.max(i - 1, 0);
					ui = parent[ui][i];
					du = depth[ui];
				}
				
			}
			
			while(ui != vi) {
				
				du = depth[ui];
				
				int i = 0;
				int ndu = du - 1;
				
				while(ndu > 0 && parent[ui][i] != parent[vi][i]) {
					ndu = du - (1 << ++i);
				}
				
				i = Math.max(i - 1, 0);
				
				ui = parent[ui][i];
				vi = parent[vi][i];
			}
			
			return ui;
		}

		void init() {
			initParent(1);
		}
		
		private void initParent(int v) {
			
			depth = new int[edges.length];
			parent = new int[edges.length][(int)(Math.log(edges.length) / Math.log(2)) + 1];
			parent[1][0] = 1;
			
			boolean[] isVisited = new boolean[edges.length];
			Queue<int[]> queue = new ArrayDeque<>();
			int currDepth = 0;
			
			queue.add(new int[] {v, -1});
			distSum[v] = 0;
			depth[v] = 0;
			isVisited[v] = true;
			
			while(!queue.isEmpty()) {
				for (int i = queue.size(); i > 0; i--) {
					int[] vs = queue.poll();
					int cv = vs[0];
					int pv = vs[1];
					int cd = depth[cv];
					
					parent[cv][0] = pv;
					for (int j = 1; cd - (1 << j) >= 0; j++) {
						parent[cv][j] = parent[parent[cv][j - 1]][j - 1]; // cv's 2^(j) parent = (cv's 2^(j-1) parent)'s 2^(j-1) parent
					}
					
					for (Edge e = edges[cv]; e != null ; e = e.next) {
						if (isVisited[e.v]) continue;
						queue.add(new int[] {e.v, cv});
						distSum[e.v] = distSum[cv] + e.dist;
						depth[e.v] = currDepth + 1;
						isVisited[e.v] = true;
					}
				}
				
				currDepth++;
			}
			
			
			
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
//		int N = 40000;
		
		Tree tree = new Tree(N + 1);
		
		for (int i = 0, cnt = N - 1; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
//			int u = i + 1;
//			int v = i + 2;
//			int d = 1;
			tree.add(u, v, d);
		}
		
		tree.init();
		
		int M = Integer.parseInt(br.readLine());
//		int M = 10000;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
//			int u = 39999;
//			int v = 39998;
			int cnt = tree.distBetween(u, v);
			sb.append(cnt).append('\n');
		}
		
		System.out.println(sb);
		
		
		
	}

}