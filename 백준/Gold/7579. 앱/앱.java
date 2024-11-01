import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int nApps = Integer.parseInt(st.nextToken());
		int memSize = Integer.parseInt(st.nextToken());
		
		int[] appMems = new int[nApps];
		int[] appCosts = new int[nApps];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nApps; i++) {
			appMems[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nApps; i++) {
			appCosts[i] = Integer.parseInt(st.nextToken());
		}
		
		int minCostSum = solve(nApps, memSize, appMems, appCosts);
		
		System.out.println(minCostSum);
		
	}

	private static int solve(int nApps, int memSize, int[] appMems, int[] appCosts) {
		
		int totalCost = 0;
		for (int i = 0; i < nApps; i++) {
			totalCost += appCosts[i];
		}
		
		int[] m = new int[totalCost + 1]; //  sum of memories by sum of costs
		
		for (int i = 0; i < nApps; i++) {
			int c = appCosts[i];
			for (int ci = totalCost; ci >= c; ci--) {
				m[ci] = Math.max(m[ci], m[ci - c] + appMems[i]);
			}
		}
		
		for (int i = 0; i <= totalCost; i++) {
			if (m[i] >= memSize) {
				return i;
			}
		}
		
		return 0;
	}
}