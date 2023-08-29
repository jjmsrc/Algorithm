import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		BigInteger[] mem = new BigInteger[n + 1];
		mem[0] = mem[1] = new BigInteger("1");
		for (int i = 2; i <= n; i++) {
			mem[i] = mem[i - 1].add(mem[i - 2]);
		}
		
		System.out.println(mem[n].remainder(new BigInteger("10007")));
	}

}