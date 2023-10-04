import java.io.*;
import java.util.*;


public class Main {

	private static int[] dxs = { -1, 1, 0, 0 };
	private static int[] dys = { 0, 0, -1, 1 };

	static int N, M;

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static class Bridge{
		int v;
		int cost;
		Bridge next;

		Bridge(int v, int cost, Bridge next) {
			super();
			this.v = v;
			this.cost = cost;
			this.next = next;
		}

	}

	private static class BridgeComp implements Comparable<BridgeComp> {
		int u;
		Bridge b;

		BridgeComp(int u, Bridge b) {
			super();
			this.u = u;
			this.b = b;
		}

		@Override
		public int compareTo(BridgeComp o) {
			return this.b.cost - o.b.cost;
		}

	}
	
	private static class Union {
		int[] parents;
		
		void init(int size) {
			parents = new int[size];
			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
		}
		
		int find(int a) {
			if (parents[a] == a)
				return a;
			return parents[a] = find(parents[a]);
		}
		
		boolean union(int a, int b) {
			int aRoot = find(a);
			int bRoot = find(b);
			if (aRoot == bRoot)
				return false;
			parents[bRoot] = aRoot;
			return true;
		}
		
		boolean isSingleUnion() {
			int root = find(parents[2]);
			for (int i = 3; i < parents.length; i++) {
				if(find(parents[i]) != root)
					return false;
			}
			return true;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = solve(map);
		System.out.println(ans);

	}

	private static int solve(int[][] map) {
		int ans = 0;

		ArrayList<Bridge> bridges = findBridges(map);

//		for (int i = 0; i < bridges.size(); i++) {
//			Bridge bridge = bridges.get(i);
//			StringBuilder sb = new StringBuilder();
//			sb.append(i + ": ");
//			for (Bridge b = bridge; b != null; b = b.next) {
//				sb.append("(" + b.v + ", " + b.cost + ")--");
//			}
//			System.out.println(sb);
//		}
//
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		ans = calcMinCost(bridges);

		return ans;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static ArrayList<Bridge> findBridges(int[][] map) {

		ArrayList<Bridge> bridges = new ArrayList<Bridge>();
		int idxIsland = 2;

		// 섬 표시하기
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1) {
					Queue<Point> queue = new ArrayDeque<>();

					Point np = new Point(i, j);
					queue.offer(np);
					map[i][j] = idxIsland;
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nx = p.x + dxs[dir];
							int ny = p.y + dys[dir];
							if (!isIn(nx, ny))
								continue;
							np = new Point(nx, ny);
							if (map[nx][ny] == 1) {
								queue.offer(np);
								map[nx][ny] = idxIsland;
							}
						}
					}
					idxIsland++;
				}
			}
		}

		for (int i = 0; i < idxIsland; i++) {
			bridges.add(null);
		}

		// 놓을 수 있는 다리 구하기
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					Queue<Point> queue = new ArrayDeque<>();

					queue.offer(new Point(i, j));
					visited[i][j] = true;
					int idx = map[i][j];
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int dir = 0; dir < 4; dir++) {
							int nx = p.x + dxs[dir];
							int ny = p.y + dys[dir];
							if (!isIn(nx, ny))
								continue;
							if (visited[nx][ny])
								continue;
							if (map[nx][ny] == idx) {
								visited[nx][ny] = true;
								queue.offer(new Point(nx, ny));
							} else if (map[nx][ny] == 0) { // add bridges
								int[] res = buildBridge(map, nx, ny, dir);
								if (res[0] != -1 && res[0] != idx) {
									bridges.set(idx, new Bridge(res[0], res[1], bridges.get(idx)));
								}
							}
						}
					}
				}
			}
		}

		return bridges;
	}

	private static int[] buildBridge(int[][] map, int x, int y, int dir) {
		int d = dir;
		int nx = x + dxs[d];
		int ny = y + dys[d];
		int cost = 1;
		while (isIn(nx, ny) && map[nx][ny] == 0) {
			nx += dxs[d];
			ny += dys[d];
			cost++;
		}

		if (isIn(nx, ny) && map[nx][ny] != 0 && cost >= 2) {
			return new int[] { map[nx][ny], cost };
		}

		return new int[] { -1, 0 };
	}

	private static int calcMinCost(ArrayList<Bridge> bridges) {

		int totalCost = 0;

		boolean[] visited = new boolean[bridges.size()];
		visited[0] = visited[1] = true;

		PriorityQueue<BridgeComp> pq = new PriorityQueue<>();
		Union union = new Union();
		union.init(bridges.size());

		for (int u = 2; u < bridges.size(); u++) {
			for (Bridge b = bridges.get(u); b != null; b = b.next) {
				pq.offer(new BridgeComp(u, b));
			}
		}

		while (!pq.isEmpty()) {
			BridgeComp bc = pq.poll();
			if (union.find(bc.b.v) == union.find(bc.u))
				continue;
			union.union(bc.b.v, bc.u);
			totalCost += bc.b.cost;
			visited[bc.u] = true;
			visited[bc.b.v] = true;
		}
		
		if (!union.isSingleUnion())
			return -1;

		return totalCost;
	}

}