import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] glass = new int[N];
		for (int i = 0; i < N; i++) {
			glass[i] = Integer.parseInt(br.readLine());
		}
		
		int[][] mem = new int[N + 2][3];
		
		for (int i = 2; i < mem.length; i++) {
			mem[i][0] = Math.max(Math.max(mem[i - 1][0], mem[i - 1][1]), mem[i - 1][2]);
			mem[i][1] = mem[i - 1][0] + glass[i - 2];
			mem[i][2] = mem[i - 1][1] + glass[i - 2];
		}
		
		System.out.println(Math.max(Math.max(mem[N + 1][0], mem[N + 1][1]), mem[N + 1][2]));
		
	}

}