import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] D = new int[M + 1][N + 1];
			
			for (int i = 1; i <= M; i++) {
				for (int j = 0; j <= Math.min(i, N); j++) {
					if (j == 0 || i == j)
						D[i][j] = 1;
					else 
						D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
				}
			}
			
			sb.append(D[M][N]).append("\n");
		}
		
		System.out.println(sb);
	}

}