import java.io.*;
import java.util.*;

public class Main {
	
	private static class Piece {
		int x, y;
		int dir; // 0 - right, 1 - left, 2 - up, 3 - down
		int t; // last flipped time
		Piece next;
		
		Piece(int x, int y, int dir, int t, Piece next) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.t = t;
			this.next = next;
		}
		
		boolean flip(int t) {
			if (this.t == t)
				return false;
			
			dir ^= 0b01;
			this.t = t;
			
			return true;
		}
		
	}

	private static class Cell {
		
		static Deque<Piece> stack = new ArrayDeque<>();

		Piece pHead, pTail;
		int color; // white = 0, red = 1, blue = 2
		
		Cell(Piece pHead, Piece pTail, int color) {
			super();
			this.pHead = pHead;
			this.pTail = pTail;
			this.color = color;
		}
		
		// target과 그 위에 있는 Piece들을 nc위치로 이동, 
		void move(boolean isReverse, Piece target, Cell nc, int nx, int ny) {

			Piece pPre = null; 
			Piece p = pHead;
			while(p != target) { // 타겟 Piece의 바로 전 Piece 탐색
				pPre = p;
				p = p.next;
			}
			if (p == null) return; // 타겟 Piece가 현재 Cell에 없으면 리턴
			
			// 움직일 Piece들의 좌표 업데이트
			for (p = target; p != null; p = p.next) {
				p.x = nx;
				p.y = ny;
			}
			
			// Piece 간 연결 재설정
			if (nc.pTail != null) nc.pTail.next = target;	// 다음 Cell의 끝에 붙이기
			if (pPre != null) pPre.next = null;				// 현재 Cell의 연결 끊기
			
			// head와 tail의 연결 재설정
			if (nc.pHead == null) nc.pHead = target;		// 다음 Cell의 head 재설정
			nc.pTail = pTail;								// 다음 Cell의 tail 재설정
			if ((pTail = pPre) == null) pHead = null;		// 현재 Cell의 head, tail 재설정
			
			if (isReverse) {
				nc.reverse(target);
			}
		}
		
		public int countPieces() {
			int cnt = 0;
			for (Piece p = pHead; p != null; cnt++, p = p.next);
			return cnt;
		}

		private void reverse(Piece target) {
			Piece p = pHead;
			Piece pPre = null;
			while(p != target) {
				pPre = p;
				p = p.next;
			}
			
			for(p = target; p != null; p = p.next) {
				stack.push(p);
			}
			
			p = pTail = stack.pop();
			if (pPre == null) {
				pHead = p;
			} else {
				pPre.next = p;
			}
			
			while(!stack.isEmpty()) {
				pTail = stack.pop();
				p.next = pTail;
				p = pTail;
			}
			
			if (pTail != null) pTail.next = null;
			
		}
		
	}
	
	private static class ChessSimulator {
		private static final int[] dx = {0, 0, -1, 1}; // 0 - right, 1 - left, 2 - up, 3 - down
		private static final int[] dy = {1, -1, 0, 0}; 
		
		private int N, K;
		private Cell[][] cells;
		private Piece[] pieces;
		
		public void init(BufferedReader br) throws Exception {
			StringTokenizer st;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cells = new Cell[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cells[i][j] = new Cell(null, null, Integer.parseInt(st.nextToken()));
				}
			}
			
			pieces = new Piece[K];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken()) - 1;
				cells[x][y].pHead = cells[x][y].pTail = 
						pieces[i] = new Piece(x, y, d, -1, null);
			}
		}

		// 최대 t가 1000이 될 때 까지 Piece 이동
		public int play() {
			
			int t = 0;
			while(++t <= 1000 && moveAllPieces(t));
			return t > 1000 ? -1 : t;
		}
		
		// 모든 Piece 이동
		private boolean moveAllPieces(int t) {
			
			for (int i = 0; i < pieces.length; i++) {
				
				Piece p = pieces[i];
				
				int ret = movePiece(t, p);
				
				if (ret == -1)
					ret = movePiece(t, p);
				
				if (ret >= 4) return false;
				
			}
			return true;
		}
		
		// 해당 Piece를 움직인 뒤의 총 개수 반환 
		private int movePiece(int t, Piece p) {
			
			Cell cc = cells[p.x][p.y];
			
			int nx = p.x + dx[p.dir];
			int ny = p.y + dy[p.dir];
			
			// 벽에 부딪혔을 경우 진행방향 변경
			if (!isIn(nx, ny)) { 
				p.flip(t);
				return -1;
			}
			
			Cell nc = cells[nx][ny];
			
			if (nc.color == 0) {		// white
				cc.move(false, p, nc, nx, ny);
			} else if (nc.color == 1) {	// red
				cc.move(true, p, nc, nx, ny);
			} else if (nc.color == 2) { // blue
				p.flip(t);
				return -1;
			}
			
			return nc.countPieces();
		}
		
		private boolean isIn(int x, int y) {
			return 0 <= x && x < N && 0 <= y && y < N;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ChessSimulator chessSimulator = new ChessSimulator();
		chessSimulator.init(br);
		
		int ans = chessSimulator.play();
		System.out.println(ans);
		
	}

}