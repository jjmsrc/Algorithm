import java.io.*;
import java.util.*;

public class Main {
	
	private static int N, C;
	private static int[] weights;
	private static List<Long> leftSums, rightSums;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		weights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		if (N == 1) {
			System.out.println(weights[0] <= C ? 2 : 1);
			return;
		}
		
		leftSums = new ArrayList<Long>();
		rightSums = new ArrayList<Long>();
		
		generateSumList(0, true, 0);
		generateSumList(N / 2, false, 0);
		Collections.sort(leftSums);
		
		int ans = 0;
		for (Long s : rightSums) {
			ans += countLower(C - s);
		}
		
		System.out.println(ans);

	}

	private static void generateSumList(int i, boolean isLeft, long sum) {
		if (sum > C)
			return;
		if (isLeft && i == (N / 2)) {
			leftSums.add(sum);
			return;
		} else if (!isLeft && i == N) {
			rightSums.add(sum);
			return;
		}
		
		generateSumList(i + 1, isLeft, sum);
		generateSumList(i + 1, isLeft, sum + weights[i]);
	}
	
	private static int countLower(long limit) {
//		int idx = Collections.binarySearch(leftSums, limit);
//		idx = idx < 0 ? -(idx + 1) : idx + 1;
//		return idx;
		
		int st = 0;
		int end = leftSums.size();
		while(st + 1 < end) {
			int mid = (st + end) / 2;
			if (leftSums.get(mid) > limit) end = mid;
			else st = mid;
		}
		
		return end;
	}

}