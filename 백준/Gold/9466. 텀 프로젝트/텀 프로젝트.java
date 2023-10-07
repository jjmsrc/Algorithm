import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for (int it = 0; it < T; it++) {
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int[] students = new int[N + 1];
			for (int is = 1; is <= N; is++) {
				students[is] = Integer.parseInt(st.nextToken());
			}
			
			int[] sStatus = new int[N + 1]; // 0: 결정 전, 1: 팀 선택 중, 2: 팀 선택 완료, 3: 팀 선택 실패
			sStatus[0] = 2;
			
			Stack<Integer> stack = new Stack<Integer>();
			for (int i = 1; i <= N; i++) {
				if (sStatus[i] == 0) {
					stack.push(i);
					sStatus[i] = 1;
					int ni = students[i];
					while(sStatus[ni] == 0) {
						sStatus[ni] = 1;
						stack.push(ni);
						ni = students[ni];
					}
					if (sStatus[ni] == 1) {
						while(!stack.isEmpty() && stack.peek() != ni) {
							int pi = stack.pop();
							sStatus[pi] = 2;
						}
						int pi = stack.pop();
						sStatus[pi] = 2;
					}
					while(!stack.isEmpty()) {
						int pi = stack.pop();
						sStatus[pi] = 3;
					}
				}
			}
			
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (sStatus[i] == 3)
					cnt++;
			}
			
			sb.append(cnt).append("\n");
		}

		System.out.println(sb);

	}
}