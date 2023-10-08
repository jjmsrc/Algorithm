import java.io.*;
import java.util.*;

public class Main {

	private static class FireBall {
		int m;
		int s;
		int d;
		FireBall next;

		FireBall(int m, int s, int d, FireBall next) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
			this.next = next;
		}

		@Override
		public String toString() {
			return m + "";
		}

		boolean hasNext() {
			return next != null;
		}

		void combineAll() {

			int cntFBs = 1;
			boolean isAllSameDir = true;
			boolean isDirEven = (this.d & 1) == 0;

			for (FireBall fb = next; fb != null; fb = fb.next) {
				cntFBs++;
				this.m += fb.m;
				this.s += fb.s;
				if (isAllSameDir && ((isDirEven && (fb.d & 1) != 0) || (!isDirEven && (fb.d & 1) == 0))) {
					isAllSameDir = false;
				}
			}

			this.m /= 5;
			this.s /= cntFBs;
			this.d = isAllSameDir ? 0 : 1;

			return;
		}

		int getAllMass() {
			int m = this.m;
			for (FireBall fb = next; fb != null; fb = fb.next) {
				m += fb.m;
			}
			return m / 5 * 4;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력값 초기화

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		FireBall[][] fireBalls = new FireBall[N][N];
		Queue<int[]> queue = new ArrayDeque<int[]>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireBalls[x][y] = new FireBall(m, s, d, null);
			queue.offer(new int[] { x, y });
		}

		// 이동 명령 수행

		int[] dxs = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dys = { 0, 1, 1, 1, 0, -1, -1, -1 };

		FireBall[][] currFBs = fireBalls;
		FireBall[][] nextFBs = new FireBall[N][N];

		for (int iMove = 0; iMove < K; iMove++) {
			int nFBs = queue.size();
			for (int i = 0; i < nFBs; i++) {
				int[] pos = queue.poll();
				int x = pos[0];
				int y = pos[1];
				FireBall fb = currFBs[x][y];
				currFBs[x][y] = null;

				if (fb.hasNext()) { // 2개 이상의 파이어볼이 있는 경우
					fb.combineAll();
					if (fb.m > 0) {
						for (int j = 0; j < 4; j++) {
							int nd = fb.d + j * 2;
							int nx = (x + dxs[nd] * fb.s) % N;
							int ny = (y + dys[nd] * fb.s) % N;
							if (nx < 0)
								nx += N;
							if (ny < 0)
								ny += N;
							if (nextFBs[nx][ny] == null)
								queue.offer(new int[] { nx, ny });
							FireBall nfb = new FireBall(fb.m, fb.s, nd, nextFBs[nx][ny]);
							nextFBs[nx][ny] = nfb;
						}
					}
				} else { // 1개만 있는 경우
					int nx = (x + dxs[fb.d] * fb.s) % N;
					int ny = (y + dys[fb.d] * fb.s) % N;
					if (nx < 0)
						nx += N;
					if (ny < 0)
						ny += N;
					if (nextFBs[nx][ny] == null)
						queue.offer(new int[] { nx, ny });
					fb.next = nextFBs[nx][ny];
					nextFBs[nx][ny] = fb;
				}
			}
			FireBall[][] tmp = currFBs;
			currFBs = nextFBs;
			nextFBs = tmp;
		}

		// 남은 질량 계산

		int sumMass = 0;
		while (!queue.isEmpty()) {
			int[] pos = queue.poll();
			int x = pos[0];
			int y = pos[1];
			if (currFBs[x][y] != null) {
				FireBall fb = currFBs[x][y];
				sumMass += fb.hasNext() ? fb.getAllMass() : fb.m;
				currFBs[x][y] = null;
			}
		}

		System.out.println(sumMass);

	}

}