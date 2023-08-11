import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static void swap(int[] p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}
	
	private static boolean np(int[] p) {
		int N = p.length;
		int i = N - 1;
		
		while(i > 0 && p[i - 1] >= p[i])
			--i;
		
		if(i == 0)
			return false;
		
		int j = N - 1;
		while(p[i - 1] >= p[j])
			--j;
		
		swap(p, i - 1, j);
		
		int k = N - 1;
		while(i < k)
			swap(p, i++, k--);
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int N = 9;
		int[] inputs = new int[N];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
		}
		
		int[] p = {0, 0, 1, 1, 1, 1, 1, 1, 1};
		
		do {
			int sum = 0;
			for (int i = 0; i < p.length; i++) {
				if(p[i] == 1)
					sum += inputs[i];
			}
			if (sum == 100)
				break;
		} while(np(p));
		
		for (int i = 0; i < p.length; i++) {
			if(p[i] == 1)
				sb.append(inputs[i]).append("\n");
		}
		
		System.out.println(sb);

	}

}