import java.io.*;
import java.util.*;

/*
 * 1. 문제 해석
 * 		토네이도가 반 시계방향으로 움직이면서 존재하는 모래들을 흩뿌린다.
 * 		흩뿌려진 모래들 중에서 "격자의 밖으로 나간 모래의 양"을 구한다.
 *  
 * 2. 해결 전략
 * 		한 칸 이동할 때마다 모래를 흩뿌린다.
 * 		흩뿌릴 모래의 기준 위치와 비율을 이차원 배열을 이용해 저장하고
 * 		각 이동별로 모래 배열을 순회해 흩뿌리면서 격자 밖으로 나간 모래의 양을 누적 계산한다.
 * 
 * 3. 주의점
 * 
 * */

public class Main {
	
	private static class Tornado {
		
		// 흩뿌릴 모래 정보
		private static final int[][] tornado = {
				// {비율, x좌표, y좌표}
				{0, 0, -1}, // 남은 모래
				{1, -1, 1}, // 1%
				{1, 1, 1},
				{2, -2, 0}, // 2%
				{2, 2, 0},
				{5, 0, -2}, // 5%
				{7, -1, 0}, // 7%
				{7, 1, 0},
				{10, -1, -1}, // 10%
				{10, 1, -1},
		};
		
		// 반시계 방향 90도 회전 행렬곱
		private static final int[][] drot = {
				// {nx}, {ny}
				{0, 1}, {-1, 0}
		};
		
		// 방향
		private static final int[][] deltas = {
				// 좌, 하, 우, 상
				{0, -1}, {1, 0}, {0, 1}, {-1, 0}
		};
		
		final int N;
		int[][] grid;
		int[] currPos;
		int[][] crot;
		int[][] ncrot;
		int di;
		int ans;
		Tornado(int N, int[][] grid) {
			this.N = N;
			this.grid = grid;

		}
		
		int simulate() {
			currPos = new int[] {N / 2, N / 2};
			crot = new int[][] {{1, 0}, {0, 1}};
			ncrot = new int[2][2];
			di = 0;
			ans = 0;
			for (int i = 1; i < N; i++) {
				moveForward(i);
				rotate();
				moveForward(i);
				rotate();
			}
			moveForward(N - 1);
			return ans;
		}
		
		private void rotate() {
			di = (di + 1) & 0b11;
			
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					ncrot[i][j] = crot[i][0] * drot[0][j] + crot[i][1] * drot[1][j];
				}
			}
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					crot[i][j] = ncrot[i][j];
				}
			}
		}
		
		private void moveForward(int len) {
			for (int i = 0; i < len; i++) {
				currPos[0] += deltas[di][0];
				currPos[1] += deltas[di][1];
				blow();
			}
		}
		
		private void blow() {
			int x = currPos[0];
			int y = currPos[1];
			int sum = 0;
			for (int i = 1; i < tornado.length; i++) {
				int nx = x + rotX(i);
				int ny = y + rotY(i);
				int s = (grid[x][y] * tornado[i][0]) / 100;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					grid[nx][ny] += s;
				} else {
					ans += s;
				}
				sum += s;
			}
			int nx = x + rotX(0);
			int ny = y + rotY(0);
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				grid[nx][ny] += grid[x][y] - sum;
			} else {
				ans += grid[x][y] - sum;
			}
			grid[x][y] = 0;
		}
		
		private int rotX (int i) {
			return tornado[i][1] * crot[0][0] + tornado[i][2] * crot[1][0];
		}
		
		private int rotY (int i) {
			return tornado[i][1] * crot[0][1] + tornado[i][2] * crot[1][1];
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] grid = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Tornado tr = new Tornado(N, grid);
		
		System.out.println(tr.simulate());
	}

}