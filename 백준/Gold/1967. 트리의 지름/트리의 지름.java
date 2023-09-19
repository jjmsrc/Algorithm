import java.io.*;
import java.util.*;


public class Main {

	static final int parent = 0, children = 1;

	private static class Edge {
		int to;
		int w;
		Edge next;

		Edge(int to, int w, Edge next) {
			this.to = to;
			this.w = w;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Edge[][] adjList = new Edge[N + 1][2];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjList[u][children] = new Edge(v, w, adjList[u][children]);
			adjList[v][parent] = new Edge(u, w, adjList[v][parent]);
		}

		int maxDiameter = 0;
		int[] maxDist = new int[N + 1]; // 리프노드에서 현재노드까지 오는 최대 거리
		for (int i = N; i >= 1; i--) {
			maxDiameter = Math.max(maxDiameter, calcMaxDiameter(i, maxDist, adjList));
		}
		System.out.println(maxDiameter);

	}

	private static int calcMaxDiameter(int currV, int[] maxDist, Edge[][] adjList) {
		
		if (adjList[currV][children] == null)
			return 0;
		
		int max[] = { 0, 0 };
		for (Edge edge = adjList[currV][children]; edge != null; edge = edge.next) {
			if (adjList[currV][children] != null && maxDist[edge.to] == 0)
				calcMaxDiameter(edge.to, maxDist, adjList);
			int d = maxDist[edge.to] + edge.w;
			if (max[0] < d) {
				max[1] = max[0];
				max[0] = d;
			} else if (max[1] < d) {
				max[1] = d;
			}
		}
		
		// 현재 최대 누적 거리 갱신
		maxDist[currV] = max[0];
		
		return max[0] + max[1];
	}
	
}