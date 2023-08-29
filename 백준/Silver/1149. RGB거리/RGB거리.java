import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] cost = new int[3][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[0][i] = Integer.parseInt(st.nextToken());
			cost[1][i] = Integer.parseInt(st.nextToken());
			cost[2][i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < N; i++) {
			cost[0][i] += Math.min(cost[1][i - 1], cost[2][i - 1]);
			cost[1][i] += Math.min(cost[0][i - 1], cost[2][i - 1]);
			cost[2][i] += Math.min(cost[1][i - 1], cost[0][i - 1]);
		}
		
		System.out.println(Math.min(cost[0][N - 1], Math.min(cost[1][N - 1], cost[2][N - 1])));
		
	}

}