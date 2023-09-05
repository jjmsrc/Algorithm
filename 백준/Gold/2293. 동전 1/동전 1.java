import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		ArrayList<Integer> coins = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			int c = Integer.parseInt(br.readLine());
			if (c <= k) {
				coins.add(c);
			}
		}
		
		n = coins.size();
		
		int[][] mem = new int[n][k + 1];
		for (int i = 0; i < n; i++) {
			mem[i][coins.get(i)] = 1;
		}
		
		// 1. 머릿속으로 적당히 생각하며 풀기
		// 2. 경우의수 하나씩 적어가며 풀기
		
		// -> n번째 코인이 항상 최대 크기의 코인으로 가지고 있는 경우
		
		for (int m = 1; m <= k; m++) {
			for (int ic = 0; ic < n; ic++) {
				int c = coins.get(ic);
				if (m - c >= 0) {
					for (int i = 0; i <= ic; i++) {
						mem[ic][m] += mem[i][m - c];
					}
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += mem[i][k];
		}
		
		System.out.println(sum);

	}

}