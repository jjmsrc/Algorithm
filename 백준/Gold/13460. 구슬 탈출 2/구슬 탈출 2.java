import java.util.*;
import java.io.*;

public class Main {
	
	private static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Point p = (Point) obj;
			return x == p.x && y == p.y;
		}
		
	}
	
	private static int nRow;
	private static int nCol;
	private static char[][] map;
	private static int[][][][] minDist = new int[10][10][10][10];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		nRow = Integer.parseInt(st.nextToken());
		nCol = Integer.parseInt(st.nextToken());
		
		map = new char[nRow][];
		
		for (int i = 0; i < nRow; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int ans = findMinMoves();
		
		System.out.println(ans);
		
	}

	private static int findMinMoves() {
		
		Point red = new Point(0, 0);
		Point blue = new Point(0, 0);
		
		// initialize
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				if (map[i][j] == 'R') {
					red.x = i;
					red.y = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					blue.x = i;
					blue.y = j;
					map[i][j] = '.';
				}
			}
		}
		
		int cnt = findMinMoves(red, blue, 0);
		
		return cnt > 10 ? -1 : cnt;
	}
	
	private static void setVisit(Point red, Point blue, int dist) {
		minDist[red.x][red.y][blue.x][blue.y] = dist;
	}
	
	private static int getVisit(Point red, Point blue) {
		return minDist[red.x][red.y][blue.x][blue.y];
	}

	private static int findMinMoves(Point red, Point blue, int i) {
		
		if (i > 10)
			return 100;
		
		int d = getVisit(red, blue);
		
		if (d != 0 && getVisit(red, blue) <= i)
			return 100;
		
		int minCnt = 100;
		
		setVisit(red, blue, i);
		
		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		
		Point nRed = new Point(0, 0);
		Point nBlue = new Point(0, 0);
		
		for (int j = 0; j < 4; j++) {
			nRed.x = red.x;
			nRed.y = red.y;
			nBlue.x = blue.x;
			nBlue.y = blue.y;
			
			boolean falled = false;
			while (map[nBlue.x][nBlue.y] != '#') {
				nBlue.x += dxs[j];
				nBlue.y += dys[j];
				if (map[nBlue.x][nBlue.y] == 'O') {
					falled = true;
					break;
				}
			}
			nBlue.x -= dxs[j];
			nBlue.y -= dys[j];
			
			if (falled) continue;
			
			while (map[nRed.x][nRed.y] != '#') {
				nRed.x += dxs[j];
				nRed.y += dys[j];
				if (map[nRed.x][nRed.y] == 'O') {
					minCnt = Math.min(minCnt, i + 1);
				}
			}
			nRed.x -= dxs[j];
			nRed.y -= dys[j];
			
			if (nBlue.equals(nRed)) {
				if ((j == 0 && red.x < blue.x) ||
						(j == 1 && red.x > blue.x) ||
						(j == 2 && red.y < blue.y) || 
						(j == 3 && red.y > blue.y)) {
					nRed.x -= dxs[j];
					nRed.y -= dys[j];
				} else {
					nBlue.x -= dxs[j];
					nBlue.y -= dys[j];
				}
			}
			
			minCnt = Math.min(minCnt, findMinMoves(nRed, nBlue, i + 1));
		}
		
		return minCnt;
	}
		
}