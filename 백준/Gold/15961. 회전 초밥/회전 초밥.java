import java.io.*;
import java.util.*;


public class Main {

	static int N, d, k, coup; // 주어진 초밥 개수, 초밥 가짓수, 연속해서 먹는 수, 쿠폰 번호

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coup = Integer.parseInt(st.nextToken());
		
		int[] sushiBelt = new int[N];
		for (int i = 0; i < N; i++) {
			sushiBelt[i] = Integer.parseInt(br.readLine());
		}

		Set<Integer> sushiSet = new HashSet<Integer>();
		int[] sushiArray = new int[d + 1];
		for (int i = 0; i < k; i++) {
			sushiSet.add(sushiBelt[i]);
			sushiArray[sushiBelt[i]]++;
		}
		
		int ans = sushiSet.size() + (sushiSet.contains(coup) ? 0 : 1);
		for (int i = 0; i < sushiBelt.length; i++) {
			int s = sushiBelt[i];
			if (--sushiArray[s] == 0) {
				sushiSet.remove(s);
			}
			int ns = sushiBelt[(i + k) % N];
			sushiArray[ns]++;
			sushiSet.add(ns);
			ans = Math.max(ans, sushiSet.size() + (sushiArray[coup] > 0 ? 0 : 1));
		}
		
		sb.append(ans);
		System.out.print(sb);

	}

}