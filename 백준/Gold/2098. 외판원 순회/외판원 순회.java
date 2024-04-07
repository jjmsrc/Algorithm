import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static final int MAX_INT = Integer.MAX_VALUE >> 2;
	private static final int NO_WAY = -1;
	private static int N;
	private static int[][] adjMat;
	private static int[][] minDist;
	private static int start;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adjMat = new int[N][N];
		minDist = new int[N][1 << N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < minDist[i].length; j++) {
				minDist[i][j] = MAX_INT;
			}
		}
		
		start = 0;
		System.out.println(dfs(start, 1));

	}
	
	private static int dfs(int curr, int visited) {
		
		
		if (minDist[curr][visited] == NO_WAY)
			return MAX_INT;
		
		if (minDist[curr][visited] != MAX_INT)
			return minDist[curr][visited];

		
		if (visited == (1 << N) - 1) {
			int a = adjMat[curr][start] == 0 ? MAX_INT : adjMat[curr][start];
			return minDist[curr][visited] = adjMat[curr][start] == 0 ? MAX_INT : adjMat[curr][start];
		}
		
		for (int j = 0; j < N; j++) {
			if (adjMat[curr][j] != 0 && ((visited & (1 << j)) == 0)) {
				int m = adjMat[curr][j] + dfs(j, visited | (1 << j));
				minDist[curr][visited] = Math.min(minDist[curr][visited], m);
			}
		}
		
		if (minDist[curr][visited] == MAX_INT)
			minDist[curr][visited] = NO_WAY;
		
		return minDist[curr][visited] == NO_WAY ? MAX_INT : minDist[curr][visited];
	}

}