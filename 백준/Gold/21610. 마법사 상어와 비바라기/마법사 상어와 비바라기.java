import java.io.*;
import java.util.*;

public class Main {

	private static class Wizard {

		static int[][] deltas = { {}, 
				{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, 
				{0, 1}, {1, 1}, {1, 0}, {1, -1}, 
		};

		int N, M;
		int[][] map;
		int[][] cmds;
		Queue<int[]> clouds;

		Wizard(int n, int m, int[][] map, int[][] cmds) {
			super();
			N = n;
			M = m;
			this.map = map;
			this.cmds = cmds;
			clouds = new ArrayDeque<int[]>();
		}

		int simulate() {
			
			clouds.add(new int[] {N - 1, 0});
			clouds.add(new int[] {N - 1, 1});
			clouds.add(new int[] {N - 2, 0});
			clouds.add(new int[] {N - 2, 1});

			for (int i = 0; i < M; i++) {
				// 구름 이동
				this.move(cmds[i][0], cmds[i][1]);
	
				// 비 내리기
				this.rain();
	
				// 물복사버그
				this.bug();
				
				// 구름 생성 및 사라지기
				this.generate();
			}
			
			// 남은 물 구하기
			int ans = this.sum();

			return ans;
		}

		private int sum() {
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += map[i][j];
				}
			}
			
			return sum;
		}

		private void move(int d, int s) {
			int size = clouds.size();
			// s가 N의 2배이상 클 경우 문제있을 수 있음
			for (int i = 0; i < size; i++) {
				int[] c = clouds.poll();
				int nx = (c[0] + deltas[d][0] * s + (N << 5)) % N;
				int ny = (c[1] + deltas[d][1] * s + (N << 5)) % N;
				clouds.offer(new int[] {nx, ny, map[nx][ny]});
				map[nx][ny] = 1;
			}
		}

		private void rain() {
			for (Iterator<int[]> it = clouds.iterator(); it.hasNext();) {
				int[] c = it.next();
				c[2] += 1;
			}
		}

		private void bug() {
			for (Iterator<int[]> it = clouds.iterator(); it.hasNext();) {
				int[] c = it.next();
				for (int i = 2; i <= 8; i += 2) {
					int nx = c[0] + deltas[i][0];
					int ny = c[1] + deltas[i][1];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] > 0)
						c[2] += 1;
				}
			}
		}

		private void generate() {
			int size = clouds.size();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] >= 2) {
						map[i][j] -= 2;
						clouds.offer(new int[] {i, j});
					}
				}
			}
			for (int i = 0; i < size; i++) {
				int[] c = clouds.poll();
				map[c[0]][c[1]] = c[2];
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		int[][] cmds = new int[M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			cmds[i][0] = Integer.parseInt(st.nextToken());
			cmds[i][1] = Integer.parseInt(st.nextToken());
		}

		Wizard wizard = new Wizard(N, M, map, cmds);
		int ans = wizard.simulate();

		System.out.println(ans);
	}

}