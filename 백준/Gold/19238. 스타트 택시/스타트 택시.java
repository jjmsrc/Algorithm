import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Taxi {
		Point pos;
		int fuel;

		Taxi(Point pos, int fuel) {
			this.pos = pos;
			this.fuel = fuel;
		}
	}

	private static class Guest {
		Point destination;

		Guest(Point destination) {
			super();
			this.destination = destination;
		}
	}

	private static class MapField {
		boolean wall;
		Guest guest;
	}

	private static class TaxiSimulator {
		int N, M;
		MapField[][] map;
		Taxi taxi;
//		Queue<Point> queue = new ArrayDeque<Point>();
		int[] dxs = { 1, -1, 0, 0 };
		int[] dys = { 0, 0, 1, -1 };

		boolean[][] isVisited;
		Queue<Point> queue = new ArrayDeque<Point>();
		Queue<Point> checked = new ArrayDeque<Point>();

		TaxiSimulator(int N, int M, ArrayList<Point> wallInfo, Point start, int fuel, int[][] guestInfo) {
			this.N = N;
			this.M = M;
			this.taxi = new Taxi(start, fuel);

			map = new MapField[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new MapField();
				}
			}

			for (Point p : wallInfo) {
				map[p.x][p.y].wall = true;
			}

			for (int[] gi : guestInfo) {
				map[gi[0]][gi[1]].guest = new Guest(new Point(gi[2], gi[3]));
			}

			isVisited = new boolean[N][N];
		}

		int simulate() {
			PriorityQueue<Point> guests = new PriorityQueue<Point>((a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);

			for (int i = 0; i < M; i++) {
				queue.offer(taxi.pos);
				isVisited[taxi.pos.x][taxi.pos.y] = true;
				int dist = 0;
				while (!queue.isEmpty() && guests.size() == 0) {
					for (int j = 0, size = queue.size(); j < size; j++) {
						Point p = queue.poll();
						checked.offer(p);
						if (map[p.x][p.y].guest != null) {
							guests.offer(p);
						}
						for (int di = 0; di < 4; di++) {
							int nx = p.x + dxs[di];
							int ny = p.y + dys[di];
							if (!isIn(nx, ny) || isVisited[nx][ny] || map[nx][ny].wall)
								continue;
							Point np = new Point(nx, ny);
							isVisited[nx][ny] = true;
							queue.offer(np);
						}
					}
					dist++;
				}

				clearQueue();

				if (guests.size() > 0) {
					Point gp = guests.poll();
					Guest g = map[gp.x][gp.y].guest;
					taxi.pos = gp;

					taxi.fuel -= Math.max(0, dist - 1);

					if (taxi.fuel < 0)
						return -1;

					int nDist = 0;
					queue.offer(gp);
					isVisited[gp.x][gp.y] = true;
					boolean passed = false;
					while (!queue.isEmpty() && !passed) {
						for (int j = 0, size = queue.size(); j < size; j++) {
							Point p = queue.poll();
							checked.offer(p);

							if (p.x == g.destination.x && p.y == g.destination.y) {
								taxi.fuel -= nDist;
								if (taxi.fuel < 0)
									return -1;
								taxi.fuel += nDist * 2;
								taxi.pos = p;
								map[gp.x][gp.y].guest = null;
								passed = true;
								break;
							}

							for (int di = 0; di < 4; di++) {
								int nx = p.x + dxs[di];
								int ny = p.y + dys[di];
								if (!isIn(nx, ny) || isVisited[nx][ny] || map[nx][ny].wall)
									continue;
								isVisited[nx][ny] = true;
								Point np = new Point(nx, ny);
								queue.offer(np);
							}
						}
						nDist++;
					}
					
					if (!passed)
						return -1;

					guests.clear();
					clearQueue();
				} else {
					return -1;
				}
			}

			return taxi.fuel;
		}

		boolean isIn(int x, int y) {
			return x >= 0 && x < N && y >= 0 && y < N;
		}

		void clearQueue() {
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				isVisited[p.x][p.y] = false;
			}
			while (!checked.isEmpty()) {
				Point p = checked.poll();
				isVisited[p.x][p.y] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N, M, f;
		ArrayList<Point> wallInfo;
		Point start;
		int[][] guestInfo;
		TaxiSimulator taxiSimulator;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());

		wallInfo = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("1"))
					wallInfo.add(new Point(i, j));
			}
		}

		st = new StringTokenizer(br.readLine());
		start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

		guestInfo = new int[M][4];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int n = Integer.parseInt(st.nextToken());
				guestInfo[i][j] = n - 1;
			}
		}

		taxiSimulator = new TaxiSimulator(N, M, wallInfo, start, f, guestInfo);

		System.out.println(taxiSimulator.simulate());

	}

}