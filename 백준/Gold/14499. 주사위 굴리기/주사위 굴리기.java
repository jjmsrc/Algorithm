import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M,x,y,K;
	static int[][] map;
	static int[] input;
	static int[] dice = {0,0,0,0,0,0}; //위 오른쪽 밑 왼쪽 앞쪽 뒤쪽
	static int[] dx = {0,0,0,-1,1}; //동서북남
	static int[] dy = {0,1,-1,0,0};
	static int up = 0;
	static int f = 4;
	static int down=2;
	static int b = 5;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken())+1;
		y = Integer.parseInt(st.nextToken())+1;
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		input = new int[K];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
//		System.out.println(Arrays.toString(dice));
		for(int i=0;i<K;i++) {
			if(move(Integer.parseInt(st.nextToken()))) {
				bw.write(dice[up]+"\n");
			}
		}
		bw.flush();
	}
	private static boolean move(int num) {
		int nx = x+dx[num];
		int ny = y+dy[num];
		if(nx<=0||ny<=0||nx>N||ny>M) return false;
		if(num==1) {
			right();
		}else if(num==2) {
			left();
		}else if(num==3) {
			back();
		}else {
			front();
		}
//		System.out.println(Arrays.toString(dice));
//		System.out.println(map[nx][ny]);
		if(map[nx][ny]==0) {
			map[nx][ny]=dice[down];
		}else {
			dice[down] = map[nx][ny];
			map[nx][ny]=0;
		}
//		System.out.println(Arrays.toString(dice));
//		System.out.println();
		x=nx;
		y=ny;
		return true;
	}
	private static void right() {
		int tmp = dice[3];
		for(int i=3;i>0;i--) {
			dice[i]=dice[i-1];
		}
		dice[0]=tmp;
	}
	private static void left() {
		int tmp = dice[0];
		for(int i=0;i<3;i++) {
			dice[i]=dice[i+1];
		}
		dice[3]=tmp;
	}
	private static void front() {
		
		int tmp = dice[up];
		dice[up] = dice[b];
		dice[b] = dice[down];
		dice[down] = dice[f];
		dice[f] = tmp;
	}
	private static void back() {
		int tmp = dice[up];
		dice[up] = dice[f];
		dice[f] = dice[down];
		dice[down] = dice[b];
		dice[b] = tmp;
	}
}