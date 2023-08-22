import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int R, C, SIZE;
	static int ans; // 사각지대의 최소 크기
	static int[][] grid, tmpGrid;
	static int[][][] cctvs;
	static ArrayList<int[]> selectedCCTVs;
	
	private static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // R, D, L, U
	static final int iR = 0, iD = 1, iL = 2, iU = 3;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		SIZE = R * C;

		grid = new int[R][C];
		tmpGrid = new int[R][C];
		selectedCCTVs = new ArrayList<int[]>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] >= 1 && grid[i][j] <= 5) {
					selectedCCTVs.add(new int[] {i, j, grid[i][j], 0});
				}
			}
		}
		
		// cctv 초기화
		makeCCTVs();

		// 계산 수행
		ans = 64;
		findMinArea(0, 0);
		sb.append(ans);

		System.out.println(sb);

	}
	
	private static void makeCCTVs() {
		
		cctvs = new int[6][][];
		cctvs[1] = new int[][] { { iR }, { iD }, { iL }, { iU } };
		cctvs[2] = new int[][] { { iR, iL }, { iD, iU } };
		int[][] tmp = new int[4][2];
		for (int i = 0; i < 4; i++) {
			tmp[i][0] = i;
			tmp[i][1] = (i + 1) & 0b11;
		}
		cctvs[3] = tmp;
		tmp = new int[4][3];
		for (int i = 0; i < 4; i++) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if (i == j)
					continue;
				tmp[i][cnt++] = j;
			}
		}
		cctvs[4] = tmp;
		cctvs[5] = new int[][] { { iR, iD, iL, iU } };
	}

	private static void findMinArea(int cnt, int start) {
		
		if (cnt == selectedCCTVs.size()) {
			int tmp = watch();
			ans = Math.min(ans, tmp);
			return;
		}
		
		int[] info = selectedCCTVs.get(cnt);
		int type = info[2];
		
		for (int i = 0; i < cctvs[type].length; i++) {
			info[3] = i;
			findMinArea(cnt + 1, start + 1);
		}
	}

	private static int watch() { // 선택한 경우의 수로 사각 지대 크기 구하기
		
		// 복사
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmpGrid[i][j] = grid[i][j];
			}
		}
		
		// 감시 영역 칠하기
		for (int[] is : selectedCCTVs) {
			int x = is[0];
			int y = is[1];
			int type = is[2];
			int d = is[3];
			for (int iDelta : cctvs[type][d]) {
				int nx = x + deltas[iDelta][0];
				int ny = y + deltas[iDelta][1];
				while(nx >= 0 && nx < R && ny >= 0 && ny < C && tmpGrid[nx][ny] != 6) {
					if (tmpGrid[nx][ny] == 0)
						tmpGrid[nx][ny] = 7;
					nx += deltas[iDelta][0];
					ny += deltas[iDelta][1];
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (tmpGrid[i][j] == 0)
					cnt++;
			}
		}
		
		return cnt;
	}
}