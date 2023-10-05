import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		
		String text = br.readLine();
		String pattern = br.readLine();
		
		int pSize = pattern.length();
		int tSize = text.length();
		
		int[] fail = new int[pSize];
		for (int i = 1, j = 0; i < pSize; i++) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			if (pattern.charAt(i) == pattern.charAt(j))
				fail[i] = ++j;
			else
				fail[i] = 0;
		}
		
		for (int i = 0, j = 0; i < tSize; i++) {
			while(j > 0 && text.charAt(i) != pattern.charAt(j))
				j = fail[j - 1];
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
				if (j == pSize) {
					indexList.add(i - pSize + 2);
					j = fail[j - 1];
				}
			}
		}
		
		sb.append(indexList.size() + "\n");
		for (int i : indexList) {
			sb.append(i).append(" ");
		}
		sb.append("\n");
		
		System.out.println(sb);
		
	}

}