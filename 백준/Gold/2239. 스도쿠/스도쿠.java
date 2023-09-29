import java.io.*;
import java.util.*;

/*
 * 1. 문제 해석
 * 
 * 2. 해결 전략
 * 
 * 3. 주의점
 * 
 * */

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		char[][] map = new char[9][];
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
		int cntZeros = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == '0') {
					cntZeros++;
				}
			}
		}

		solve(map, cntZeros);
		
		print(map);
	}

	private static boolean solve(char[][] map, int cntZeros) {
		
		if (cntZeros == 0)
			return true;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == '0') {
					boolean[] nums = checkNums(i, j, map);
					for (int ip = 1; ip <= 9; ip++) {
						if (!nums[ip]) {
							map[i][j] = (char)(ip + '0');
							if (solve(map, cntZeros - 1))
								return true;
							map[i][j] = '0';
						}
					}
					return false;
				}
			}
		}
		
		return false;
	}

	private static boolean[] checkNums(int i, int j, char[][] map) {
		
		boolean[] nums = new boolean[10];
		
		int si = i / 3 * 3;
		int sj = j / 3 * 3;
		
		char c = '0';
		for (int k = 0; k < 9; k++) {
			c = map[i][k];
			if (c != '0') 
				nums[c - '0'] = true;
			c = map[k][j];
			if (c != '0') 
				nums[c - '0'] = true;
			c = map[si + k / 3][sj + k % 3];
			if (c != '0') 
				nums[c - '0'] = true;
		}
		
		return nums;
	}

	private static void print(char[][] ans) {
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

}