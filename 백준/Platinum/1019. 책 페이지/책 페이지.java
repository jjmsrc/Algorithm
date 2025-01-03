import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static long[][][] cntNums = new long[11][10][10];
	private static boolean[][] isCounted = new boolean[11][10];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String number = br.readLine();
		
		long[] ans = count(number);
		
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
		System.out.println();
		
	}
	
	private static long[] count(String number) {
		long[] fixedNums = new long[10];
		long[] cnt = new long[10];
		
		for (int i = 1; i < number.length(); i++) {
			for (int j = 1; j < 10; j++) {
				combineArrays(cnt, count(i, j));
			}
		}
		for (int i = 1; i < number.charAt(0) - '0'; i++) {
			combineArrays(cnt, count(number.length(), i));
		}
		
		if (number.length() == 1)
			cnt[number.charAt(0) - '0']++;
		
		// 999 일때
		for (int i = 1, len = number.length(), pos = len - 1; i < len; i++, pos--) {
			int currentVal = number.charAt(i - 1) - '0';
			int nextVal = number.charAt(i) - '0';
			if (pos == 1) nextVal++;
			fixedNums[currentVal]++;
			
			// 90x~98x까지, 990~999 까지
			for (int j = 0; j < nextVal; j++) {
				combineArrays(cnt, count(pos, j));
			}
			
			// fixedNums * cnt
			for (int j = 0; j < 10; j++) {
				cnt[j] += fixedNums[j] * (long)Math.pow(10, pos - 1) * nextVal;
			}
			
		}
		
		return cnt;
	}
	
	private static long[] count(int pos, int val) {
		
		// 만약 pos 3, val 1 이라면, 100 ~ 199 의 숫자를 센다.
		long[] ret = cntNums[pos][val];
		
		if (isCounted[pos][val]) {
			return ret;
		}
		isCounted[pos][val] = true;
		
		long mul = (long)Math.pow(10, pos - 1);
		
		// 100 ~ 199에서 백의 자리의 1의 개수
		ret[val] = mul;
		
		// 1000 ~ 1999 에서 첫번째 자리수 뺀 나머지 숫자 개수 세기
		// 100~999
		// 010~099
		// 001~009
		// 000
		// ...
		if (pos > 0) {
			for (int vi = 0; vi < 10; vi++) {
				combineArrays(ret, count(pos - 1, vi));
			}
		}
		
		return ret;
	}
	
	private static long combineArrays (long[] arr1, long[] arr2) {
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			cnt += arr2[i];
			arr1[i] += arr2[i];
		}
		return cnt;
	}
	
}