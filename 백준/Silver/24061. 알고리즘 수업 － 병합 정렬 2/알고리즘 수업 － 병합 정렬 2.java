import java.io.*;
import java.util.*;

public class Main {

	private static int[] tmp;

	private static int k, cnt;
	
	private static void mergeSort(int[] nums, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(nums, l, m);
			mergeSort(nums, m + 1, r);
			merge(nums, l, m, r);
		}
	}

	private static void merge(int[] nums, int l, int m, int r) {
		int o = l, i = l, j = m + 1;
		while (i <= m && j <= r) {
			if (comp(nums[i], nums[j]) < 0) {
				tmp[o++] = nums[i++];
			} else {
				tmp[o++] = nums[j++];
			}
		}
		while (i <= m) {
			tmp[o++] = nums[i++];
		}
		while (j <= r) {
			tmp[o++] = nums[j++];
		}
		o = l;
		while (o <= r) {
			nums[o] = tmp[o];
			o++;
			if (++cnt == k) {
				StringBuffer sb = new StringBuffer();
				for (int t : nums) {
					sb.append(t).append(" ");
				}
				sb.setLength(sb.length() - 1);
				System.out.println(sb);
				System.exit(0);
			}
		}
	}

	private static int comp(int a, int b) {
		return a - b;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cnt = 0;

		int[] nums = new int[N];
		tmp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(nums, 0, N - 1);
		
		System.out.println(-1);

	}
}