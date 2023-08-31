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

		@Override
		public String toString() {
			return "Edge [to=" + to + ", weight=" + weight + "]";
		}
		
	}
	static ArrayList<Edge>[] edgeList;
	static int N;
	static long min = Long.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edgeList = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			edgeList[i]=new ArrayList<>();
		}
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int weight = Integer.parseInt(st.nextToken());
				if(i!=j&&weight!=0) {
					edgeList[i].add(new Edge(j,weight));
				}
				
			}
		}
		for(int i=1;i<=N;i++) {
			dfs(i,i,new boolean[N+1],1,0);
//			System.out.println();
		}
		System.out.println(min);
	}
	private static void dfs(int start,int cur,boolean[] visited,int cnt,long sum) {
//		System.out.println(Arrays.toString(visited));
//		System.out.println(start+","+cur+","+cnt+","+sum);
		if(cnt==N) {
			for(Edge e : edgeList[cur]) {
				if(e.to==start) {
//					System.out.println(sum+e.weight);
					min = Math.min(sum+e.weight, min);
					
					return;
				}
			}
		}
		for(Edge e : edgeList[cur]) {
			if(!visited[e.to]&&e.to!=start) {
				visited[e.to] = true;
				dfs(start,e.to,visited,cnt+1,sum+e.weight);
				visited[e.to]=false;
			}
			
		}

	}
}