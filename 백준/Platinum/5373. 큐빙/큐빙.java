import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	private static class Cube {
		
		private static enum Face {
			U, D, F, B, L, R;
			
			static Face[][] rotTable = {
					{R, F, L, B},	// U
					{B, L, F, R},	// D
					{R, D, L, U},	// F
					{U, L, D, R},	// B
					{F, D, B, U},	// L
					{U, B, D, F},	// R
			};
			
		}
		
		private static class Piece {
			private int[] colors;
			
			public Piece() {
				colors = new int[6];
				Arrays.fill(colors, -1);
			}
			
			public void setColor(Face f, int color) {
				colors[f.ordinal()] = color;
			}
			
			public int getColor(Face f) {
				return colors[f.ordinal()];
			}
			
			public void rotateFaces(Face f, boolean isClockwise) {
				Face[] rotTable = Face.rotTable[f.ordinal()];
				if (!isClockwise) {
					for (int i = 0; i < 3; i++) {
						int fa = rotTable[i].ordinal();
						int fb = rotTable[i + 1].ordinal();
						int tmp = colors[fa];
						colors[fa] = colors[fb];
						colors[fb] = tmp;
					}
				} else {
					for (int i = 3; i > 0; i--) {
						int fa = rotTable[i].ordinal();
						int fb = rotTable[i - 1].ordinal();
						int tmp = colors[fa];
						colors[fa] = colors[fb];
						colors[fb] = tmp;
					}
				}
//				System.out.println(this);
			}

			@Override
			public String toString() {
				StringBuilder sb = new StringBuilder();
				for (int c : colors)
					sb.append(c != -1 ? "wyrogb".charAt(c) : '?').append(' ');
				return sb.toString();
			}
			
		}
		
		private static int[][] rotIndexs = {
				{0, 0},
				{0, 1},
				{0, 2},
				{1, 2},
				{2, 2},
				{2, 1},
				{2, 0},
				{1, 0},
		};
		
		private Piece[][][] pieces;
		
		public Cube() {
			pieces = new Piece[3][3][3];
			for (int i = 0; i < 27; i++) {
				int x = i / 9;
				int y = (i / 3) % 3;
				int z = i % 3;
				pieces[x][y][z] = new Piece();
			}
			
			for (int i = 0; i < 9; i++) {
				int x = i / 3;
				int y = i % 3;
				pieces[x][y][2].setColor(Face.U, Face.U.ordinal());
				pieces[x][y][0].setColor(Face.D, Face.D.ordinal());
				pieces[2][x][y].setColor(Face.F, Face.F.ordinal());
				pieces[0][x][y].setColor(Face.B, Face.B.ordinal());
				pieces[x][0][y].setColor(Face.L, Face.L.ordinal());
				pieces[x][2][y].setColor(Face.R, Face.R.ordinal());
			}
			
		}
		
		private Piece getPiece(int[] pos) {
			return pieces[pos[0]][pos[1]][pos[2]];
		}
		private void setPiece(int[] pos, Piece p) {
			pieces[pos[0]][pos[1]][pos[2]] = p;
		}
		
		private void rotatePieces( Face f,int fixedAxis, int n,
				boolean rotPiecesCW, boolean rotFacesCW) {
			
			// rotate a piece
			for (int i = 0; i < 9; i++) {
				int x = i / 3;
				int y = i % 3;
				int[] xa;
				if (fixedAxis == 0) {
					xa = new int[] {n, x, y};
				}
				else if (fixedAxis == 1) {
					xa = new int[] {x, n, y};
				}
				else { // if (fixedAxis == 2) {
					xa = new int[] {x, y, n};
				}
//				System.out.println(Arrays.toString(xa));
				Piece piece = getPiece(xa);
				piece.rotateFaces(f, rotFacesCW);
			}
			
			// rotate a cube
			for (int i = 0; i < 6; i++) {
				int ia, ib;
				if (rotPiecesCW) {
					ia = i;
					ib = i + 2;
				} else {
					ia = 7 - i;
					ib = 5 - i;
				}
//				System.out.println(ia + ", " + ib);
				int[] ra = rotIndexs[ia];
				int[] rb = rotIndexs[ib];
				int[] xa, xb;
				if (fixedAxis == 0) {
					xa = new int[] {n, ra[0], ra[1]};
					xb = new int[] {n, rb[0], rb[1]};
				}
				else if (fixedAxis == 1) {
					xa = new int[] {ra[0], n, ra[1]};
					xb = new int[] {rb[0], n, rb[1]};
				}
				else { // if (fixedAxis == 2) {
					xa = new int[] {ra[0], ra[1], n};
					xb = new int[] {rb[0], rb[1], n};
				}
				Piece tmp = getPiece(xa);
				setPiece(xa, getPiece(xb));
				setPiece(xb, tmp);
				
//				System.out.println(getPiece(xa));
//				System.out.println(getPiece(xb));
			}

		}
		
		public void rotate(char cmdFace, char cmdRot) {
			
			Face f;
			boolean isClockwise = true;
			if (cmdRot == '-')
				isClockwise = false;
			
			if (cmdFace == 'U') {
				f = Face.U;
				rotatePieces(f, 2, 2, !isClockwise, isClockwise);
			} else if (cmdFace == 'D') {
				f = Face.D;
				rotatePieces(f, 2, 0, isClockwise, isClockwise);
			} else if (cmdFace == 'F') {
				f = Face.F;
				rotatePieces(f, 0, 2, !isClockwise, isClockwise);
			} else if (cmdFace == 'B') {
				f = Face.B;
				rotatePieces(f, 0, 0, isClockwise, isClockwise);
			} else if (cmdFace == 'L') {
				f = Face.L;
				rotatePieces(f, 1, 0, !isClockwise, isClockwise);
			} else if (cmdFace == 'R') {
				f = Face.R;
				rotatePieces(f, 1, 2, isClockwise, isClockwise);
			} else {
				throw new IllegalArgumentException();
			}
		}
		
		public String getFaceColors() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					Piece piece = pieces[i][j][2];
					int ci = piece.getColor(Face.U);
					char c = "wyrogb".charAt(ci);
					sb.append(c);
				}
				sb.append('\n');
			}
			return sb.toString();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// U - w, D - y, F - r, B - o, L - g, R - b

		int T = Integer.parseInt(br.readLine());
		int n = 0;
		for (int i = 0; i < T; i++) {
			n = Integer.parseInt(br.readLine());
			String cmds = br.readLine();
			String ans = solve(cmds);
			sb.append(ans);
		}

		System.out.println(sb);
	}

	private static String solve(String cmds) {

		Cube cube = new Cube();

		int nCmd = (cmds.length() / 3 + 1) * 3;
		for (int i = 0; i < nCmd; i += 3) {
			char cmdFace = cmds.charAt(i);
			char cmdRot = cmds.charAt(i + 1);
			cube.rotate(cmdFace, cmdRot);
		}

		return cube.getFaceColors();
	}

}