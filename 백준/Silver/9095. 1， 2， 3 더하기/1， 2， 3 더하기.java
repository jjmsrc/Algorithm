import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int[] mem = new int[11];
		mem[1] = 1;
		mem[2] = 2;
		mem[3] = 4;
		
		for (int i = 4; i < mem.length; i++) {
			mem[i] = mem[i - 1] + mem[i - 2] + mem[i - 3]; 
		}
		
		for (int i = 0; i < T; i++) {
			int k = sc.nextInt();
			System.out.println(mem[k]);
		}
	}

}