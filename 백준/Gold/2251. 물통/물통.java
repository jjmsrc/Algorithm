import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[] bottleSize = new int[3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			bottleSize[i] = Integer.parseInt(st.nextToken());
		}

		int R = Math.min(bottleSize[0], bottleSize[2]) + 1;
		int C = Math.min(bottleSize[1], bottleSize[2]) + 1;
		int[][] d = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				d[i][j] = -1;
			}
		}

		Stack<int[]> stack = new Stack<int[]>();
		stack.push(new int[] {0, 0});
		
		int[][] perm = {
			{0, 1, 2},
			{0, 2, 1},
			{1, 0, 2},
			{1, 2, 0},
			{2, 0, 1},
			{2, 1, 0},
		};
		int[] ws = new int[3];
		int[] nws = new int[3];
		while(!stack.isEmpty()) {
			int[] p = stack.pop();
			int a = p[0];
			int b = p[1];
			int c = d[a][b] = bottleSize[2] - a - b;
			ws[0] = a;
			ws[1] = b;
			ws[2] = c;
			for(int[] pp : perm) { // pp[0] -> pp[1] 붓기
				int i = pp[0], j = pp[1], k = pp[2];
				if (ws[i] != 0 && ws[j] < bottleSize[j]) {
					int diff = Math.min(bottleSize[j] - ws[j], ws[i]);
					nws[i] = ws[i] - diff;
					nws[j] = ws[j] + diff;
					nws[k] = ws[k];
					if (d[nws[0]][nws[1]] == -1)
						stack.push(new int[] {nws[0], nws[1]});
				}
			}
		}
		
		int cnt = 0;
		for (int i = d[0].length - 1; i >= 0; i--) {
			if (d[0][i] != -1)
				sb.append(d[0][i]).append('\n');
		}
		System.out.println(sb);

	}

}