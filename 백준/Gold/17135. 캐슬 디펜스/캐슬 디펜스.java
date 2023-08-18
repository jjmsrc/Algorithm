import java.io.*;
import java.util.*;


public class Main {

	static int N, M, D;
	static boolean[][] grid;
	static int[] archers;
	static List<Point> attackOrder; // 각 성에서 공격하는 위치를 우선 순위 대로 저장
	static int ansMax;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int calcDist(Point p) {
			return Math.abs(x - p.x) + Math.abs(y - p.y);
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}

	static int calcDist(int x, int y, int nx, int ny) {
		return Math.abs(x - nx) + Math.abs(y - ny);
	}

	static boolean[][] deepCopy(boolean[][] arr) {
		boolean[][] tmp = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		return tmp;
	}

	static int simulate() {

		boolean[][] tmpGrid = deepCopy(grid);

		int cntEnemy = 0;
		
		Stack<Point> attacked = new Stack<Point>();

		for (int t = 0; t <= N - 1; t++) { // 시간
			// 공격 가능한 적 파악
			for (int i = 0; i < 3; i++) { // 모든 궁수에 대해서
				for (Point delta : attackOrder) {
					int nx = N - 1 + delta.x - t;
					int ny = archers[i] + delta.y;
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && tmpGrid[nx][ny]) {
						// 공격한 적 저장
						attacked.push(new Point(nx, ny));
						break;
					}
				}
			}
			
			while(!attacked.isEmpty()) {
				Point e = attacked.pop();
				if (tmpGrid[e.x][e.y]) {
					tmpGrid[e.x][e.y] = false;
					cntEnemy++;
				}
			}
		}

		return cntEnemy;
	}

	static void comb(int cnt, int start) {
		if (cnt == 3) {
			int ans = simulate();
			ansMax = Math.max(ansMax, ans);
			return;
		}

		for (int i = start; i < M; i++) {
			archers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	static void initAttackOrder() {
		// attackOrder 초기화
		int size = 2 * (D - 1) + 1;
		int half = size / 2;
		boolean[][] tmpVisited = new boolean[size][size];
		attackOrder = new ArrayList<Point>();

		int[] dxs = { 0, -1, 0 };
		int[] dys = { -1, 0, 1 };

		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0));
		tmpVisited[size - 1][half] = true;
		int bredth = 0;
		while (!queue.isEmpty() && bredth < D) {
			int qsize = queue.size();
			for (int i = 0; i < qsize; i++) {
				Point p = queue.poll();
				attackOrder.add(p);
				for (int j = 0; j < 3; j++) {
					int dx = p.x + dxs[j];
					int dy = p.y + dys[j];
					int nx = dx + size - 1;
					int ny = dy + half;
					if (nx >= 0 && nx < size && ny >= 0 && ny < size && !tmpVisited[nx][ny]) {
						tmpVisited[nx][ny] = true;
						queue.offer(new Point(dx, dy));
					}
				}
			}
			bredth++;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		grid = new boolean[N][M];
		archers = new int[3];
		ansMax = 0;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j * 2) == '1')
					grid[i][j] = true;
			}
		}

		// 실행
		initAttackOrder();
		comb(0, 0);

		sb.append(ansMax);

		System.out.println(sb);

	}

}