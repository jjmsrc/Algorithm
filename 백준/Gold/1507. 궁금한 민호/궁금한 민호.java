import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] adj;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if (N == 1) {
			System.out.println(0);
			return;
		}

		boolean[] isSelected = new boolean[N];
		int ans = fw();
		System.out.println(ans);

	}

	private static int fw() {
		int[][] nadj = new int[N][N];
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) continue;
				for (int j = i + 1; j < N; j++) {
					if (j == k || j == i)
						continue;
					int ikj = adj[i][k] + adj[k][j];
					if (ikj == adj[i][j]) {
						nadj[i][j] = nadj[j][i] = 1;
					} else if (ikj < adj[i][j]) {
						return -1;
					}
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (nadj[i][j] == 0)
					sum += adj[i][j];
			}
		}
		
		return sum;
	}

}