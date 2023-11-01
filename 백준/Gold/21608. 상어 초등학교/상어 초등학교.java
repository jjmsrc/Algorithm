import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int NN = N * N;
		int[][] friends = new int[NN + 1][5];
		int[] idxOfFriends = new int[NN + 1];

		for (int i = 1; i <= NN; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				friends[i][j] = Integer.parseInt(st.nextToken());
				idxOfFriends[friends[i][0]] = i;
			}
		}

		int ans = simulate(N, friends, idxOfFriends);

		System.out.println(ans);

	}

	private static int simulate(final int N, int[][] friends, int[] idxF) {

		int NN = N * N;

		int[] dxs = { -1, 0, 1, 0 };
		int[] dys = { 0, -1, 0, 1 };

		int[][] room = new int[N + 1][N + 1];

		for (int si = 1; si <= NN; si++) { // 차례대로 앉을 학생 순서

			int[] fs = friends[si]; 

			// 자리를 결정할 조건
			int maxFs = -1; // 근처에 있는 좋아하는 학생
			int maxEmpty = 0; // 근처에 있는 빈자리
			int[] pos = { 0, 0 };

			for (int x = 1; x <= N; x++) {
				for (int y = 1; y <= N; y++) {
					if (room[x][y] != 0)
						continue;
					int cntFs = 0;
					int cntEmpty = 0;
					for (int di = 0; di < 4; di++) { // 인접한 자리 확인
						int nx = x + dxs[di];
						int ny = y + dys[di];
						if (nx < 1 || nx > N || ny < 1 || ny > N)
							continue;
						if (room[nx][ny] != 0) { // 학생이 있는 자리일 경우
							for (int i = 1; i <= 4; i++) {
								if (room[nx][ny] == fs[i]) {
									++cntFs;
									break;
								}
							}
						} else { // 학생이 없는 자리일 경우
							++cntEmpty;
						}
					}
					// 조건 확인 후 갱신
					if (maxFs < cntFs) { // 1번 조건
						maxFs = cntFs;
						maxEmpty = cntEmpty;
						pos[0] = x;
						pos[1] = y;
					} else if (maxFs == cntFs) { // 1번 조건 (다수)
						if (maxEmpty < cntEmpty) { // -> 2번 조건
							maxEmpty = cntEmpty;
							pos[0] = x;
							pos[1] = y;
						}
					}
				}
			}

			// 자리 앉기
			room[pos[0]][pos[1]] = fs[0];
		}

		// 만족도 총 합 구하기
		int sum = 0;
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				int nearFs = 0;
				for (int di = 0; di < 4; di++) { // 인접한 자리 확인
					int nx = x + dxs[di];
					int ny = y + dys[di];
					if (nx < 1 || nx > N || ny < 1 || ny > N)
						continue;
					for (int i = 1; i <= 4; i++) {
						if (room[nx][ny] == friends[idxF[room[x][y]]][i]) {
							++nearFs;
							break;
						}
					}
				}

				sum += Math.pow(10, nearFs == -1 ? 0 : nearFs - 1);
			}
		}

		return sum;
	}

}