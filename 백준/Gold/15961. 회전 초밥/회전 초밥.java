import java.io.*;
import java.util.*;

/*
 * 1. 문제 해석
 * 		회전 초밥집에서 최대한 다양한 초밥을 먹으려고 할 때 
 * 		손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구한다.
 * 
 * 2. 해결 전략
 * 		Set 자료구조를 이용해 초밥의 종류를 삽입, 삭제하면서 Set 의 개수를 확인한다.
 * 
 * 3. 주의점
 * 
 * */

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

		int cntSushiKind = 0;
		int[] sushiCntArray = new int[d + 1];
		for (int i = 0; i < k; i++) {
			if (sushiCntArray[sushiBelt[i]] == 0) 
				cntSushiKind++;
			sushiCntArray[sushiBelt[i]]++;
		}
		
		int ans = cntSushiKind + (sushiCntArray[coup] > 0 ? 0 : 1);
		for (int i = 0, j = k; i < sushiBelt.length; i++, j = (j < N - 1 ? j + 1 : 0)) {
			if (--sushiCntArray[sushiBelt[i]] == 0) 
				cntSushiKind--;
			if (sushiCntArray[sushiBelt[j]]++ == 0) 
				cntSushiKind++;
			ans = Math.max(ans, cntSushiKind + (sushiCntArray[coup] > 0 ? 0 : 1));
		}
		
		sb.append(ans);
		System.out.print(sb);

	}

}