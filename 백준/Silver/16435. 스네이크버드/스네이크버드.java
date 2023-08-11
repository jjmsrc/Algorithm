import java.util.*;
import java.io.*;


public class Main {
	

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue pq = new PriorityQueue();

		// 선언 및 초기화
		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		int length = Integer.parseInt(st.nextToken());

		int[] apples = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < apples.length; i++) {
			apples[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(apples);
		
		for (int apple : apples) {
			if(apple <= length)
				length++;
		}

		System.out.println(length);
	}

}