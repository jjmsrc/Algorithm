import java.util.*;
import java.io.*;

public class Main {
	
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		int ans = hanoi(1, 3, 2, N);
		System.out.println(ans);
		System.out.println(sb.toString());
	}
	
	private static int hanoi(int from, int to, int other, int n) {
		
		if (n == 1) {
			sb.append(from + " " + to).append("\n");
			return 1;
		}
		
		int res = 0;
		
		res += hanoi(from, other, to, n - 1);
		res += 1;
		sb.append(from + " " + to).append("\n");
		res += hanoi(other, to, from, n - 1);
		
		return res;
	}
}