import java.util.*;
import java.io.*;

public class Main {
	
	private static Deque<int[][]> gridPool = new ArrayDeque<int[][]>();
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = findMaxVal(grid, 5);
		
		System.out.println(ans);
		
	}
	
	private static int findMaxVal(int[][] grid, int cnt) {
		
		if (cnt == 0) {
			int max = 0;
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (max < grid[i][j])
						max = grid[i][j];
				}
			}
			return max;
		}
		
		int max = 0;
		for (int i = 0; i < 4; i++) {
			int[][] newGrid = copy(grid);
			move(newGrid, i);
			max = Math.max(max, findMaxVal(newGrid, cnt - 1));
			gridPool.offerFirst(newGrid);
		}
		
		return max;
	}
	
	private static int[][] copy(int[][] grid) {
		int[][] newGrid;
		if (gridPool.size() == 0) {
			newGrid = new int[grid.length][grid.length];
		} else {
			newGrid = gridPool.pollFirst();
		}
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}
		return newGrid;
	}
	
	private static void move(int[][] grid, int di) {
		
		Deque<Integer> deque = new ArrayDeque<Integer>();
		
		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		
		int n = grid.length;
		int x = dxs[di] == 0 ? 0 : (dxs[di] == 1 ? 0 : n - 1);
		int y = dys[di] == 0 ? 0 : (dys[di] == 1 ? 0 : n - 1);
		for (int i = 0; i < n; i++) {
			int nx = x;
			int ny = y;
			for (int j = 0; j < n; j++) {
				if (grid[nx][ny] != 0) {
					deque.offer(grid[nx][ny]);
					grid[nx][ny] = 0;
				}
				nx += dxs[di];
				ny += dys[di];
			}
			nx = x;
			ny = y;
			while(!deque.isEmpty()) {
				int a = deque.poll();
				if (!deque.isEmpty() && a == deque.peek()) {
					deque.poll();
					a *= 2;
				}
				grid[nx][ny] = a;
				nx += dxs[di];
				ny += dys[di];
			}

			if (dxs[di] == 0) {
				x += 1;
			} else {
				y += 1;
			}
		}
		
	}

}