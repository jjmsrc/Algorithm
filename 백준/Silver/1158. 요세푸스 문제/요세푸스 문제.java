import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 선언 및 초기화
		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int K = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		
		// 계산 및 출력
		sb.append("<");
		int idx = 0;
		for (int i = 0; i < N; i++) {
			idx = (idx + K - 1) % list.size();
			sb.append(list.get(idx)).append(", ");
			list.remove(idx);
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		
		System.out.println(sb);
	}

}