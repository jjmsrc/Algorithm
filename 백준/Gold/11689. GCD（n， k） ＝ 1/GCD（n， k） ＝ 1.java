import java.io.*;
import java.util.*;

public class Main {
	
	private static int MAX_SQRT = 1_000_000;
	
	private static List<Integer> primes;
	private static Set<Integer> primeSet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		
		initPrimes();
		
		System.out.println(countGCDEqualsOne(n));
		
	}
	
	private static void initPrimes() {
		
		int n = MAX_SQRT + 1;
		
		boolean[] isPrimeNumber = new boolean[n];
		
		primes = new ArrayList<>();
		primeSet = new HashSet<>();
		
		for (int i = 2; i < n; i++) {
			isPrimeNumber[i] = true;
		}
		
		for (int i = 2; i < n; i++) {
			if (!isPrimeNumber[i]) continue;
			for (int j = i * 2; j < n; j += i) {
				isPrimeNumber[j] = false;
			}
			primes.add(i);
			primeSet.add(i);
		}
	}
	
	private static long countGCDEqualsOne(long n) {
		
		if (n < MAX_SQRT && primeSet.contains((int)n)) {
			return n - 1;
		}
		
		List<Integer> subPrimes = new ArrayList<>();
		
		long cnt = Math.max(1, n - 1); // n을 제외
		long dn = n;
		
		for (int p : primes) {
			if (p > n) break;
			if (dn % p != 0) continue;
			while(dn % p == 0) dn = dn / p;
			long q = n / p;
			long qCnt = count(q - 1, subPrimes.size(), subPrimes);
			cnt -= qCnt; // p의 배수의 해당하는 경우를 제외
			subPrimes.add(p);
		}
		
		if (dn != n && dn > 1) { // dn이 1,000,000 보다 큰 소수 일 경우
			cnt -= count(n / dn, subPrimes.size(), subPrimes);
		}
		
		return cnt;
	}
	
	private static long count(long n, int len, List<Integer> ps) {
		
		long cnt = n;
		
		for (int i = 0; i < len; i++) {
			int p = ps.get(i);
			if (p > n) break;
			
			long q = n / p;
			long qCnt = count(q, i, ps);
			
			cnt -= qCnt; // p의 배수의 해당하는 경우를 제외
			
		}
		
		return cnt;
	}

}