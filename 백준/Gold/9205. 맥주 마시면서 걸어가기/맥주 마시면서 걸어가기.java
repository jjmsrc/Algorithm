import java.util.*;
import java.io.*;

/*
 * 1. 문제 해석
 * 		집에서 락 페스티벌까지 갈 때 맥주를 적절히 마시면서 갈 수 있을지 아닐지를 구하는 문제이다.
 * 
 * 2. 해결 전략
 * 		집에서 락 페스티벌까지 갈 때 편의점을 적절히 경유하는 경로를 찾아야 한다.
 * 		50미터의 한병이고 최대 20병을 들고가므로 편의점을 거치지 않고 이동할 수 있는 최대 거리는 1000미터이다.
 * 		거리의 크기는 상관없이 1000 미터 이내로 연결되는 편의점을 들르면서 이동하는 경로를 찾아야 한다.
 * 		편의점 사이의 거리가 1000미터보다 크다면 연결이 안되어 있다고 간주한다.
 * 
 * 3. 주의점
 * 
 * 
 * */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			int size = N + 2;
			
			int[][] coords = new int[size][2]; 
			
			for (int j = 0; j < size; j++) {
				st = new StringTokenizer(br.readLine());
				coords[j][0] = Integer.parseInt(st.nextToken());
				coords[j][1] = Integer.parseInt(st.nextToken());
			}
			
			boolean ans = simulate(coords);
			
			sb.append(ans ? "happy\n" : "sad\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean simulate(int[][] coords) {
		
		int size = coords.length;
		
		boolean[][] adjMat = new boolean[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (calcDist(coords[i], coords[j]) <= 1000)
					adjMat[j][i] = adjMat[i][j] = true;
			}
		}
		
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < size; j++) {
					if (j == k || j == i)
						continue;
					if (adjMat[i][k] && adjMat[k][j])
						adjMat[i][j] = true;
				}
			}
		}
		
		return adjMat[0][size - 1];
	}

	private static int calcDist(int[] p, int[] q) {
		return Math.abs(p[0] - q[0]) + Math.abs(p[1] - q[1]);
	}

}