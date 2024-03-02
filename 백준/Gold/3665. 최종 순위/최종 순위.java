import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	private static final int MAX_N = 500;

	static int N;
	static int[] bufRanks = new int[MAX_N + 1];
	static int[][] adjMat = new int[MAX_N + 1][MAX_N + 1];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {

			initTest();

			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				bufRanks[j] = Integer.parseInt(st.nextToken());
			}

			// 작년 순위 정보 초기화
			for (int qi = 2; qi <= N; qi++) {
				for (int pi = 1; pi < qi; pi++) {
					int p = bufRanks[pi];
					int q = bufRanks[qi];
					adjMat[p][q] = 1; // 연결
					adjMat[0][q]++; // inner edge 개수 증가
				}
			}

			// 바뀐 순위 정보 업데이트
			int M = Integer.parseInt(br.readLine());
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int t1 = Integer.parseInt(st.nextToken());
				int t2 = Integer.parseInt(st.nextToken());
				exchangeRank(t1, t2);
			}

			String ans = solve();
			sb.append(ans).append("\n");
		}

		System.out.println(sb);

	}

	private static void initTest() {
		for (int i = 0; i < adjMat.length; i++) {
			Arrays.fill(adjMat[i], 0);
		}
		Arrays.fill(bufRanks, 0);
	}

	private static void exchangeRank(int t1, int t2) {
		if (adjMat[t1][t2] == 1) {
			adjMat[0][t1] += 1;
			adjMat[0][t2] -= 1;
		} else {
			adjMat[0][t1] -= 1;
			adjMat[0][t2] += 1;
		}
		adjMat[t1][t2] = adjMat[t1][t2] ^ 1;
		adjMat[t2][t1] = adjMat[t2][t1] ^ 1;
	}

	private static String solve() {

		boolean isAmb = false;
		ArrayList<Integer> list = new ArrayList<Integer>();

		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 1; i <= N; i++) {
			if (adjMat[0][i] == 0) {
				queue.offer(i);
				if (queue.size() > 1)
					isAmb = true;
			}
		}
		
		while(!queue.isEmpty()) {
			int t = queue.poll();
			list.add(t);
			
			for (int i = 1; i <= N; i++) {
				if (adjMat[t][i] == 1) {
					adjMat[t][i] = 0;
					if (--adjMat[0][i] == 0) {
						queue.offer(i);
						if (queue.size() > 1)
							isAmb = true;
					}
				}
			}
		}
		
		if (list.size() < N) {
			return "IMPOSSIBLE";
		}
		
		if (isAmb) {
			return "?";
		}

		StringBuilder sb = new StringBuilder();
		for(int t: list)
			sb.append(t).append(" ");
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

}