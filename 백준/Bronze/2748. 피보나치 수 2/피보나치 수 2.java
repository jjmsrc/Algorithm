import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		if (N == 0) {
			System.out.println(0);
			return;
		}
		
		long[] fibo = new long[N + 1];
		fibo[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		}
		
		System.out.println(fibo[N]);
	}

}