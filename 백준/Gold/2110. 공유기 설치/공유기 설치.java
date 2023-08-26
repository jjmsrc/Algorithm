import java.io.*;
import java.util.*;

//가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램
// 최대 거리 출력

public class Main { 

	static int N, C; // 집 개수, 공유기 개수

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int[] homes = new int[N];
		int min = 0, max = 0;
		for (int i = 0; i < homes.length; i++) {
			homes[i] = Integer.parseInt(br.readLine());
			if (homes[i] > max)
				max = homes[i];
		}
		
		Arrays.sort(homes); // 위치 순으로 정렬
		
		int[] dist = new int[N + 1];
		for (int i = 1; i < N; i++) {
			dist[i] = homes[i] - homes[i - 1]; // 집과 집사이의 거리
		}
		dist[0] = dist[N] = 1_000_000_000;
		
		int res = 0;
		while(min <= max) {
			int cnt = C; // 놓는 횟수
			int mid = (min >> 1) + (max >> 1) + (min & 1 & max); // 적당한 최소 거리 설정
			for (int i = 1; i <= N; i++) {
				if (dist[i] < mid) { // 설정한 거리보다 작을 경우
					int sum = dist[i];
					while(i <= N && (sum += dist[++i]) < mid); // 설정한 거리보다 커지게 거리를 합치기
				}
				cnt--;
			}
			
//			System.out.println(min+" "+max+" "+mid+" "+cnt);
			
			if (cnt > 0) { // 공유기를 충분히 놓지 못했을 때
				// 거리 줄이기
				max = mid - 1;
			} else { // 공유기를 충분히 놓았을 때
				// 현재 값 저장하고 거리 늘리기
				res = mid;
				min = mid + 1;
			}
 		}
		
//		System.out.println(min+", "+max);

		System.out.print(res);

	}

}