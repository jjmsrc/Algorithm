import java.util.*;
import java.io.*;

public class Main {

	static int N;

	private static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Point))
				return false;
			Point p = (Point) o;
			return p.x == this.x && p.y == this.y;
		}
	}

	private static boolean isIn(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		int[][] grid = new int[N + 1][N + 1];
		final int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			grid[x][y] = 1;
		}

		final int L = Integer.parseInt(br.readLine());
		final int ROTL = 0, ROTR = 1;
		int[][] movements = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			int rot = st.nextToken().charAt(0) == 'L' ? ROTL : ROTR;
			movements[i][0] = sec;
			movements[i][1] = rot;
		}

		Deque<Point> snake = new ArrayDeque<>();
		snake.add(new Point(1, 1));
		int[] dxs = { 0, 1, 0, -1 }; // 좌, 하, 우, 상
		int[] dys = { 1, 0, -1, 0 };
		int dir = 0;

		int cntSec = 1;
		int iMove = 0;
		while (true) {

			Point head = snake.peekLast();
			int nx = head.x + dxs[dir];
			int ny = head.y + dys[dir];
			Point nHead = new Point(nx, ny);

			if (!isIn(nx, ny) || snake.contains(nHead)) // 탈출 조건
				break;

			if (grid[nx][ny] == 0) // 사과가 없을 경우
				snake.poll();
			else {
				grid[nx][ny] = 0;
			}
			snake.add(nHead);

			// 다음 방향 결정
			if (iMove < L && cntSec == movements[iMove][0]) {
				dir = movements[iMove][1] == ROTL ? (dir - 1) & 3 : (dir + 1) & 3;
				iMove++;
			}

			cntSec++;
		}

		System.out.println(cntSec);

	}

}