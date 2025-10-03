import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		
		System.out.println(eulersTotient(n));
	}
	
	private static long eulersTotient(long n) {

		long cnt = n;
		long dn = n;
		
		for (int i = 2, en = (int)Math.sqrt(n) + 1; i < en; i++) {
			if (dn % i != 0) continue;
			while(dn % i == 0) dn = dn / i;
			cnt -= cnt / i;
		}
		
		if (dn > 1) cnt -= cnt / dn;
		
		return cnt;
	}

}