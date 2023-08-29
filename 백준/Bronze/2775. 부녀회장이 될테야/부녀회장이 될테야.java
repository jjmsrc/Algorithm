import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			
			int[][] mem = new int[k + 1][n + 1];
			
			for (int j = 0; j < n + 1; j++) {
				mem[0][j] = j;
			}
			
			for (int ik = 1; ik <= k; ik++) {
				for (int in = 1; in <= n; in++) {
					mem[ik][in] = mem[ik - 1][in] + mem[ik][in - 1];
				}
			}
			
			System.out.println(mem[k][n]);
		}
		
	}

}