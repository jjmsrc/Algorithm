import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static int[] arr;
	static boolean[] visited;
	static int[] dx = {-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N==K) {
			System.out.println(0);
			System.out.println(N);
			return;
		}
		if(K<N) {
			System.out.println(N-K);
			for(int i=N;i>=K;i--) {
				System.out.print(i+" ");
			}
			return;
		}
		arr = new int[2*K];
		visited = new boolean[2*K];
		BFS();
	}
	private static void BFS() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {N,0});
		visited[N]=true;
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			if(tmp[0]==K) {
				System.out.println(tmp[1]);
				Stack<Integer> stack = new Stack<>();
				int cur = tmp[0];
				stack.add(cur);
				while(arr[cur]!=N) {
					stack.add(arr[cur]);
					cur = arr[cur];
				}
				stack.add(arr[cur]);
				while(!stack.isEmpty()) {
					System.out.print(stack.pop()+" ");
				}
				return;
			}
			for(int i=0;i<2;i++) {
				int nx = tmp[0]+dx[i];
				if(nx>=0&&nx<2*K&&!visited[nx]) {
					visited[nx]=true;
					queue.add(new int[] {nx,tmp[1]+1});
					arr[nx]=tmp[0];
				}
			}
			int nx = tmp[0]*2;
			if(nx>=0&&nx<2*K&&!visited[nx]) {
				visited[nx]=true;
				queue.add(new int[] {nx,tmp[1]+1});
				arr[nx]=tmp[0];
			}
		}
	}
}