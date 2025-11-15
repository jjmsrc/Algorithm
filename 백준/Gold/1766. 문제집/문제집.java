import java.io.*;
import java.util.*;

public class Main {
	
	private static class Edge {
		int v;
		Edge next;
		
		Edge(int v, Edge next) {
			this.v = v;
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
		int[] cntIn = new int[N + 1];
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[u] = new Edge(v, edges[u]);
			cntIn[v]++;
		}
		
		for (int i = 1; i <= N; i++) {
			if (cntIn[i] == 0) pq.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {
			int v = pq.poll();
			
			sb.append(v).append(' ');
			
			for (Edge e = edges[v]; e != null; e = e.next) {
				if (--cntIn[e.v] == 0) {
					pq.add(e.v);
				}
			}
		}
		
		System.out.println(sb);
		
	}

}