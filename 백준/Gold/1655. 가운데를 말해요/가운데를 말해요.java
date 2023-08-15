import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> left = new PriorityQueue<Integer>((a, b) -> b - a);
		PriorityQueue<Integer> right = new PriorityQueue<Integer>();
		left.add(-10001);
		right.add(10001);
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (right.peek() <= num)
				right.add(num);
			else
				left.add(num);
			
			if (left.size() < right.size()) {
				if (left.size() + 1 < right.size()) {
					left.add(right.poll());
					sb.append(Math.min(left.peek(), right.peek()));
				}
				else
					sb.append(right.peek());
			}
			else if (left.size() == right.size()) {
				sb.append(Math.min(left.peek(), right.peek()));
			} else {
				if (left.size() > right.size() + 1) {
					right.add(left.poll());
					sb.append(Math.min(left.peek(), right.peek()));
				}
				else
					sb.append(left.peek());
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

}