import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
	
	
	private static class Path {
		int l;
		int r;
		
		Path(int l, int r) {
			if (l <= r) {
				this.l = l;
				this.r = r;
			} else {
				this.l = r;
				this.r = l;
			}
		}

		@Override
		public String toString() {
			return "Path [l=" + l + ", r=" + r + "]";
		}
		
	}
	
	private static class Railway {
		
		int n;
		
		List<Path> paths;
		List<Path> pathsL;
		List<Path> pathsR;
		
		Railway(int n) {
			this.n = n;
			paths = new ArrayList<>();
		}
		
		void add(int h, int o) {
			paths.add(new Path(h, o));
		}
		
		private void init(int d) {
			List<Path> paths = this.paths.stream().filter(a -> a.r - a.l <= d).collect(Collectors.toList());
			this.pathsL = paths.stream().sorted((a, b) -> a.l - b.l).collect(Collectors.toList());
			this.pathsR = paths.stream().sorted((a, b) -> a.r - b.r).collect(Collectors.toList());
		}
		
		int findMax(int d) {
			
			init(d);
			
			int n = pathsL.size();
			
			int ans = 0;
			
			int li = 0;
			int ri = 0;
			
			// (rx 보다 작은 p.r들의 개수) - (lx 보다 작은 p.l들의 개수)
			
			ans = ri;
			
			Path pl;
			Path pr;
			for (; ri < n; ri++) {
				pr = pathsR.get(ri);
				
				int rx = pr.r;
				int lx = rx - d;
				
				while(li < n) {
					pl = pathsL.get(li);
					if (lx <= pl.l) break;
					li++;
				}
				
				int cnt = (ri + 1) - li;
				if (ans < cnt) ans = cnt;
				
			}
			
			return ans;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		
		Railway railway = new Railway(n);
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int o = Integer.parseInt(st.nextToken());
			railway.add(h, o);
		}
		
		int d = Integer.parseInt(br.readLine());
		
		System.out.println(railway.findMax(d));

	}

}