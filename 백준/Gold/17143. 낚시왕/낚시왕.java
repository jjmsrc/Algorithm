import java.io.*;
import java.util.*;

/*
 * 1. 문제 해석
 * 		낚시왕이 상어 낚시를 한다.
 * 		격자판에 존재하는 상어가 1초마다 주어진 정보에 따라 움직인다.
 * 		낚시왕은 가장 왼쪽 열에 1칸 왼쪽에서 시작해 1초에  한칸씩 오른쪽으로 전진하면서
 * 		같은 열에있는 상어 중 땅과 가장 가까이 있는 상어를 하나 잡을 수 있다.
 * 		이때 낚시왕이 잡은 상어 크기의 총합을 구하는 문제이다.
 * 
 * 2. 해결 전략
 * 		맵의 정보와 각 상어의 정보를 따로 저장하는 자료구조를 적절히 선택하고
 * 		조건을 꼼꼼히 파악해 결과를 계산한다.
 * 
 * 3. 주의점
 * 		상어는 벽에 부딪히면 반대 방향으로 이동한다.
 * 		상어가 같은 칸에서 만나는 경우 크기가 더 큰 상어가 잡아먹는다.
 * 		상어가 움직이다 부딪히는 경우는 없고 움직이고 멈춘 자리에서만 비교한다.
 * 		상어의 (r, c) 좌표는 1부터 시작한다.
 * 
 * */

public class Main {

	static int R, C, M; // 격자판 행 길이, 열 길이, 상어의 수
	static int[][][] grid;
	static int[] dx = { -1, 1, 0, 0 }; // 위, 아래, 오른쪽, 왼쪽
	static int[] dy = { 0, 0, 1, -1 };

	static class Shark { // 상어
		int s, d, z; // 속력, 이동방향, 크기

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

		static int[] move(int r, int c, Shark shark) {

			int speed = shark.s;
			int dir = shark.d;

			while (--speed >= 0) {
				int nr = r + dx[dir];
				int nc = c + dy[dir];
				if (nr >= R || nr < 0 || nc >= C || nc < 0) {
					shark.d = dir = dir ^ 1;
					nr = r + dx[dir];
					nc = c + dy[dir];
				}
				r = nr;
				c = nc;
			}

			return new int[] { r, c };
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new int[R][C][2];

		Shark[] sharks = new Shark[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 좌표값 보정
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향값 보정
			int z = Integer.parseInt(st.nextToken());
			grid[r][c][0] = i;
			sharks[i] = new Shark(s, d, z);
		}

		int sumSize = 0;
		for (int king = 0; king < C; king++) {
			int phase = king & 1; // 현재 맵
			int nPhase = phase ^ 1; // 다음 맵

			for (int r = 0; r < R; r++) {
				if (grid[r][king][phase] != 0) {
					int s = grid[r][king][phase];
					sumSize += sharks[s].z;
					sharks[s] = null;
					grid[r][king][phase] = 0;
					break;
				}
			}

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (grid[r][c][phase] != 0) {
						int s = grid[r][c][phase];
						int[] p = Shark.move(r, c, sharks[s]); // 이동
						grid[r][c][phase] = 0; // 현재 맵에서 삭제
						int nr = p[0];
						int nc = p[1];
						if (grid[nr][nc][nPhase] != 0) { // 겹칠 때
							int ps = grid[nr][nc][nPhase]; // 겹치는 상어 위치
							if (sharks[s].z > sharks[ps].z)
								grid[nr][nc][nPhase] = s;
						} else {
							grid[nr][nc][nPhase] = s;
						}
					}
				}
			}
		}

		System.out.println(sumSize);

	}

}