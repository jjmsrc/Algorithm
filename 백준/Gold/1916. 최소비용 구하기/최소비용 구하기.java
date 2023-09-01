import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Edge{
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		
	}
	static ArrayList<Edge>[] edgeList;
	static int N,M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edgeList = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			edgeList[i] = new ArrayList<>();
		}
		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[from].add(new Edge(to,weight));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		boolean[] visited = new boolean[N+1];
		int min=Integer.MAX_VALUE;
		int vertex=0;
		for(int i=1;i<=N;i++) {
			vertex=-1;
			min = Integer.MAX_VALUE;
			for(int j=1;j<=N;j++) {
				if(!visited[j]&&dist[j]<min) {
					min = dist[j];
					vertex = j;
				}
			}
			if(vertex==-1) break;
			visited[vertex] = true;
			if(vertex==end) break;
			for(Edge e : edgeList[vertex]) {
				if(!visited[e.to]&&dist[e.to]>min+e.weight) {
					dist[e.to] = min+e.weight;
				}
			}
			
		}
		System.out.println(dist[end]);
	}
}