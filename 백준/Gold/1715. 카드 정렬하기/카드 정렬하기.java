import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		int sum = 0;
		for (int i = 1; i < N; i++) {
			int a = pq.poll();
			int b = pq.poll();
			int c = (a + b);
			sum += c;
			pq.offer(c);
		}
		
		System.out.println(sum);
	}
}