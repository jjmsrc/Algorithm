import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		final int a = Integer.parseInt(st.nextToken());
		final int b = Integer.parseInt(st.nextToken());
		final int c = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		long ans = 1;
		
		for (int i = b; i > 0; i >>= 1) {
			list.add((i & 1) == 1 ? a : 1);
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			ans = ((ans * ans) % c * list.get(i)) % c;
		}

		System.out.println(ans);

	}

}