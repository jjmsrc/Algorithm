import java.io.*;
import java.util.*;

public class Main { 

	static int N, M; // 세로 크기, 가로 크기
	static int[][] grid;
	
	static int[][][] blocks = {
		{
			{0, 0}, {0, 1}, {0, 2}, {0, 3}, // 1*4
		},
		{
			{0, 0}, {1, 0}, {2, 0}, {3, 0}, // 4*1
		},
		{
			{0, 0}, {0, 1}, {1, 0}, {1, 1}, // 2*2
		},
	};
	
	private static void makeBlocks() {
		blocks = new int[3 + 8 + 8][4][2];
		blocks[0] = new int[][] {
			{0, 0}, {0, 1}, {0, 2}, {0, 3} // 1*4
		};
		blocks[1] = new int[][] {
			{0, 0}, {1, 0}, {2, 0}, {3, 0} // 4*1
		};
		blocks[2] = new int[][] {
			{0, 0}, {0, 1}, {1, 0}, {1, 1} // 2*2
		};
		
		// 2 * 3 또는 3 * 2 범위의 블록이 가질 수 있는 형태의 경우의 수
		int[][] idxs = {
			{0, 1, 2, 4},	
			{0, 1, 3, 5},	
			{0, 2, 3, 4},	
			{0, 2, 3, 5},	
			{1, 2, 3, 4},	
			{1, 2, 3, 5},	
			{0, 2, 4, 5},	
			{1, 3, 4, 5},	
		};
		
		// 3 * 2 blocks
		// 0, 1
		// 2, 3
		// 4, 5
		int[][] pos1 = {
			{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1} 
		};
		
		int bi = 3;
		for (int[] idx : idxs) {
			int bj = 0;
			for (int i : idx) {
				blocks[bi][bj++] = pos1[i];
			}
			bi++;
		}
		
		// 2 * 3 blocks
		// 0, 2, 4
		// 1, 3, 5
		int[][] pos2 = {
			{0, 0}, {1, 0}, {0, 1}, {1, 1}, {0, 2}, {1, 2} 
		};
		
		// bi = 3 + 8;
		for (int[] idx : idxs) {
			int bj = 0;
			for (int i : idx) {
				blocks[bi][bj++] = pos2[i];
			}
			bi++;
		}
	}
	
	private static int findMaxVal(int[][] block) {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int sum = 0;
				for (int[] xy : block) {
					int nx = i + xy[0];
					int ny = j + xy[1];
					if (nx >= N || ny >= M)
						break;
					sum += grid[nx][ny];
				}
				if (max < sum)
					max = sum;
			}
		}
		return max;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		grid = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeBlocks();
		
		int ans = 0;
		
		for (int[][] block : blocks) {
			int tmp = findMaxVal(block);
			if (ans < tmp)
				ans = tmp;
		}
		
		System.out.print(ans);

	}

}