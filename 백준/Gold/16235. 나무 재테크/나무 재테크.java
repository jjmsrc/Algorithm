import java.util.*;
import java.io.*;

public class Main {

	private static class Tree implements Comparable<Tree>{
		int x, y;
		int age;

		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(age, o.age);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][N + 1];
		int[][] A = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		PriorityQueue<Tree> trees = new PriorityQueue<>();
		PriorityQueue<Tree> nextTrees = new PriorityQueue<>();
		Queue<Tree> deadTrees = new ArrayDeque<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.offer(new Tree(x, y, age));
		}
		
		int[] dxs = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dys = {1, 1, 0, -1, -1, -1, 0, 1};
		
		// K년 시뮬레이션
		for (int year = 0; year < K; year++) {
			// 봄
			while(!trees.isEmpty()) {
				Tree tree = trees.poll();
				if (map[tree.x][tree.y] >= tree.age) {
					map[tree.x][tree.y] -= tree.age;
					++tree.age;
					nextTrees.offer(tree);
					if (tree.age % 5 == 0) { // 가을 처리
						for (int i = 0; i < 8; i++) {
							int nx = tree.x + dxs[i];
							int ny = tree.y + dys[i];
							if (nx >= 1 && nx <= N && ny >= 1 && ny <= N) {
								nextTrees.offer(new Tree(nx, ny, 1));
							}
						}
					}
				} else {
					deadTrees.add(tree);
				}
			}
			
			// 여름
			while(!deadTrees.isEmpty()) {
				Tree tree = deadTrees.poll();
				map[tree.x][tree.y] += tree.age / 2;
			}
			 
			// 가을
			
			// 겨울
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] += A[i][j];
				}
			}
			
			// 교환
			PriorityQueue<Tree> tmp = trees;
			trees = nextTrees;
			nextTrees = tmp;
		}

		System.out.println(trees.size());

	}

}