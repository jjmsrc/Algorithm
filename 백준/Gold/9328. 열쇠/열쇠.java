import java.io.*;
import java.util.*;

public class Main {
	
	private final static int[] dxs = {0, 1, 0, -1};
	private final static int[] dys = {1, 0, -1, 0};
	
	private enum CellType {
		EMPTY, WALL, DOC, DOOR, KEY
	}
	
	private static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}

	private static class Cell {
		Point p;
		CellType type;
		char alpha;
		Cell(int x, int y, char ctype) {
			p = new Point(x, y);
			if (ctype == '.') {
				type = CellType.EMPTY;
			} else if (ctype == '*') {
				type = CellType.WALL;
			} else if (ctype == '$') {
				type = CellType.DOC;
			} else {
				type = Character.isUpperCase(ctype) ? CellType.DOOR : CellType.KEY;
			}
			this.alpha = ctype;
		}
	}
	
	private static class Map {
		int w;
		int h;
		Cell[][] map;

		Map() {
		}
		
		public void init(int w, int h, BufferedReader br) throws Exception {
			this.w = w;
			this.h = h;
			this.map = new Cell[h][w];
			for (int hi = 0; hi < h; hi++) {
				String line = br.readLine().strip();
				for (int wi = 0; wi < w; wi++) {
					map[hi][wi] = new Cell(hi, wi, line.charAt(wi));
				}
			}
		}
		
		private Cell getCell(Point p) {
			return map[p.x][p.y];
		}

		public int findMaxDocs(SangGeun sg) {
			
			Point p = new Point(0, 0);
			int[] edges = {w - 1, h - 1, w - 1, h - 1};
			final int MAX_DIST = (w - 1 + h - 1) * 2;
			int dist = 0;
			
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; dist < MAX_DIST; i = (i + 1) % 4) {
				for (int j = 0; j < edges[i] && dist < MAX_DIST; j++, dist++) {
					findDocs(getCell(p).p, sg);
					if (sg.checkNewKey()) {
						dist = 0;
						sb.append(" BOOM ");
					}
					sb.append(map[p.x][p.y].alpha);
					p.x += dxs[i];
					p.y += dys[i];
				}
			}
			
			return sg.cntDocs;
		}

		private void findDocs(Point start, SangGeun sg) {

			if (!canMove(sg, getCell(start)) || sg.isVisited(start))
				return;
			
			if (getCell(start).type == CellType.KEY && !sg.hasKey(getCell(start).alpha)) {
				// 새로 열쇠를 얻었을 경우 방문 데이터를 초기화하고 전부 다시 탐색
				sg.addKey(getCell(start).alpha);
				sg.clearVisit();
				sg.visit(start);
			} else {
				if (getCell(start).type == CellType.DOC) {
					sg.findDocument();
				}
				sg.visit(start);
			}
			
			Deque<Point> dq = new ArrayDeque<>();
			
			dq.add(getCell(start).p);
			while(!dq.isEmpty()) {
				Point p = dq.pollFirst();
				for (int i = 0; i < 4; i++) {
					int nx = p.x + dxs[i];
					int ny = p.y + dys[i];
					if (!isIn(nx, ny)) continue;
					Point np = map[nx][ny].p;
					if (canMove(sg, map[nx][ny]) && !sg.isVisited(np)) {
						if (map[nx][ny].type == CellType.KEY && !sg.hasKey(map[nx][ny].alpha)) {
							// 새로 열쇠를 얻었을 경우 방문 데이터를 초기화하고 전부 다시 탐색
							sg.addKey(map[nx][ny].alpha);
							sg.clearVisit();
							sg.visit(np);
							dq.clear();
							dq.offerLast(np);
							break;
						} else {
							if (map[nx][ny].type == CellType.DOC) {
								sg.findDocument();
							}
							sg.visit(np);
							dq.offerLast(np);
						}
					}
				}
			}
		}
		
		private boolean isIn(int x, int y) {
			return x >= 0 && x < h && y >= 0 && y < w;
		}

		private boolean canMove(SangGeun sg, Cell c) {
			if (c.type != CellType.WALL) { 	// 벽이 아닌 경우
				if (c.type == CellType.DOOR && !sg.hasKey(c.alpha)) {
					return false; 			// 문에 알맞는 열쇠가 없는 경우 제외
				}
				return true;
			}
			return false;
		}

	}
	
	private static class SangGeun {
		int cntDocs;
		int cntKeys;
		HashSet<Character> keys;
		HashSet<Point> visit;

		SangGeun() {
			this.cntDocs = 0;
			this.cntKeys = 0;
			this.keys = new HashSet<>();
			this.visit = new HashSet<>();
		}
		
		public void findDocument() {
			cntDocs++;
		}

		public void clearVisit() {
			visit.clear();
			cntDocs = 0;
		}

		boolean hasKey(char door) {
			return keys.contains(Character.toLowerCase(door));
		}
		
		void addKey(char key) {
			keys.add(key);
		}
		
		boolean isVisited(Point p) {
			return visit.contains(p);
		}
		
		void visit(Point p) {
			visit.add(p);
		}
		
		boolean checkNewKey() {
			if (cntKeys < keys.size()) {
				cntKeys = keys.size();
				return true;
			}
			return false;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int numTest = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < numTest; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			Map map = new Map();
			map.init(w, h, br);
			
			SangGeun sg = new SangGeun();
			
			String line = br.readLine().strip();
			if (!line.equals("0")) {
				for (int li = 0; li < line.length(); li++) {
					sg.addKey(line.charAt(li));
				}
			}
			int ans = map.findMaxDocs(sg);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);

	}

}