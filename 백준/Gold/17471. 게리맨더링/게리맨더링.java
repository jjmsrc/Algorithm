import java.io.*;
import java.util.*;

public class Main {

	static int N, nPopul[], adjMat[][]; // 선거구 개수
	static int[] parents;

	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	private static int findMinDiff(int subset) {

		make();

		// 조합과 union finding
		for (int i = 0; i < N - 1; i++) {
			int p = 1 << i;
			for (int j = i + 1; j < N; j++) {
				int q = 1 << j;
				if (((subset & p) == 0 && (subset & q) == 0) || 
						((subset & p) > 0 && (subset & q) > 0)) {
					if (adjMat[i][j] == 1) {
						union(i, j);
					}
				}
			}
		}
		
		int aRoot = find(0);
		int bRoot = -1;
		int aSum = nPopul[0];
		int bSum = 0;
		for (int i = 1; i < N; i++) {
			int tmpRoot = find(i);
			if (aRoot == tmpRoot) {
				aSum += nPopul[i];
			} else {
				if (bRoot == -1)
					bRoot = tmpRoot;
				if (bRoot != tmpRoot)
					return 1000;
				bSum += nPopul[i];
			}
		}

		return Math.abs(aSum - bSum);
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		nPopul = new int[N];
		for (int i = 0; i < nPopul.length; i++) {
			nPopul[i] = Integer.parseInt(st.nextToken());
		}

		adjMat = new int[N][N];
		for (int from = 0; from < N; from++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjMat[from][to - 1] = 1;
			}
		}

		int ans = 1000;
		for (int i = 1; i < (1 << N); i++) { // 부분 집합
			int tmp = findMinDiff(i);
			if (ans > tmp)
				ans = tmp;
		}

		System.out.print(ans == 1000 ? -1 : ans);

	}

}