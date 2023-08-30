import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static class Node {
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
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] islands = new Node[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			islands[from] = new Node(to, w, islands[from]);
			islands[to] = new Node(from, w, islands[to]);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[N + 1];
		int[] maxDist = new int[N + 1];
		int[] minMaxDist = new int[N + 1];
		maxDist[start] = Integer.MAX_VALUE;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		pq.offer(new int[] {start, maxDist[start], Integer.MAX_VALUE});
		
		while(!pq.isEmpty()) {
			int[] d = pq.poll();
			
			int v = d[0]; // 현재 섬 번호
			
			if (visited[v])
				continue;
			visited[v] = true;
			minMaxDist[v] = d[2];
			
			for (Node node = islands[v]; node != null; node = node.next) {
				if (!visited[node.v] && maxDist[node.v] < node.w) {
					maxDist[node.v] = node.w;
					pq.offer(new int[] {node.v, maxDist[node.v], Math.min(d[2], maxDist[node.v])});
				}
			}
		}

		System.out.println(minMaxDist[end]);
	}
}