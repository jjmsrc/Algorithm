import java.io.*;
import java.util.*;

public class Main {
	
	private static int[] gates;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nGate, nPlane;
		
		nGate = Integer.parseInt(br.readLine());
		nPlane = Integer.parseInt(br.readLine());
		gates = new int[nGate + 1];
		
		int cntSuccess = 0;
		for (int i = 0; i < nPlane; i++) {
			int gi = Integer.parseInt(br.readLine());
			if (putPlane(gi)) cntSuccess++;
			else break;
		}
		
		System.out.println(cntSuccess);

	}

	private static boolean putPlane(int gi) {
		
		for (int i = gi; i >= 1;) {
			if (gates[i] == 0) {
				gates[gi] = Math.max(1, gi - i);
				gates[i] = 1;
				return true;
			} else {
				i -= gates[i];
			}
		}
		
		return false;
	}

}