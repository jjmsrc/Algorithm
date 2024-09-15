import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int[] cumsum = new int[n + 1];
		int left = 0;
		int right = 0;
		int minLength = n;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < cumsum.length; i++) {
			cumsum[i] = cumsum[i - 1] + Integer.parseInt(st.nextToken());
			if (right == 0 && cumsum[i] >= s)
				right = i;
		}
		
		if (right == 0) {
			minLength = 0;
		} else {
			minLength = right;
			while(right <= n) {
				while(cumsum[right] - cumsum[left + 1] >= s) {
					left++;
				}
				if (minLength > right - left)
					minLength = right - left;
				right++;
			}
		}
		
		System.out.println(minLength);
		
	}

}