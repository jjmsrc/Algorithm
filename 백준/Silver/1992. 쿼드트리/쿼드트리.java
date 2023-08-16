import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static StringBuilder sb;
	
	static int[][] grid;

	static int checkColor(int r, int c, int size) {
		int color = grid[r][c];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (color != grid[r + i][c + j])
					return -1;
			}
		}
		return color;
	}

	static void compress(int r, int c, int size) {
		
		if (size == 1) {
			sb.append(grid[r][c]);
			return;
		}
		
		int color = checkColor(r, c, size);
		
		if (color >= 0) {
			sb.append(color);
			return;
		}
		
		int half = size / 2;
		
		sb.append("(");
		compress(r, c, half);
		compress(r, c + half, half);
		compress(r + half, c, half);
		compress(r + half, c + half, half);
		sb.append(")");

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		grid = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				grid[i][j] = line.charAt(j) == '0' ? 0 : 1;
			}
		}
		
		compress(0, 0, N);
		
		System.out.println(sb);

	}

}