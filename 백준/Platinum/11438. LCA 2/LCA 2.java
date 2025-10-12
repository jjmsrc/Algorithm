import java.io.*;
import java.util.*;

public class Main {
	
	private static class LCATree {
		private static class Edge {
			int v;
			Edge next;
			
			Edge(int v, Edge next) {
				this.v = v;
				this.next = next;
			}
		}
		
		int n;
		Edge[] edges;
		int[] depth;
		int[][] parents; // {{2^i 번째 부모, ...}, ...}
		
		LCATree(int n) {
			this.n = n;
			this.edges = new Edge[n + 1];
		}
		
		void add(int u, int v) {
			edges[u] = new Edge(v, edges[u]);
			edges[v] = new Edge(u, edges[v]);
		}
		
		void initLCA() {
			
			int root = 1;
			int log2n = (int)(Math.log(n) / Math.log(2));
			
			depth = new int[n + 1];
			parents = new int[n + 1][log2n + 1];
			
			boolean[] isVisited = new boolean[n + 1];
			Queue<Integer> queue = new ArrayDeque<>();
			
			parents[1][0] = 1;
			isVisited[root] = true;
			queue.offer(root);
			for (int di = 0; !queue.isEmpty(); di++) { // depth 별 탐색
				for (int vi = 0, size = queue.size(); vi < size; vi++) { // 현재 depth 내 정점 탐색
					
					// 현재 정점 할당
					int v = queue.poll();
					
					// 현재 정점과 연결된 edge 탐색
					for (Edge e = edges[v]; e != null; e = e.next) {
						if (isVisited[e.v]) continue;
						isVisited[e.v] = true;
						queue.offer(e.v);
						
						parents[e.v][0] = v; // 인접 부모 초기화
						
						// depth 초기화
						depth[e.v] = di;
						
						// 부모 정보 초기화, 2^i 번째 부모 노드 정보 저장
						// e의 (2^i 번째 부모) = (e의 2^(i-1) 번째 부모)의 2^(i-1) 번째 부모
						for (int ai = 1; di - (1 << ai) >= 0; ai++) {
							parents[e.v][ai] = parents[parents[e.v][ai - 1]][ai - 1];
						}
					}
				}
			}
			
			
			
		}
		
		int lca(int u, int v) {
			
			int ui = u;
			int vi = v;
			int du = depth[ui];
			int dv = depth[vi];
			
			if (du > dv) {
				ui = v;
				vi = u;
				du = depth[ui];
				dv = depth[vi];
			}
			
			while(du < dv) {
				int i = 1;
				int ndv = 0;
				
				for (	ndv = dv - (1 << i); 
						ndv >= 0 && du <= ndv; 
						ndv = dv - (1 << ++i));
				
				vi = parents[vi][i - 1];
				dv = depth[vi];
			}
			
			while(ui != vi) {
				int i = 1;
				int ndv = 0;
				
				for (	ndv = dv - (1 << i); 
						ndv >= 0 && parents[ui][i] != parents[vi][i]; 
						ndv = dv - (1 << ++i));
				
				ui = parents[ui][i - 1];
				vi = parents[vi][i - 1];
				du = depth[ui];
				dv = depth[vi];
			}
			
			return ui;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		LCATree tree = new LCATree(N);
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			tree.add(u, v);
		}
		
		tree.initLCA();
		
		int M = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(tree.lca(u, v)).append('\n');
		}
		
		System.out.println(sb);

	}

}