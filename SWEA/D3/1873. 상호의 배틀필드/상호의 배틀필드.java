import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution {
	
	static int R, C;
	static char[][] grid;
	
	private static class Tank {
		private static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // R, D, L, U
		int x, y;
		int d; // 방향
		
		public Tank(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		void exec(char cmd) {
			switch(cmd) {
			case 'U':
				move(3);
				break;
			case 'D':
				move(1);
				break;
			case 'L':
				move(2);
				break;
			case 'R':
				move(0);
				break;
			case 'S':
				shoot();
				break;
			}
		}
		
		void move(int d) {
			this.d = d;
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && grid[nx][ny] == '.') {
				grid[nx][ny] = (">v<^").charAt(d);
				grid[x][y] = '.';
				x = nx;
				y = ny;
			} else {
				grid[x][y] = (">v<^").charAt(d);
			}
		}
		
		void shoot() {
			int nx = x + deltas[d][0];
			int ny = y + deltas[d][1];
			
			while(nx >= 0 && nx < R && ny >= 0 && ny < C) {
				int type = grid[nx][ny];
				if (type == '*') {
					grid[nx][ny] = '.';
					break;
				} else if (type == '#') {
					break;
				} else if (type == '.' || type == '-') {
					nx += deltas[d][0];
					ny += deltas[d][1];
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			grid = new char[R][C];
			Tank tank = null;
			
			for (int j = 0; j < R; j++) {
				grid[j] = br.readLine().toCharArray();
				for (int j2 = 0; j2 < C; j2++) {
					if (">v<^".indexOf(grid[j][j2]) >= 0) {
						tank = new Tank(j, j2, ">v<^".indexOf(grid[j][j2]));
					}
				}
			}
			
			int nCmd = Integer.parseInt(br.readLine());
			String line = br.readLine();
			for (int j = 0; j < nCmd; j++) {
				tank.exec(line.charAt(j));
			}
			
			sb.append("#" + i + " ");
			for (int j = 0; j < R; j++) {
				sb.append(grid[j]).append("\n");
			}
		}

		System.out.println(sb.toString());

	}

}