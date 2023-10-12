import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
	
	private static int[] fiboMem;
	private static Map<BigInteger, Long> fiboMap;
	private final static BigInteger B2 = new BigInteger("2");
	private final static long DIVD = 1_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		BigInteger N = new BigInteger(st.nextToken());
		
		if (N.equals(BigInteger.ZERO)) {
			System.out.println(0);
			return;
		}
		
		fiboMem = new int[11];
		fiboMem[1] = 1;
		for (int i = 2; i <= 10; i++) {
			fiboMem[i] = fiboMem[i - 1] + fiboMem[i - 2];
		}
		
		fiboMap = new HashMap<BigInteger, Long>();
		
		System.out.println(fibo(N));
	}
	
	private static long fibo(BigInteger n) {
		if (n.compareTo(BigInteger.TEN) <= 0) return fiboMem[n.intValue()];
		
		Long a = fiboMap.get(n);
		if (a != null)
			return a;
		
		if (n.remainder(B2).equals(BigInteger.ZERO)) {
			BigInteger bn1 = n.divide(B2);
			BigInteger bn2 = bn1.subtract(BigInteger.ONE);
			long ln1 = fibo(bn1);
			long ln2 = fibo(bn2);
			long ans = ((ln1 * ln1) % DIVD + (2 * ln1 * ln2) % DIVD) % DIVD;
			fiboMap.put(n, ans);
			return ans;
		} else {
			BigInteger bn2 = n.divide(B2);
			BigInteger bn1 = bn2.add(BigInteger.ONE);
			long ln1 = fibo(bn1);
			long ln2 = fibo(bn2);
			long ans = ((ln1 * ln1) % DIVD + (ln2 * ln2) % DIVD) % DIVD;
			fiboMap.put(n, ans);
			return ans;
		}
	}

}