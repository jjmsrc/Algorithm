import java.io.*;
import java.util.*;

public class Main {

	private static class Player {
		int x, y;
		int keys;

		Player(int x, int y, int keys) {
			super();
			this.x = x;
			this.y = y;
			this.keys = keys;
		}

		void addKey(char key) {
			int idx = "abcdef".indexOf(key);
			if (idx >= 0)
				keys = keys | (1 << idx);
		}
		
		boolean openDoor(char door) {
			int idx = "ABCDEF".indexOf(door);
			if (idx != -1)
				return (keys & (1 << idx)) > 0;
			return false;
		}
	}

	private static class Map {
		int N, M;
		char[][] map;
		boolean[][][] visited;

		Map(int n, int m, char[][] map) {
			super();
			N = n;
			M = m;
			this.map = map;

			visited = new boolean[1 << 6][N][M];
		}
		
		private boolean isIn(int x, int y) {
			return x >= 0 && x < N && y >= 0 && y < M;
		}
		
		private boolean visit(int x, int y, int keys) {
			if (!visited[keys][x][y])
				return visited[keys][x][y] = true;
			return false;
		}
		
		private Player findStart() {
			Player p = null;
			// find start point
			for (int i = 0; i < N && p == null; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '0') {
						p = new Player(i, j, 0);
						map[i][j] = '.';
						visited[0][i][j] = true;
						break;
					}
				}
			}
			return p;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] mapinp = new char[N][];

		for (int i = 0; i < N; i++) {
			mapinp[i] = br.readLine().toCharArray();
		}
		
		Map map = new Map(N, M, mapinp);

		int ans = solve(map);

		System.out.println(ans);

	}

	private static int solve(Map map) {

		int[] dxs = { 1, -1, 0, 0 };
		int[] dys = { 0, 0, 1, -1 };
		Queue<Player> players = new ArrayDeque<>();

		Player start = map.findStart();
		players.offer(start);

		int time = 0;
		while (!players.isEmpty()) {

			time++;

			for (int i = 0, size = players.size(); i < size; i++) {
				Player p = players.poll();

				for (int j = 0; j < 4; j++) {
					int nx = p.x + dxs[j];
					int ny = p.y + dys[j];

					// check in map
					if (!map.isIn(nx, ny))
						continue;

					char c = map.map[nx][ny];
					Player np;
					// search
					switch (c) {
					case 'a':
					case 'b':
					case 'c':
					case 'd':
					case 'e':
					case 'f':
						// add key and move
						np = new Player(nx, ny, p.keys);
						np.addKey(c);
						if (map.visit(nx, ny, np.keys))
							players.offer(np);
						break;
					case 'A':
					case 'B':
					case 'C':
					case 'D':
					case 'E':
					case 'F':
						// open the door
						if (p.openDoor(c)) {
							np = new Player(nx, ny, p.keys);
							if (map.visit(nx, ny, np.keys))
								players.offer(np);
						}
						break;
					case '.':
						// check visit
						if (map.visit(nx, ny, p.keys))
							players.offer(new Player(nx, ny, p.keys));
						break;
					case '1':
						return time;
					}
				}
			}
		}

		return -1;
	}

}