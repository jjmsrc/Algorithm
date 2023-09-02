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
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return x == p.x && y == p.y;
		}
	}
	
	private static class BlockManager {
		int[] dirs;
		int[][] deltas;
		Map<Character, Integer> blocks;
		final int U = 1;
		final int R = 1 << 1;
		final int D = 1 << 2;
		final int L = 1 << 3;
		
		BlockManager() {
			// 블록 정보 생성
			dirs = new int[] { U, R, D, L };
			
			deltas = new int[9][];
			deltas[U] = new int[] { -1, 0 };
			deltas[R] = new int[] { 0, 1 };
			deltas[D] = new int[] { 1, 0 };
			deltas[L] = new int[] { 0, -1 };

			blocks = new HashMap<Character, Integer>();
			blocks.put('1', D | R);
			blocks.put('2', U | R);
			blocks.put('3', U | L);
			blocks.put('4', L | D);
			blocks.put('-', L | R);
			blocks.put('|', U | D);
			blocks.put('+', L | R | U | D);
		}
		
		int[] getDirs() {
			return dirs;
		}
		
		int[] getDelta(int inDir) {
			return deltas[inDir];
		}
		
		int findDir(Point p, Point np) {
			int x = np.x - p.x;
			int y = np.y - p.y;
			if (x == 0) {
				if (y == 1) return R;
				else if (y == -1) return L;
			} else if (y == 0) {
				if (x == 1) return D;
				else if (x == -1) return U;
			}
			return -1;
		}
		
		int findNextDir(int in, char type) {
			int out = -1;
			
			if ("|+-".indexOf(type) >= 0) {
				out = in;
			} else {
				if (in == U) in = D;
				else if (in == D) in = U;
				else if (in == R) in = L;
				else if (in == L) in = R;
				int bDir = blocks.get(type);
				out = bDir ^ in;
			}
			
			return out;
		}

		public char findType(char[] types) {
			
			int dir = 0;
			int zDir = 0;
			for (int i = 0; i < 4; i++) {
				if (types[i] == '.' || types[i] == 'M')
					continue;
				if (types[i] == 'M') {
					continue;
				}
				if (types[i] == 'Z') {
					zDir |= dirs[i];
					continue;
				}
				int in = dirs[i];
				if (in == U) in = D;
				else if (in == D) in = U;
				else if (in == R) in = L;
				else if (in == L) in = R;
				int bDir = blocks.get(types[i]);
				if ((bDir & in) != 0)
					dir |= dirs[i];
			}
			
			if (!blocks.values().contains(dir)) {
				dir |= zDir;
			}
			
			for(char type : blocks.keySet()) {
				int tmp = blocks.get(type);
				if (tmp == dir)
					return type;
			}
			
			return '.';
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}

		int[] ans = findEmptyBlock(map, new Point(R, C));

		System.out.println((ans[0] + 1)+" "+(ans[1] + 1)+" "+(char)ans[2]);
	}
	
	private static int[] findEmptyBlock(char[][] map, Point size) {

		// 출발/도착 위치 확인
		Point m = null;
		for (int i = 0; i < size.x; i++) {
			for (int j = 0; j < size.y; j++) {
				if (map[i][j] == 'M') {
					m = new Point(i, j);
				}
			}
		}
		
		BlockManager bm = new BlockManager();
		int[] dirs = bm.getDirs();

		// 출발할 방향 찾기
		Point c = new Point(m.x, m.y);
		int cDir = -1;
		for (int i = 0; i < 4; i++) { 
			int[] delta = bm.getDelta(dirs[i]);
			int nx = c.x + delta[0];
			int ny = c.y + delta[1];
			if (nx < 0 || nx >= size.x || ny < 0 || ny >= size.y || map[nx][ny] == '.' || map[nx][ny] == 'Z')
				continue;
			cDir = bm.findDir(c, new Point(nx, ny));
			break;
		}

		// 지워진 부분 위치 찾기
		Point e = null;
		while (e == null) {
			int[] delta = bm.getDelta(cDir);
			int nx = c.x + delta[0];
			int ny = c.y + delta[1];
			if (map[nx][ny] != '.') { // 갈 수 있는 블록
				cDir = bm.findNextDir(cDir, map[nx][ny]);
				c.x = nx;
				c.y = ny;
			} else { // 지워진 블록
				e = new Point(nx, ny);
			}
		}
		
		// 지워진 블록의 타입 찾기
		char[] types = new char[4];
		for (int i = 0; i < 4; i++) { 
			int[] delta = bm.getDelta(dirs[i]);
			int nx = e.x + delta[0];
			int ny = e.y + delta[1];
			if (nx < 0 || nx >= size.x || ny < 0 || ny >= size.y) {
				types[i] = '.';
				continue;
			}
			types[i] = map[nx][ny];
		}
		
		char type = bm.findType(types);

		return new int[] {e.x, e.y, (int)type};
	}

}