import java.awt.Container;
import java.io.*;
import java.util.*;

public class Main {
	
	private static class Friend {
		static Map<String, Integer> nameMap = new HashMap<String, Integer>();;
		static int[] parents = new int[200_000];
		static int[] numFriends = new int[200_000];
		static int cnt = 0;
		
		static void init() {
			nameMap.clear();
			for (int i = 0; i < 200_000; i++) {
				parents[i] = i;
				numFriends[i] = 0;
			}
		}
		
		static int find(String nameA, String nameB) {
			Integer ia = nameMap.get(nameA);
			Integer ib = nameMap.get(nameB);
			
			int ans = 0;
			if (ia == null && ib == null) {
				numFriends[cnt] = 1;
				int a = cnt++;
				numFriends[cnt] = 1;
				int b = cnt++;
				nameMap.put(nameA, a);
				nameMap.put(nameB, b);
				union(a, b);
				ans = 2;
			} else if (ia == null && ib != null) {
				numFriends[cnt] = 1;
				int a = cnt++;
				nameMap.put(nameA, a);
				union(ib, a);
				ans = numFriends[find(a)];
			} else if (ia != null && ib == null) {
				numFriends[cnt] = 1;
				int b = cnt++;
				nameMap.put(nameB, b);
				union(ia, b);
				ans = numFriends[find(b)];
			} else {
				union(ia, ib);
				ans = numFriends[find(ia)];
			}
			
			return ans;
		}
		
		private static int find(int a) {
			if (a == parents[a]) return a;
			return parents[a] = find(parents[a]);
		}
		
		private static boolean union(int a, int b) {
			int rootA = find(a);
			int rootB = find(b);
			if (rootA == rootB) return false;
			parents[rootB] = rootA;
			numFriends[rootA] += numFriends[rootB];
			return true;
		}
	}
	
	static Map<String, Set<String>> friends;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		friends = new HashMap<String, Set<String>>();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			Friend.init();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				sb.append(Friend.find(a, b)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}