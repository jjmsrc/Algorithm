import java.io.*;
import java.util.*;

public class Main {

	static int N, R, C;

	private static int recursiveFunc(int r, int c, int size) {

		if (R < r || R >= r + size || C < c || C >= c + size)
			return size * size;

		if (size == 2) {
			int cnt = 0;
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (r + i == R && c + j == C)
						return cnt;
					cnt++;
				}
			}
		}

		int half = size >> 1;
		int max = half * half;

		int cnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				int tmp = recursiveFunc(r + half * i, c + half * j, half);
				if (tmp == max)
					cnt += tmp;
				else
					return cnt + tmp;
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		int ans = recursiveFunc(0, 0, 1 << N);

		sb.append(ans);

		System.out.println(sb);

	}

}