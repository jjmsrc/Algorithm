import java.io.*;
import java.util.*;


public class Main {
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] box = new int[N][M];
		Queue<Point> queue = new ArrayDeque<Point>();
		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		int maxTomatos = N * M;
		int cntTomatos = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					queue.offer(new Point(i, j));
					cntTomatos++;
				} else if (box[i][j] == -1) {
					maxTomatos--;
				}
			}
		}
		
		int day = -1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point p = queue.poll();
				for (int j = 0; j < 4; j++) {
					int nx = p.x + dxs[j];
					int ny = p.y + dys[j];
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && box[nx][ny] == 0) {
						queue.offer(new Point(nx, ny));
						box[nx][ny] = 1;
						cntTomatos++;
					}
				}
			}
			day++;
		}
		
		sb.append(cntTomatos == maxTomatos ? day : -1);
		
		System.out.println(sb);

	}

}