import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int nums[][] = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i][0] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			nums[i][1] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j][0] < nums[i][0] && nums[j][1] + 1 > nums[i][1])
					nums[i][1] = nums[j][1] + 1;
			}
			max = Math.max(max, nums[i][1]);
		}
		
		System.out.println(max);
		
	}
}