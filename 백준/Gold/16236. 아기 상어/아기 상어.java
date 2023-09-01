import java.io.*;
import java.util.*;

public class Main {

	private static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	private static class Shark {
		Point p;
		int size;
		private int cntEaten;
		Shark(Point p) {
			this.p = p;
			this.size = 2;
			cntEaten = 0;
		}
		void eat() {
			if (size < 7 && ++cntEaten == size) {
				cntEaten = 0;
				++size;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		Point posShark = null;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					posShark = new Point(i, j);
					map[i][j] = 0;
				}
			}
		}

		Shark shark = new Shark(posShark);
		int ans = findAllFishes(map, shark);

		System.out.println(ans);
	}

	private static int findAllFishes(int[][] map, Shark shark) {
		
		int totalTime = 0;
		
		for (int t; (t = findFish(map, shark)) > 0; totalTime += t) {
			int a = t;
		}
		
		return totalTime;
	}

	private static int findFish(int[][] map, Shark shark) { // 가장 가까운 물고기 찾고 소요시간 반환

		final int N = map.length;
		PriorityQueue<Point> pq = new PriorityQueue<Point>((a, b) ->a.x != b.x ? a.x - b.x : a.y - b.y);
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		int[] dxs = { -1, 0, 0, 1 };
		int[] dys = { 0, -1, 1, 0 };
		int time = 1;

		q.offer(shark.p);
		visited[shark.p.x][shark.p.y] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int ss = 0; ss < size; ss++) {
				Point p = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dxs[i];
					int ny = p.y + dys[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || 
							visited[nx][ny] || map[nx][ny] > shark.size)
						continue;
					
					if (map[nx][ny] == 0 || map[nx][ny] == shark.size) {
						q.offer(new Point(nx, ny));
						visited[nx][ny] = true;
					} else {
						pq.offer(new Point(nx, ny));
					}
				}
			}
			if (!pq.isEmpty()) {
				Point p = pq.poll();
				shark.p = p;
				shark.eat();
				map[p.x][p.y] = 0;
				return time;
			}
			time++;
		}

		return 0;
	}

}