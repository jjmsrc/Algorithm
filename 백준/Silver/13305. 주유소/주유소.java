import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] dist = new int[N - 1];
		int[] oil = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < oil.length; i++) {
			oil[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < dist.length; i++) {
			min = Math.min(min, oil[i]);
			sum += min * dist[i];
		}
		
		sb.append(sum);

		System.out.println(sb);

	}

}