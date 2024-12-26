import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node {
		int from;
		int to;
		long sum;
		int subNodeCnt;
		Node(int from, int to, long sum, int subNodeCnt) {
			this.from = from;
			this.to = to;
			this.sum = sum;
			this.subNodeCnt = subNodeCnt;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
		
		// 누적합을 배열 형태로 구하기
		long[] sumArr = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			long num = Long.parseLong(br.readLine());
			sumArr[i] = sumArr[i - 1] + num;
		}
		
		// 세그먼트 트리 형태 합 구하기
		ArrayList<Node> segmentTree = makeSegmentTree(N, sumArr);

		// 명령어 입력받기
		for (int i = 0, cnt = M + K; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken()); // 명령어
			int arg1 = Integer.parseInt(st.nextToken());
			long arg2 = Long.parseLong(st.nextToken());
			if (cmd == 1) {
				updateNum(segmentTree, 0, arg1, arg2);
			} else if (cmd == 2) {
				sb.append(rangeSum(segmentTree, 0, arg1, arg2)).append('\n');
			}
		}
		
		System.out.println(sb);

	}

	

	private static ArrayList<Node> makeSegmentTree(int n, long[] sumArr) {
		ArrayList<Node> tree = new ArrayList<Node>();
		
		addSegmentNode(sumArr, 1, n, tree);
		
		return tree;
		
	}
	
	private static int addSegmentNode(long[] sumArr, int from, int to, ArrayList<Node> tree) {
		
		if (from > to) {
			return 0;
		}
		else if (from == to) {
			tree.add(new Node(from, to, sumArr[to] - sumArr[from - 1], 1));
			return 1;
		} else {
			int cnt = 1;
			int mid = (from + to) / 2;
			Node node = new Node(from, to, sumArr[to] - sumArr[from - 1], 0);
			tree.add(node);
			cnt += addSegmentNode(sumArr, from, mid, tree);
			cnt += addSegmentNode(sumArr, mid + 1, to, tree);
//			System.out.printf("===add2: %d %d %d \n", cnt, from, to);
			return node.subNodeCnt = cnt;
		}
	}
	
	private static long rangeSum(ArrayList<Node> tree, int idx, int from, long to) {
		
//		System.out.printf("rs: %d %d %d \n", idx, from, to);
		
		if (from > to)
			return 0;
		
		Node node = tree.get(idx);
		
		if (from == node.from && to == node.to) {
//			System.out.printf("!!!!!!!!rs: %d %d %d \n", idx, from, to);
			return node.sum;
		} else {
			int li = idx + 1;
			Node leftChild = tree.get(li);
			int ri = idx + leftChild.subNodeCnt + 1;
			Node rightChild = tree.get(ri);
			
//			System.out.printf("rs: %d %d %d \n", idx, from, to);
//			System.out.printf("a: %d %d %d \n", aIdx, aSubNode.from, aSubNode.to);
//			System.out.printf("b: %d %d %d \n", bIdx, bSubNode.from, bSubNode.to);
			
			long sum = 0;
			
			if (to <= leftChild.to) {
				sum = rangeSum(tree, li, from, to);
			} else if (rightChild.from <= from) {
				sum = rangeSum(tree, ri, from, to);
			} else {
				sum += rangeSum(tree, li, from, leftChild.to);
				sum += rangeSum(tree, ri, rightChild.from, to);
			}
			
			return sum;
		}
	}
	
	private static long updateNum(ArrayList<Node> tree, int idx, int dest, long newNum) {
		
		Node node = tree.get(idx);
		
		if (node.from == dest && node.to == dest) {
			long diff = node.sum - newNum;
			node.sum = newNum;
			return diff;
		}
		
		int li = idx + 1;
		Node leftChild = tree.get(li);
		int ri = idx + leftChild.subNodeCnt + 1;
		
		long diff;
		
		if (dest <= leftChild.to) {
			diff = updateNum(tree, li, dest, newNum);
		} else { // if (rightChild.from <= dest) {
			diff = updateNum(tree, ri, dest, newNum);
		}
		
		node.sum -= diff;
		
		return diff;
	}
}