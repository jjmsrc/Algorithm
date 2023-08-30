import java.io.*;
import java.util.*;

/*
 * 1. 문제 해석
 * 		말이 되고픈 원숭이가 움직이면서 도착지점까지 갈 때 그 동작수의 최솟값을 출력한다.
 * 		원숭이는 말처럼 K번만 움직일 수 있으며, 상하좌우로 움직일 수 있다.
 * 		말처럼 움직일 때는 벽을 넘을 수 있다.
 * 		격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다.
 * 
 * 2. 해결 전략
 * 		W*H*(K+1) 의 메모리 배열을 만들어 DP를 수행한다.
 * 
 * 3. 주의점
 * 
 * */

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[W][H];
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < H; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dxsHorse = {-1, -2, -2, -1, 1, 2, 2, 1}; // 오른쪽에서 반시계방향으로
		int[] dysHorse = {2, 1, -1, -2, -2, -1, 1, 2};
		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		int[][][] mem = new int[W][H][K + 1];
		mem[0][0][K] = 1;
		q.offer(new int[] {1, 0, 0, K});
		while(!q.isEmpty()) {
			
			int[] d = q.poll();
			int r = d[1];
			int c = d[2];
			int k = d[3];
			
			if (r == W - 1 && c == H - 1)
				break;
			
			if (k > 0)
				for (int i = 0; i < 8; i++) {
					int nx = r + dxsHorse[i];
					int ny = c + dysHorse[i];
					
					if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[nx][ny] == 0) {
						if (mem[nx][ny][k - 1] == 0 || mem[nx][ny][k - 1] > mem[r][c][k] + 1) {
							mem[nx][ny][k - 1] = mem[r][c][k] + 1;
							q.offer(new int[] {mem[nx][ny][k - 1], nx, ny, k - 1});
						}
					}
				}
			for (int i = 0; i < 4; i++) {
				int nx = r + dxs[i];
				int ny = c + dys[i];
				if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[nx][ny] == 0) {
					if (mem[nx][ny][k] == 0 || mem[nx][ny][k] > mem[r][c][k] + 1) {
						mem[nx][ny][k] = mem[r][c][k] + 1;
						q.offer(new int[] {mem[nx][ny][k], nx, ny, k});
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= K; i++) {
			if (mem[W - 1][H - 1][i] > 0 && min > mem[W - 1][H - 1][i])
				min = mem[W - 1][H - 1][i];
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min - 1);
	}
}