import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	final static int D = 0, U = 1, R = 2, L = 3; // 접는 방향
	static int[][] grid;
	static int[] durl;
	static int k, h;
	
	private static void punch(int idx, int[] size, int x, int y) {
		
		if (idx == 2 * k) {
			grid[x][y] = h;
			return;
		}
		
		int nx = x;
		int ny = y;
		
		int lenX = size[0];
		int lenY = size[1];
		int nLenX = size[0];
		int nLenY = size[1];
		
		int side = durl[idx];
		
		switch(side) {
		case D:
			nx = x + size[0] / 2;
			nLenX = lenX / 2;
			break;
		case U:
			nLenX = lenX / 2;
			break;
		case R:
			ny = y + size[1] / 2;
			nLenY = lenY / 2;
			break;
		case L:
			nLenY = lenY / 2;
			break;
		}
		
		punch(idx + 1, new int[] {nLenX, nLenY}, nx, ny);
		
		int[] mapDU = {2, 3, 0, 1};
		int[] mapRL = {1, 0, 3, 2};
		
		switch(side) {
		case D:
			for (int i = 0; i < nLenX; i++) {
				for (int j = 0; j < nLenY; j++) {
					grid[x + i][y + j] = mapDU[grid[x + lenX - 1 - i][y + j]];
				}
			}
			break;
		case U:
			for (int i = 0; i < nLenX; i++) {
				for (int j = 0; j < nLenY; j++) {
					grid[x + lenX - 1 - i][y + j] = mapDU[grid[x + i][y + j]];
				}
			}
			break;
		case R:
			for (int i = 0; i < nLenX; i++) {
				for (int j = 0; j < nLenY; j++) {
					grid[x + i][y + j] = mapRL[grid[x + i][y + lenY - 1 - j]];
				}
			}
			break;
		case L:
			for (int i = 0; i < nLenX; i++) {
				for (int j = 0; j < nLenY; j++) {
					grid[x + i][y + lenY - 1 - j] = mapRL[grid[x + i][y + j]];
				}
			}
			break;
		}
		
	}
	
	public static void main(String[] args) throws Exception {

		// 입출력 변수 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 시스템 입력
		StringTokenizer st; // 토크나이저
		StringBuilder sb = new StringBuilder(); // 출력을 임시로 저장하는 버퍼

		k = Integer.parseInt(br.readLine()); // 손수건의 크기
		
		durl = new int[k * 2]; // 접는 방향을 저장하는 배열
		st = new StringTokenizer(br.readLine()); // 한 줄 읽고 토큰화 준비
		for (int j = 0; j < durl.length; j++) { // 접는 방향 읽기
			durl[j] = "DURL".indexOf(st.nextToken().charAt(0)); // 접는 방향 읽고 배열에 저장
		}
		
		h = Integer.parseInt(br.readLine()); // 구멍 뚫는 위치
		
		int N = 1 << k;
		
		grid = new int[N][N];
		
		punch(0, new int[] {N, N}, 0, 0);
		
		for (int j = 0; j < grid.length; j++) {
			for (int k = 0; k < grid[j].length; k++) {
				sb.append(grid[j][k]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb); // 출력

	}
}