import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(seq);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			sb.append(binarySearch(seq, tmp) >= 0 ? 1 : 0).append("\n");
		}

		System.out.println(sb);
	}
	
	static int binarySearch(int[] arr, int n) {
		
		int l = 0;
		int r = arr.length - 1;
		int mid = (l + r) / 2;
		while(arr[mid] != n && l <= r) {
			if (arr[mid] < n) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
			mid = (l + r) / 2;
		}
		
		return arr[mid] == n ? mid : -1;
	}


}