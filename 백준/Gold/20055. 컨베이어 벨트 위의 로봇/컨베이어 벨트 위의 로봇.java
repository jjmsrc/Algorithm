import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 받기
		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int NN = 2 * N;
		final int K = Integer.parseInt(st.nextToken());

		int[] belt = new int[NN];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < NN; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> robots = new LinkedList<>();
		int iStart = 0;
		int cntZeros = 0;
		int cntSteps = 0;
		do {
			// step 1
			iStart = (iStart - 1 + NN) % NN; // 1-1: 한칸 전진
			// 1-2: 로봇이 내리는지 확인
			int iDrop = (iStart + N - 1) % NN; // 내리는 위치
			if (!robots.isEmpty() && robots.get(0) == iDrop) {
				robots.remove(0);
			}

			// step 2
			// 2-1: 로봇 이동
			ListIterator<Integer> it = robots.listIterator();
			int rFront = -1; // 앞 로봇
			while (it.hasNext()) { 
				int r = it.next();
				int nr = (r + 1) % NN;
				if (belt[nr] > 0) { // 내구도가 1 이상

					// 움직일 위치에 로봇이 있는지 확인
					if (rFront == nr) {
						rFront = r;
						continue;
					}

					// 내구도 0이 되는지 확인
					if (--belt[nr] == 0)
						cntZeros++;
					if (nr != iDrop) { // 내리는 위치가 아니면
						it.set(nr);
					} else { // 내리는 위치라면
						it.remove();
					}
					rFront = nr;
				} else {
					rFront = r;
				}

			}

			// step 3
			// 3-1: 로봇 올리기
			if (belt[iStart] > 0) { // 내구도가 0이상이라면
				if (--belt[iStart] == 0)
					cntZeros++;
				robots.add(iStart);
			}

			// step 4
			cntSteps++;
		} while (cntZeros < K); // 내구도 0인 상자 개수 확인

		// 결과값 출력
		System.out.println(cntSteps);

	}
}