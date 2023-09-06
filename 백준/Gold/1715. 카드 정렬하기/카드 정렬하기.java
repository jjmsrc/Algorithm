import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		int result=0;
		while(!pq.isEmpty()) {
			int sum=0;
			sum+=pq.poll();
			if(!pq.isEmpty()) {
				sum+=pq.poll();
			}else {
				break;
			}
			result+=sum;
			pq.add(sum);
		}
		System.out.println(result);
	}
}