import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String[] seq = new String[13];
		seq[0] = "-";
		for (int i = 1; i < seq.length; i++) {
			seq[i] = seq[i - 1] + " ".repeat((int)Math.pow(3, i - 1)) + seq[i - 1];
		}
		
		String line;
		while((line = br.readLine()) != null && !line.isEmpty()) {
			int n = Integer.parseInt(line);
			sb.append(seq[n]).append('\n');
		}
		
		System.out.println(sb);
		
	}

}