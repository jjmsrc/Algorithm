import java.io.*;
import java.util.*;


public class Main {
	
	static StringBuilder sb;
	static int[][] grid;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // 적어도 M보다는 크게 잘라가야함.
		
		long[] woods = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			woods[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(woods); // 정렬
		
		long sum = 0;
		long cut = woods[N - 1];
		for (int i = N - 1, cnt = 1; i >= 0; i--, cnt++) { // 내림차순으로 탐색
			// 동일한 키의 나무가 앞에 몇 개 있는지 확인
			while(i > 0 && woods[i] == woods[i - 1]) {
				i--;
				cnt++;
			}
			// 자를 길이 결정, 현재 나무길이 c 다음으로 작은 나무길이 v라고 하면 (c - v) * cnt을 더해서 비교함.
			if (i > 0 && sum + (woods[i] - woods[i - 1]) * cnt < M) {
				sum += (woods[i] - woods[i - 1]) * cnt;
				cut = woods[i - 1];
			} else {
				long diff = M - sum;
				cut -= (diff + cnt - 1) / cnt;
				break;
			}
		}
		
		System.out.print(cut);

	}

}