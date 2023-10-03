import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] cards = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] mem = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = i - 1; j >= 0; j--) {
				mem[i] = Math.max(mem[i], mem[j] + cards[i - j]);
			}
		}
		
		System.out.println(mem[N]);
		
	}

}