import java.io.*;
import java.util.*;

public class Main {
	
	private static int[][] scores = new int[2][100_000];
	private static int[][] scoresMem = new int[2][100_000];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int nTest = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < nTest; i++) {
			int nCol = Integer.parseInt(br.readLine());
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < nCol; k++) {
					scores[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(solve(nCol)).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	private static int solve(int nCol) {
		
		scoresMem[0][0] = scores[0][0];
		scoresMem[1][0] = scores[1][0];
		
		if (nCol == 1) {
			return Math.max(scoresMem[0][nCol - 1], scoresMem[1][nCol - 1]);
		} 
		
		scoresMem[0][1] = scoresMem[1][0] + scores[0][1];
		scoresMem[1][1] = scoresMem[0][0] + scores[1][1];
		
		if (nCol == 2) {
			return Math.max(scoresMem[0][nCol - 1], scoresMem[1][nCol - 1]);
		}
		
		for (int i = 2; i < nCol; i++) {
			scoresMem[0][i] = Math.max(scoresMem[1][i - 1], scoresMem[1][i - 2]) + scores[0][i];
			scoresMem[1][i] = Math.max(scoresMem[0][i - 1], scoresMem[0][i - 2]) + scores[1][i];
		}
		
		return Math.max(scoresMem[0][nCol - 1], scoresMem[1][nCol - 1]);
	}

}