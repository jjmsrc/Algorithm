import java.util.*;
import java.io.*;


public class Main {
	
	static int R, C;
	static int[] dxs = {0, -1, 0, 1};
	static int[] dys = {1, 0, -1, 0};
	static int[] rdxs = {0, 1, 0, -1};
	static int[] rdys = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] room = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = simulate(room, T);
		
		System.out.println(ans);
	}
	
	private static int simulate(int[][] room, int T) {
		
		int ac = -1; // 공기청정기 위쪽 열 좌표
		int[][] roomNext = new int[R][C];
		for (int i = 0; i < R; i++) {
			if (room[i][0] == -1) {
				roomNext[i][0] = -1;
				roomNext[i + 1][0] = -1;
				ac = i;
				break;
			}
		}
		
		for (int t = 0; t < T; t++) {
			
			// 미세먼지 확산
			spread(room, roomNext);
			
			int[][] tmp = room;
			room = roomNext;
			roomNext = tmp;
			
			// 공기 순환
			cleanAir(room, ac);
			
		}
		
		int amountOfDust = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0)
					amountOfDust += room[i][j];
			}
		}
		
		return amountOfDust;
	}
	
	private static void cleanAir(int[][] room, int airCleaner) {
		
		int uac = airCleaner, dac = airCleaner + 1;
		
		int[] uLen = {C - 2, uac, C - 1, uac - 1};
		int[] dLen = {C - 2, R - dac - 1, C - 1, R - dac - 2};
		
		int x = uac - 1;
		int y = 0;
		for (int i = 3; i >= 0; i--) {
			int nx = x - dxs[i];
			int ny = y - dys[i];
			int len = uLen[i];
			for (int j = 0; j < len; j++) {
				room[x][y] = room[nx][ny];
				x = nx;
				y = ny;
				nx -= dxs[i];
				ny -= dys[i];
			}
		}
		room[uac][1] = 0;
		
		x = dac + 1;
		y = 0;
		for (int i = 3; i >= 0; i--) {
			int nx = x - rdxs[i];
			int ny = y - rdys[i];
			int len = dLen[i];
			for (int j = 0; j < len; j++) {
				room[x][y] = room[nx][ny];
				x = nx;
				y = ny;
				nx -= rdxs[i];
				ny -= rdys[i];
			}
		}
		room[dac][1] = 0;
	}

	private static void spread(int[][] room, int[][] roomNext) {
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0) {
					int cnt = 0;
					int r = room[i][j] / 5;
					for (int k = 0; k < 4; k++) {
						int nx = i + dxs[k];
						int ny = j + dys[k];
						if (nx < 0 || nx >= R || ny < 0 || ny >= C || room[nx][ny] == -1)
							continue;
						++cnt;
						roomNext[nx][ny] += r;
					}
					roomNext[i][j] += room[i][j] - r * cnt;
					room[i][j] = 0;
				}
			}
		}
	}

}