import java.io.*;
import java.util.*;


public class Main {

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void swap(int[] p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}

	static boolean np(int[] p) {
		int N = p.length;
		int i = N - 1;

		while (i > 0 && p[i - 1] >= p[i])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (p[i - 1] >= p[j])
			--j;

		swap(p, i - 1, j);
		
		int k = N - 1;
		while(i < k)
			swap(p, i++, k--);

		return true;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());

		List<Pair> listH = new ArrayList<>();
		List<Pair> listC = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == 1)
					listH.add(new Pair(i, j));
				else if (val == 2)
					listC.add(new Pair(i, j));
			}
		}

		int[][] chickenDist = new int[listH.size()][listC.size()];

		for (int i = 0; i < listH.size(); i++) {
			for (int j = 0; j < listC.size(); j++) {
				chickenDist[i][j] = Math.abs(listH.get(i).x - listC.get(j).x)
						+ Math.abs(listH.get(i).y - listC.get(j).y);
			}
		}
		
		int[] p = new int[listC.size()];
		for (int i = 0; i < M; i++) {
			p[listC.size() - 1 - i] = 1;
		}
		
		int ans = Integer.MAX_VALUE;
		do {
			int sum = 0;
			for (int i = 0; i < listH.size(); i++) {
				int minDist = Integer.MAX_VALUE;
				for (int j = 0; j < listC.size(); j++) {
					if (p[j] == 0)
						continue;
					minDist = Math.min(minDist, chickenDist[i][j]);
				}
				sum += minDist;
			}
			ans = Math.min(ans, sum);
		} while(np(p));

		System.out.println(ans);

	}

}