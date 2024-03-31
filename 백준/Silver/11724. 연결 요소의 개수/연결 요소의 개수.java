import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> adjList[] = new ArrayList[N + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		boolean[] isVisited = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			if (isVisited[i])
				continue;
			dfs(i, adjList, isVisited);
			ans += 1;
		}
		
		System.out.println(ans);

	}

	private static void dfs(int i, ArrayList<Integer>[] adjList, boolean[] isVisited) {
		
		if (isVisited[i])
			return;
		isVisited[i] = true;
		for (Integer v : adjList[i]) {
			dfs(v, adjList, isVisited);
		}
		
	}

}