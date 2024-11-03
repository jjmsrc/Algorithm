import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int nNums = Integer.parseInt(br.readLine());
		int[] nums = new int[nNums + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= nNums; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int nQuestions = Integer.parseInt(br.readLine());
		
		boolean[][] mem = new boolean[nNums + 1][nNums + 1];
		findPalindroms(nums, mem);
		
		for (int i = 0; i < nQuestions; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(mem[s][e] ? 1 : 0).append('\n');
		}
		
		System.out.println(sb);
		
	}

	private static void findPalindroms(int[] nums, boolean[][] mem) {
		for (int i = 1; i < mem.length; i++) {
			mem[i][i] = true;
			int l = i - 1;
			int m = i;
			int r = i + 1;
			while(l >= 1 && r < nums.length) {
				if (nums[l] == nums[r]) {
					mem[l][r] = true;
				} else {
					break;
				}
				l--;
				r++;
			}
			l = i - 1;
			while(l >= 1 && m < nums.length) {
				if (nums[l] == nums[m]) {
					mem[l][m] = true;
				} else {
					break;
				}
				l--;
				m++;
			}
		}
	}

}