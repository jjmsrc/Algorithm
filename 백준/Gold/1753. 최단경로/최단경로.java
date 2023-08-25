import java.io.*;
import java.util.*;

public class Main {

	static int V, E, K; // 정점 개수, 간선 개수, 시작 정점 번호
	static StringBuilder sb;

	static class Node {
		int v, w;
		Node next;

		public Node(int v, int w, Node next) {
			this.v = v;
			this.w = w;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(br.readLine());

		Node[] adjList = new Node[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjList[u] = new Node(v, w, adjList[u]);
		}

		boolean[] visited = new boolean[V + 1];
		int[] minDist = new int[V + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		for (int i = 0; i < minDist.length; i++) {
			minDist[i] = Integer.MAX_VALUE;
		}
		minDist[K] = 0;
		pq.offer(new int[] {K, minDist[K]});

		while (!pq.isEmpty()) {
			// 최솟값 뽑기
			int[] minD = pq.poll();
			int mi = minD[0];
			int min = minD[1];

			// 방문 체크
			if (visited[mi])
				continue;
			visited[mi] = true;

			// 최솟값 갱신
			for (Node tmp = adjList[mi]; tmp != null; tmp = tmp.next) {
				if (!visited[tmp.v] && minDist[tmp.v] > min + tmp.w) {
					minDist[tmp.v] = min + tmp.w;
					pq.offer(new int[] {tmp.v, minDist[tmp.v]});
				}
			}
		}
		
		for (int i = 1; i < minDist.length; i++) {
			sb.append(minDist[i] == Integer.MAX_VALUE ? "INF" : minDist[i]).append("\n");
		}

		System.out.println(sb);

	}

}