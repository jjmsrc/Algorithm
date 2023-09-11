import java.io.*;
import java.util.*;

public class Main {

	static int[][] deltas = { {}, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 }, };

	static class Fish {
		int x, y;
		int d;

		Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		Fish(Fish f){
			this.x = f.x;
			this.y = f.y;
			this.d = f.d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[][] map = new int[4][4];
		Fish[] fishes = new Fish[16 + 1];

		for (int x = 0; x < 4; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < 4; y++) {
				int idxFish = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				fishes[idxFish] = new Fish(x, y, d);
				map[x][y] = idxFish;
			}
		}

		int iFish = map[0][0];
		Fish f = fishes[iFish];
		Fish shark = new Fish(0, 0, f.d);
		fishes[map[0][0]] = null;
		map[0][0] = 0;

		int ans = findMaxSum(map, fishes, shark) + iFish;
		System.out.println(ans);
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < 4 && ny >= 0 && ny < 4;
	}
	
	private static int[][] copyMap(int[][] map) {
		int[][] newMap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}
	
	private static Fish[] copyFishes(Fish[] fishes) {
		Fish[] newFishes = new Fish[16 + 1];
		for (int i = 1; i <= 16; i++) {
			if (fishes[i] != null) {
				newFishes[i] = new Fish(fishes[i]);
			}
		}
		return newFishes;
	}

	private static int findMaxSum(int[][] map, Fish[] fishes, Fish shark) {

		// 물고기 이동
		moveFishes(map, fishes, shark);
		
		// 먹고 물고기 상태 업데이트
		int maxVal = 0;
			// 먹을 수 있는 경우
		int nx = shark.x + deltas[shark.d][0];
		int ny = shark.y + deltas[shark.d][1];
		while(isIn(nx, ny)) {
			if (map[nx][ny] > 0) {
				int[][] newMap = copyMap(map);
				Fish[] newFishes = copyFishes(fishes);
				Fish newShark = new Fish(shark);
				
				int iFish = newMap[nx][ny];
				Fish f = newFishes[iFish];
				newShark.x = nx; // 좌표 변경
				newShark.y = ny;
				newShark.d = f.d; // 방향 전환
				newMap[nx][ny] = 0; // 물고기 제거
				newFishes[iFish] = null;
				
				// 다음 액션 수행
				int ans = findMaxSum(newMap, newFishes, newShark);
				if (maxVal < ans + iFish)
					maxVal = ans + iFish;
			}
			nx += deltas[shark.d][0];
			ny += deltas[shark.d][1];
		}
		return maxVal;
	}

	private static void moveFishes(int[][] map, Fish[] fishes, Fish shark) {
		for (int i = 1; i <= 16; i++) {
			Fish f = fishes[i];
			if (f == null) continue;
			int d = f.d;
			for (int j = 0; j < 8; j++) {
				int nx = f.x + deltas[d][0];
				int ny = f.y + deltas[d][1];
				// 움직일 수 있는지 - 벽, 상어 체크
				if (isIn(nx, ny) && (nx != shark.x || ny != shark.y)) {
					
					// 이동 예정 - 물고기와 위치 변경, 맵과 배열 좌표 갱신
					
					Fish nf = fishes[map[nx][ny]]; // 이동 위치의 물고기
					
					// 물고기 위치 바꾸기
					// 위치 변경
					int idx = map[nx][ny];
					map[nx][ny] = map[f.x][f.y];
					map[f.x][f.y] = idx;
					
					// 좌표 갱신
					if (nf != null) {
						nf.x = f.x;
						nf.y = f.y;
					}
					f.x = nx;
					f.y = ny;
					break; // 이동 완료
				}
				// 이동 불가 시 45도 방향 전환
				d = f.d = d == 8 ? 1 : d + 1;
			}
		}
	}
}