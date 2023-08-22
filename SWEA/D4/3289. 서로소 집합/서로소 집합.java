import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	static int N;
	static int parents[];

	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i; 
		}
	}

	private static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]); // Path Compression
	}
	
	private static boolean union(int a, int b) { 
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false; 
		parents[bRoot] = aRoot;
		return true;
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= T; i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			N = n + 1;
			parents = new int[N];
			make();
			sb.setLength(0);
			
			for (int j = 0; j < m; j++) {
				int op = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				if (op == 0) {
					union(a, b);
				} else {
					sb.append(find(a) == find(b) ? 1 : 0);
				}
			}
			System.out.println("#" + i + " " + sb);
		}
		sc.close();
	}

}