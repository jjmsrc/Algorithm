import java.io.*;
import java.util.*;


public class Main {
	
	static class Point {
		int x;
		int y;
		int z;
		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] box = new int[H][N][M];
		Queue<Point> queue = new ArrayDeque<Point>();
		int[] dxs = {1, -1, 0, 0, 0, 0};
		int[] dys = {0, 0, 1, -1, 0, 0};
		int[] dzs = {0, 0, 0, 0, 1, -1};
		int maxTomatos = N * M * H;
		int cntTomatos = 0;
		
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < M; c++) {
					box[h][r][c] = Integer.parseInt(st.nextToken());
					if (box[h][r][c] == 1) {
						queue.offer(new Point(r, c, h));
						cntTomatos++;
					} else if (box[h][r][c] == -1) {
						maxTomatos--;
					}
				}
			}
		}
		
		int day = -1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point p = queue.poll();
				for (int j = 0; j < 6; j++) {
					int nx = p.x + dxs[j];
					int ny = p.y + dys[j];
					int nz = p.z + dzs[j];
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && nz >= 0 && nz < H && box[nz][nx][ny] == 0) {
						queue.offer(new Point(nx, ny, nz));
						box[nz][nx][ny] = 1;
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