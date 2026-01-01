import java.io.*;
import java.util.*;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	
	private static int[][] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		tree = new int[27][2];
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			int p = charToTreeIdx(line.charAt(0));
			char c1 = line.charAt(2);
			char c2 = line.charAt(4);
			if (c1 != '.') tree[p][0] = charToTreeIdx(c1);
			if (c2 != '.') tree[p][1] = charToTreeIdx(c2);
		}
		
		preOrder(1);
		sb.append('\n');
		inOrder(1);
		sb.append('\n');
		postOrder(1);
		
		System.out.println(sb);

	}
	
	private static void preOrder(int i) {
		if (i == 0) return;
		sb.append(treeIdxToChar(i));
		preOrder(tree[i][0]);
		preOrder(tree[i][1]);
	}
	
	private static void inOrder(int i) {
		if (i == 0) return;
		inOrder(tree[i][0]);
		sb.append(treeIdxToChar(i));
		inOrder(tree[i][1]);
	}

	private static void postOrder(int i) {
		if (i == 0) return;
		postOrder(tree[i][0]);
		postOrder(tree[i][1]);
		sb.append(treeIdxToChar(i));
	}
	
	private static int charToTreeIdx(char c) {
		return c - 'A' + 1;
	}
	
	private static char treeIdxToChar(int idx) {
		return (char)(idx + 'A' - 1);
	}

}