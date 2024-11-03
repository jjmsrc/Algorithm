import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int nComputers = Integer.parseInt(br.readLine());
		int nEdges = Integer.parseInt(br.readLine());
		int[][] edges = new int[nComputers + 1][nComputers + 1];
		
		for (int i = 0; i < nEdges; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[u][v] = edges[v][u] = 1;
		}
		
		boolean[] isVisted = new boolean[nComputers + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		
		int cnt = 0;
		queue.add(1);
		isVisted[1] = true;
		while(!queue.isEmpty())	{
			int c = queue.poll();
			for (int i = 1; i <= nComputers; i++) {
				if (edges[c][i] == 1 && !isVisted[i]) {
					isVisted[i] = true;
					queue.offer(i);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
	}

}