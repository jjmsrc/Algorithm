import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long ans = 0;
		
		Map<Integer, Integer> partialSumMapA = new HashMap<Integer, Integer>();

		int T = Integer.parseInt(br.readLine());

		int N = Integer.parseInt(br.readLine());
		int[] prefixSumA = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(st.nextToken());
			prefixSumA[i] = prefixSumA[i - 1] + n;
			for (int j = 0; j < i; j++) {
				int sum = prefixSumA[i] - prefixSumA[j];
				int cnt = partialSumMapA.getOrDefault(sum, 0);
				partialSumMapA.put(sum, cnt + 1);
			}
		}

		int M = Integer.parseInt(br.readLine());
		int[] prefixSumB = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			int n = Integer.parseInt(st.nextToken());
			prefixSumB[i] = prefixSumB[i - 1] + n;
			for (int j = 0; j < i; j++) {
				int sumB = prefixSumB[i] - prefixSumB[j];
				int sumA = T - sumB;
				int cnt = partialSumMapA.getOrDefault(sumA, 0);
				ans = ans + cnt;
			}
		}

		System.out.println(ans);
	}

}