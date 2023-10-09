import java.io.*;
import java.util.*;

public class Main {

	static int[] dxs = { 1, -1, 0, 0 };
	static int[] dys = { 0, 0, 1, -1 };

	static int N, M;
	static int[][] mem;
	static int[][] mat;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		mat = new int[M][N];
		mem = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				mem[i][j] = -1;
			}
		}

		mem[0][0] = 1;

		int ans = cntCases(M - 1, N - 1);

		System.out.println(ans);

	}

	static int cntCases(int x, int y) {

		if (mem[x][y] != -1) {
			return mem[x][y];
		}

		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			int nx = x + dxs[i];
			int ny = y + dys[i];
			if (nx >= 0 && nx < M && ny >= 0 && ny < N && mat[x][y] < mat[nx][ny]) {
				cnt += cntCases(nx, ny);
			}
		}

		return mem[x][y] = cnt;

	}

}