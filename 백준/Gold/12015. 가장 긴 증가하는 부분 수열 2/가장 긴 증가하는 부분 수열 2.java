import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int offset = 10;
		int[] mem = new int[1_000_000 + 1];
		int[] localMax = new int[(1_000_000 >> offset) + 1];
		
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			int rootIdx = num >> offset;
			int max = 0;
			if (rootIdx > 0) {
				for (int j = 0; j < rootIdx; j++) {
					if (max < localMax[j])
						max = localMax[j];
				}
			}
			for (int j = rootIdx << offset; j < num; j++) {
				if (max < mem[j])
					max = mem[j];
			}
			max += 1;
			mem[num] = max;
			if (localMax[rootIdx] < max)
				localMax[rootIdx] = max;
		}
		
		System.out.println(Arrays.stream(mem).max().getAsInt());
	}

}