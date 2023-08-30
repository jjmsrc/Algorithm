import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 각 날의 상담 소요일과 금액을 저장하는 배열
	static int[][] data;

	public static void main(String[] args) throws Exception {

		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		data = new int[N][2]; // N * 2 차원 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}

		// 초기값으로 함수 실행
		int ans = dp(N);
		
		// 결과값 출력
		System.out.println(ans);

	}

	private static int dp(int n) {
		
		int[][] mem = new int[n + 1][n + 1];
		for (int i = 1; i < mem.length; i++) {
			for (int j = 1; j < mem[i].length; j++) {
				mem[i][j] = -1;
			}
		}
		
		return runDp(n, n, mem);
	}
	
	private static int runDp(int day, int limit, int[][] mem) {
		if (mem[day][limit] != -1)
			return mem[day][limit];
		
		int noConsulting = runDp(day - 1, limit, mem);
		if (day + data[day - 1][0] - 1 <= limit) {
			int consulting = runDp(day - 1, day - 1, mem) + data[day - 1][1];
			mem[day][limit] = Math.max(noConsulting, consulting);
		}
		else {
			mem[day][limit] = noConsulting;
		}
		
		return mem[day][limit];
	}
	
}