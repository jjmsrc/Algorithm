import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] grid = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		Queue<int[]> air = new ArrayDeque<int[]>();
		Queue<int[]> meltedCheese = new ArrayDeque<int[]>();
		
		int VISITED = 0b011, UNVISTED = 0b111;
		int CHEESE = 1;
		
		int time = 0;
		int cntCheese = 0;
		
		do {
			++time;
			
			cntCheese = 0;
			while(!meltedCheese.isEmpty()) {
				int[] c = meltedCheese.poll();
				if (grid[c[0]][c[1]] == CHEESE)
					cntCheese++;
				grid[c[0]][c[1]] = UNVISTED;
			}
			
			air.offer(new int[] {0, 0});
			grid[0][0] = VISITED;
			while(!air.isEmpty()) {
				int[] ap = air.poll();
				for (int i = 0; i < 4; i++) {
					int nx = ap[0] + dxs[i];
					int ny = ap[1] + dys[i];
					if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
						if (grid[nx][ny] == 0 || grid[nx][ny] == UNVISTED) {
							grid[nx][ny] = VISITED;
							air.offer(new int[] {nx, ny});
						} else if (grid[nx][ny] == CHEESE) {
							meltedCheese.offer(new int[] {nx, ny});
						}
					}
				}
			}
			
			VISITED ^= 0b100;
			UNVISTED ^= 0b100;
			
		} while(!meltedCheese.isEmpty());

		System.out.println(time - 1);
		System.out.println(cntCheese);

	}

}