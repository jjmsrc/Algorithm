import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] mem = new int[n + 1];
		
		mem[0] = mem[1] = 1;
		
		for (int i = 2; i <= n; i++) {
			mem[i] = (mem[i - 1] + mem[i - 2] * 2) % 10_007;
		}
		
		System.out.println(mem[n]);
		
	}

}