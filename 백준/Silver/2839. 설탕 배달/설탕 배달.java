import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());

		int min = 5000;
		for (int i5 = N / 5; i5 >= 0; i5--) {
			int remain = N - i5 * 5;
			if (remain % 3 == 0) 
				min = Math.min(min, remain / 3 + i5);
		}
		
		System.out.println(min != 5000 ? min : -1);

	}

}