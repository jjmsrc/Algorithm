import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int[] budgetReq = new int[N];
		int maxReq = 0;
		for (int i = 0; i < N; i++) {
			budgetReq[i] = Integer.parseInt(st.nextToken());
			if (maxReq < budgetReq[i])
				maxReq = budgetReq[i];
		}
		
		int totalBudget = Integer.parseInt(br.readLine());
		
		int left = 0;
		int right = totalBudget;
		int mid = 0;
		int maxBudget = 0;
		int sum = totalBudget;
		
		while(left < right) {
			mid = (left + right) / 2;
			for (int i = 0; i < N; i++) {
				sum -= Math.min(mid, budgetReq[i]);
			}
			if (sum >= 0) {
				maxBudget = mid;
				left = mid + 1;
			} else {
				right = mid;
			}
			sum = totalBudget;
		}
		
		System.out.println(Math.min(maxReq, maxBudget));
	}
}