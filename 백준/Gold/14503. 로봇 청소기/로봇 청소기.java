import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int cntSafeArea;
	static int ans;
	
	static int grid[][];

	// 북, 동, 남, 서
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	
	static int simulate(int x, int y, int d) {
		
		boolean isMoved;
		int cntClear = 0;
		
		do {
			isMoved = false;
			
			// 현재 칸 확인
			if (grid[x][y] == 0) {
				grid[x][y] = 2;
				cntClear++;
			}
				
			// 4방향 확인
			for (int i = 0; i < 4; i++) {
				d = (d - 1) & 0b11;
				int nx = x + deltas[d][0];
				int ny = y + deltas[d][1];
				if (grid[nx][ny] == 0) {
					x = nx;
					y = ny;
					isMoved  = true;
					break;
				}
			}
				
			// 빈칸이 없는 경우
			if (!isMoved) {
				int nx = x - deltas[d][0];
				int ny = y - deltas[d][1];
				if (grid[nx][ny] != 1) {
					x = nx;
					y = ny;
					isMoved = true;
				}
			}
			
			
		} while(isMoved);
		
		return cntClear;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()); // 초기 좌표
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); // 초기 방향
		
		grid = new int[N][M];
		for (int i = 0; i < grid.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = simulate(x, y, d);

		sb.append(ans);
		System.out.println(sb);

	}

}