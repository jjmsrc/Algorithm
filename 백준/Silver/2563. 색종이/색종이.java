import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();

		// 선언 및 초기화
		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		
		boolean grid[][] = new boolean[100][100];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for (int r = 0; r < 10; r++) {
				for (int c = 0; c < 10; c++) {
					grid[a + r][b + c] = true;
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j])
					cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}