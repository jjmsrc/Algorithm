import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][2];
		int[] result = new int[N+2];
		int max = Integer.MIN_VALUE;
		for(int i=1;i<=N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i=N;i>=0;i--) {
			if(arr[i][0]+i<=N+1) {
				result[i] = Math.max(arr[i][1]+result[arr[i][0]+i], result[i+1]);
			}else {
				result[i] = result[i+1];
			}
			
		}
		System.out.println(result[0]);
	}
}