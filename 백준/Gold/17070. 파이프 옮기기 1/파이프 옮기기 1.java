import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[][] grid = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		final int 가로 = 0, 세로 = 1, 대각선 = 2;
		int[][][] mem = new int[N + 1][N + 1][3];
		mem[1][2][가로] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (grid[i][j] == 0) {
					// 가로로 도착한 경우
					mem[i][j][가로] += mem[i][j - 1][가로] + mem[i][j - 1][대각선];
					// 세로로 도착한 경우
					mem[i][j][세로] += mem[i - 1][j][세로] + mem[i - 1][j][대각선];
					// 대각선으로 도착한 경우
					if (grid[i - 1][j] == 0 && grid[i][j - 1] == 0)
						mem[i][j][대각선] += mem[i - 1][j - 1][가로] + mem[i - 1][j - 1][세로] + mem[i - 1][j - 1][대각선];
				}
			}
		}

		System.out.println(mem[N][N][가로] + mem[N][N][세로] + mem[N][N][대각선]);
	}

}