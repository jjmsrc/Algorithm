import java.io.*;
import java.util.*;

public class Main {
	
	private static class Point {
		int x;
		int y;
		Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		Point[] points = new Point[n];
		
		for (int i = 0; i < points.length; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}
		
		double ans = 0;
		Point p1 = points[n - 1];
		for (int i = 0; i < points.length; i++) {
			Point p2 = points[i];
			ans += (p1.x + p2.x) * (double)(p1.y - p2.y);
			p1 = p2;
		}
		
		ans /= 2.0;
		ans = Math.abs(ans);
		
		System.out.println(String.format("%.1f", ans));
		
	}
}