import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int minDiff;
	static int[][] abils;
	static int[] memSum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		abils = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				abils[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				abils[i][j] += abils[j][i];
			}
		}
		
		minDiff = Integer.MAX_VALUE;
		
		boolean[] isSelected = new boolean[N];
		int[] startMems = new int[N / 2];
		isSelected[0] = true;
		selectTeam(1, 1, isSelected, startMems, 0);
		
		System.out.println(minDiff);

	}
	
	private static void selectTeam(int cnt, int start, boolean[] isSelected, int[] startMems, int startSum) {
		
		if (cnt == N / 2) {
			
			ArrayList<Integer> linkMems = new ArrayList<Integer>();
			for (int i = 0; i < isSelected.length; i++) {
				if (!isSelected[i]) linkMems.add(i);
			}
			
			int linkSum = 0;
			for (int i = 0; i < linkMems.size() - 1; i++) {
				for (int j = i + 1; j < linkMems.size(); j++) {
					int ii = linkMems.get(i);
					int jj = linkMems.get(j);
					linkSum += abils[ii][jj];
				}
			}
			
			minDiff = Math.min(minDiff, Math.abs(startSum - linkSum));
			return;
		}
		
		for (int i = start; i < N - (N / 2 - cnt - 1); i++) {
			isSelected[i] = true;
			startMems[cnt] = i;
			int tmp = 0;
			for (int j = 0; j < cnt; j++) {
				tmp += abils[startMems[j]][i];
			}
			selectTeam(cnt + 1, i + 1, isSelected, startMems, startSum + tmp);
			isSelected[i] = false;
		}
	}
	
}