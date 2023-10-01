import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] sumArray = new int[2][n];
		
		sumArray[0][0] = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < n; i++) {
			int ci = i & 1;
			int pi = ci ^ 1;
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				sumArray[ci][j] = Integer.parseInt(st.nextToken());
			}
			
			sumArray[ci][0] += sumArray[pi][0];
			for (int j = 1; j < i; j++) {
				sumArray[ci][j] += Math.max(sumArray[pi][j - 1], sumArray[pi][j]);
			}
			sumArray[ci][i] += sumArray[pi][i - 1];
		}
		
		int maxVal = 0;
		for (int num : sumArray[(n - 1) & 1]) {
			if (maxVal < num)
				maxVal = num;
		}
		
		System.out.println(maxVal);

	}

}