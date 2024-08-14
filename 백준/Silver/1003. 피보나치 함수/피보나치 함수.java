import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] cntMem = new int[41][2];
		
		cntMem[0][0] = cntMem[1][1] = 1;
		
		for (int i = 2; i <= 40; i++) {
			cntMem[i][0] = cntMem[i - 1][0] + cntMem[i - 2][0];
			cntMem[i][1] = cntMem[i - 1][1] + cntMem[i - 2][1];
		}
		
		int nTests = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < nTests; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(cntMem[n][0]).append(" ").append(cntMem[n][1]).append("\n");
		}
		
		System.out.println(sb);
		
		
	}

}