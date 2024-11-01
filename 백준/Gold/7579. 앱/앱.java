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
		
		int totalMemSum = 0;
		int totalCostSum = 0;
		int memLimit = 0;
		
		for (int i = 0; i < nApps; i++) {
			totalMemSum += appMems[i];
			totalCostSum += appCosts[i];
		}
		memLimit = totalMemSum - memSize;
		
		int[] c = new int[memLimit + 1]; //  sum of costs by sum of memories
		
		for (int i = 0; i < nApps; i++) {
			int m = appMems[i];
			for (int mi = memLimit; mi >= m; mi--) {
				c[mi] = Math.max(c[mi], c[mi - m] + appCosts[i]);
			}
		}
		
		return totalCostSum - c[memLimit];
	}
}