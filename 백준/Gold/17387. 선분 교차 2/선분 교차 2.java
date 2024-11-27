import java.io.*;
import java.util.*;

public class Main {
	
	private static class Vector {
		double x;
		double y;

		Vector(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public Vector sub(Vector a) {
			return new Vector(x - a.x, y - a.y);
		}

		@Override
		public String toString() {
			return "Vector [x=" + x + ", y=" + y + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Vector[] va = new Vector[2];
		Vector[] vb = new Vector[2];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2; i++) {
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			va[i] = new Vector(x, y);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2; i++) {
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			vb[i] = new Vector(x, y);
		}
		
		int ans = 0;
		int res1 = ccw(va[0], va[1], vb[0]) * ccw(va[0], va[1], vb[1]);
		int res2 = ccw(vb[0], vb[1], va[0]) * ccw(vb[0], vb[1], va[1]);
		
		if ((res1 < 0 && res2 < 0) ||
				(res1 == 0 && res2 < 0) ||
				(res1 < 0 && res2 == 0)) {
			ans = 1;
		} else if (res1 == 0 && res2 == 0) {
			if (checkOverlap(va[0], va[1], vb[0], vb[1]) || 
					checkOverlap(vb[0], vb[1], va[0], va[1])) {
				ans = 1;
			}
		}
		
		System.out.println(ans);
		
	}
	
	// counter clock wise
	// 1이면 반시계, 0이면 동일 직선, -1이면 시계 방향 위치
	private static int ccw(Vector pivot, Vector src, Vector dst) {
		double p = cp(src.sub(pivot), dst.sub(pivot));
		if (p > 0) {
			return 1;
		} else if (p < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	// cross product
	private static double cp(Vector a, Vector b) {
		return a.x * b.y - b.x * a.y;
	}
	
	private static boolean checkOverlap(Vector aVec1, Vector aVec2, Vector bVec1, Vector bVec2) {
		double[] xs = {bVec1.x, bVec2.x};
		double[] ys = {bVec1.y, bVec2.y};
		for (int i = 0; i < 2; i++) {
			if (((aVec1.x <= xs[i] && aVec2.x >= xs[i]) || (aVec1.x >= xs[i] && aVec2.x <= xs[i])) && 
					((aVec1.y <= ys[i] && aVec2.y >= ys[i]) || (aVec1.y >= ys[i] && aVec2.y <= ys[i]))) {
				return true;
			}
		}
		return false;
	}

}