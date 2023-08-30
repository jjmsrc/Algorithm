import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K,W,H;
	static int[][][] map;
	static int[] dx1 = {-1,1,0,0};
	static int[] dy1 = {0,0,-1,1};
	static int[] dx2 = {-1,-2,-2,-1,1,2,2,1};
	static int[] dy2 = {-2,-1,1,2,2,1,-1,-2};
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map= new int[H][W][K+1];
		boolean[][][] visited = new boolean[H][W][K+1];
		
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if(map[i][j][0]==1) {
					for(int k=0;k<=K;k++) {
						map[i][j][k]=-1;
						visited[i][j][k]=true;
					}
				}
			}
		}
		if(W==1&&H==1) {
			if(map[0][0][0]==0) {
				System.out.println(0);
				return;
			}else {
				System.out.println(-1);
				return;
			}
		}
		BFS(visited);
//		for(int i=0;i<=K;i++) {
//			for(int j=0;j<H;j++) {
//				for(int k=0;k<W;k++) {
//					System.out.print(map[j][k][i]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		int result=Integer.MAX_VALUE;
		for(int i=0;i<=K;i++) {
			if(map[H-1][W-1][i]>0) {
				result = Math.min(result, map[H-1][W-1][i]);
			}
		}
		if(result==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
		
	}
	private static void BFS(boolean[][][] visited) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0,0,0,0}); //x,y,num,k
		visited[0][0][0]=true;
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			for(int i=0;i<4;i++) {
				int nx = tmp[0]+dx1[i];
				int ny = tmp[1]+dy1[i];
				if(nx>=0&&nx<H&&ny>=0&&ny<W&&!visited[nx][ny][tmp[3]]) {
					map[nx][ny][tmp[3]] = tmp[2]+1;
					visited[nx][ny][tmp[3]]=true;
					queue.add(new int[] {nx,ny,tmp[2]+1,tmp[3]});
				}
			}
			if(tmp[3]<K) {
				for(int i=0;i<8;i++) {
					int nx = tmp[0]+dx2[i];
					int ny = tmp[1]+dy2[i];
					if(nx>=0&&nx<H&&ny>=0&&ny<W&&!visited[nx][ny][tmp[3]+1]&&map[nx][ny][tmp[3]+1]!=-1) {
						map[nx][ny][tmp[3]+1] = tmp[2]+1;
						visited[nx][ny][tmp[3]+1]=true;
						queue.add(new int[] {nx,ny,tmp[2]+1,tmp[3]+1});
					}
				}
			}
			
		}
		
	}
	
}