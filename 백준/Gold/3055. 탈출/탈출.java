import java.io.*;
import java.util.*;

/*
 * 1. 문제 해석
 * 		고슴도치의 위치는 'S', 비버의 굴은 'D'로 표시된다.
 * 		물은 '*', 돌은 'X', 빈 곳은 '.'으로 표시된다.
 * 		매 분마다 고슴도치는 상하좌우 빈 곳으로 이동할 수 있다.
 * 		매 분마다 물도 상하좌우 4방향으로 동시에 퍼진다.
 * 		물과 고슴도치는 돌을 통과할 수 없다.
 * 		고슴도치는 물을 통과할 수 없다.
 * 		고슴도치가 비버의 굴로 이동할 수 있을 때 필요한 최소 시간을 구하여 출력한다.
 * 
 * 2. 해결 전략
 * 		매 분마다 고슴도치와 물의 위치를 BFS를 이용해 이동시킨다.
 * 		고슴도치는 물을 통과할 수 없으므로 물의 위치를 먼저 이동시키고 그 다음 고슴도치를 이동시킨다.
 * 
 * 3. 주의점
 * 		고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다.
 * 
 * */

public class Main {

	private static class Point {
		int x, y;

		Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
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

		int ans = simulate(map);

		System.out.println(ans != -1 ? ans : "KAKTUS");
	}

	private static int simulate(char[][] map) {
		
		int nRow = map.length;
		int nCol = map[0].length;
		int[] dxs = {1, -1, 0, 0};
		int[] dys = {0, 0, 1, -1};
		
		Queue<Point> hedgehog = new ArrayDeque<>();
		Queue<Point> flood = new ArrayDeque<>();
		
		for (int i = 0; i < nRow; i++) {
			for (int j = 0; j < nCol; j++) {
				if (map[i][j] == 'S') {
					hedgehog.offer(new Point(i, j));
				} else if(map[i][j] == '*') {
					flood.offer(new Point(i, j));
				}
			}
		}
		
		int minutes = 0;
		while(!hedgehog.isEmpty()) {
			++minutes;
			
			for (int i = 0, size = flood.size(); i < size; i++) {
				Point w = flood.poll();
				for (int j = 0; j < 4; j++) {
					int nx = w.x + dxs[j];
					int ny = w.y + dys[j];
					if (nx >= 0 && nx < nRow && ny >= 0 && ny < nCol) {
						if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
							map[nx][ny] = '*';
							flood.offer(new Point(nx, ny));
						}
					}
				}
			}
			
			for (int i = 0, size = hedgehog.size(); i < size; i++) {
				Point w = hedgehog.poll();
				for (int j = 0; j < 4; j++) {
					int nx = w.x + dxs[j];
					int ny = w.y + dys[j];
					if (nx >= 0 && nx < nRow && ny >= 0 && ny < nCol) {
						if (map[nx][ny] == '.') {
							map[nx][ny] = 'S';
							hedgehog.offer(new Point(nx, ny));
						} else if (map[nx][ny] == 'D') {
							return minutes;
						}
					}
				}
			}
		}
		
		return -1;
	}

}