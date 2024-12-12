import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		int[] mem = new int[1_000_001];
		ArrayList<int[]> nums = new ArrayList<int[]>();
		int to = 0;
		
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		mem[to++] = num;
		nums.add(new int[] {num, 0});
		for (int i = 1; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			
			int idx = binarySearch(mem, num, 0, to);
			
			if (to == idx) {
				nums.add(new int[] {num, to});
				mem[to++] = num;
			} else {
				nums.add(new int[] {num, idx});
				mem[idx] = num;
			}

		}
		
		int[] seq = new int[to];
		
		sb.append(to).append('\n');
		for (int i = nums.size() - 1, j = to - 1; i >= 0; i--) {
			int[] pair = nums.get(i);
			if (pair[1] == j) {
				seq[j] = pair[0];
				j--;
			}
		}
		
		for (int a : seq) {
			sb.append(a).append(' ');
		}
		
		System.out.println(sb);
		
	}
	
	private static int binarySearch(int[] arr, int key, int from, int to) {
		
		int lo = from - 1;
		int hi = to;
		
		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			int val = arr[mid];
			if (val < key) {
				lo = mid;
			} else if (val > key) {
				hi = mid;
			} else {
				return mid;
			}
		}
		
		return hi;
	}

}