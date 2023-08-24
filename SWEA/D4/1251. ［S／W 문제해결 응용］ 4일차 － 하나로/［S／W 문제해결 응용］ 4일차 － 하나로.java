import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static BigDecimal[] xs, ys;
	static BigDecimal taxRate;
	static BigDecimal[][] dist;

	static class Pair { // 우선순위 큐에 사용하는 클래스
		int v;
		BigDecimal d;

		public Pair(int v, BigDecimal d) {
			this.v = v;
			this.d = d;
		}
	}

	static BigDecimal calcDist(int i, int j) { // 거리 제곱 계산
		
		if (dist[i][j] != null)
			return dist[i][j];
		
		if (i == j) {
			return dist[i][j] = new BigDecimal(0);
		}

		BigDecimal dx = xs[i].subtract(xs[j]);
		BigDecimal dy = ys[i].subtract(ys[j]);

		return dist[i][j] = dist[j][i] = ((dx.multiply(dx)).add(dy.multiply(dy))).multiply(taxRate);
	}

	static BigDecimal solve() {

		// 초기화
		boolean[] visit = new boolean[N];
		BigDecimal[] minDist = new BigDecimal[N];
		for (int i = 0; i < minDist.length; i++) {
			minDist[i] = new BigDecimal("1000000000000");
		}
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> a.d.compareTo(b.d));
		
		BigDecimal ans = new BigDecimal(0);
		minDist[0] = new BigDecimal(0);
		pq.offer(new Pair(0, minDist[0]));
		while (!pq.isEmpty()) {
			
			Pair data = pq.poll();
			
			if (visit[data.v])
				continue;
			
			visit[data.v] = true;
			ans = ans.add(data.d);
			
			for (int i = 0; i < N; i++) {
				if (!visit[i] && minDist[i].compareTo(calcDist(data.v, i)) > 0) {
					minDist[i] = calcDist(data.v, i);
					pq.offer(new Pair(i, minDist[i]));
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {

			BigDecimal ans = null;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			xs = new BigDecimal[N];
			ys = new BigDecimal[N];
			dist = new BigDecimal[N][N];

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				xs[j] = new BigDecimal(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ys[j] = new BigDecimal(st.nextToken());
			}

			taxRate = new BigDecimal(br.readLine());

			ans = solve();

			sb.append("#" + i + " " + ans.setScale(0, RoundingMode.HALF_EVEN) + '\n');

		}

		System.out.println(sb.toString());

	}

}