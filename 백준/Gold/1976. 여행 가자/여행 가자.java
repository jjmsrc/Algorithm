import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Union {
		int[] parents;
		
		void init(int n) {
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
		}
		
		int find(int a) {
			if (a == parents[a]) return a;
			return parents[a] = find(parents[a]);
		}
		
		boolean union(int a, int b) {
			int aRoot = find(a);
			int bRoot = find(b);
			if (aRoot == bRoot) return false;
			parents[bRoot] = aRoot;
			return true;
		}
		
		boolean isConnected(int[] u) {
			for (int i = 1; i < u.length; i++) {
				if (this.find(u[i - 1]) != this.find(u[i]))
					return false;
			}
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N, M;
		int[][] adjMat;
		int[] plan;
		boolean[] visited;
		Union union = new Union();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjMat = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		plan = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		union.init(N);
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			queue.offer(i);
			while(!queue.isEmpty()) {
				int city = queue.poll();
				for (int j = 0; j < N; j++) {
					if (visited[j]) continue;
					if (adjMat[city][j] == 1) {
						visited[j] = true;
						union.union(city, j);
						queue.offer(j);
					}
				}
			}
		}
		
		System.out.println(union.isConnected(plan) ? "YES" : "NO");
	}

}