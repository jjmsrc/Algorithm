import java.io.*;
import java.util.*;

public class Main {

    private static class Edge {
        int v;
        Edge next;

        Edge(int v, Edge next) {
            super();
            this.v = v;
            this.next = next;
        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            Edge[] edges = new Edge[V + 1];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges[u] = new Edge(v, edges[u]);
                edges[v] = new Edge(u, edges[v]);
            }

            boolean ans = testBG(V, edges);
            sb.append(ans ? "YES" : "NO").append("\n");
        }

        System.out.println(sb.toString());

    }

    private static boolean testBG(int numV, Edge[] edges) {

        int[] stat = new int[numV + 1]; // 0: 미결정, 1: 좌측, 2: 우측
        
        Queue<Integer> queue = new ArrayDeque<Integer>();
        
        for (int i = 1; i <= numV; i++) {
			Edge edge = edges[i];
			if (edge == null)
				continue;
			queue.offer(i);
			stat[i] = 1;
			while(!queue.isEmpty()) {
				int u = queue.poll();
				for (Edge e = edges[u]; e != null; e = e.next) {
					if (stat[e.v] == 0) {
						stat[e.v] = stat[u] ^ 0b11;
						queue.offer(e.v);
					} else {
						if (stat[e.v] == stat[u]) {
							return false;
						}
					}
				}
				edges[u] = null;
			}
		}

        return true;
    }

}