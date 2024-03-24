import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static class Union {
		int[] parents;
		
		void init(int n) {
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
		}
		
		int find(int n) {
			if (n == parents[n])
				return n;
			return parents[n] = find(parents[n]);
		}
		
		boolean union(int a, int b)	{
			int aRoot = find(a);
			int bRoot = find(b);
			if (aRoot == bRoot) 
				return false;
			parents[bRoot] = aRoot;
			return true;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Union union = new Union();
		union.init(N);
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (!union.union(from, to)) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
		
	}
	
}