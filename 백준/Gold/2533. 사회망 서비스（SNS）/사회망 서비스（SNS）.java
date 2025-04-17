import java.io.*;
import java.util.*;

public class Main {
	
	private static class Node {
		int v;
		Node next;
		Node(int v, Node next) {
			this.v = v;
			this.next = next;
		}
	}
	
	private static final int IF_NONE = 0, IF_EA = 1;
	private static final int NEW = 0, PENDING = 1, DONE = 2;
	
	private static int N;
	private static Node[] list;
	private static int[] stat;
	private static int[] cntEdges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		list = new Node[N + 1];
		cntEdges = new int[N + 1];
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list[u] = new Node(v, list[u]);
			list[v] = new Node(u, list[v]);
			cntEdges[u]++;
			cntEdges[v]++;
		}
		
		int ans = solve();
		
		System.out.println(ans);

	}

	private static int solve() {

		int cntEarlyAdapter = 0;
		
		// 최소의 얼리어댑터 수를 저장
		int[][] mem = new int[N + 1][2];
		 
		Deque<Integer> deque = new ArrayDeque<>();
		stat = new int[N + 1];
		
		deque.add(1);
		
		while(!deque.isEmpty()) {
			int v = deque.peekLast();
			if (stat[v] == NEW) {
				stat[v] = PENDING;
				for (Node node = list[v]; node != null; node = node.next) {
					if (stat[node.v] == NEW) deque.offerLast(node.v);
				}
			} else if (stat[v] == PENDING) {
				stat[v] = DONE;
				int cntEAIfNone = 0;
				int cntEAIfEA = 0;
				for (Node node = list[v]; node != null; node = node.next) {
					if (stat[node.v] != DONE) continue;
					cntEAIfNone += mem[node.v][IF_EA];
					cntEAIfEA += Math.min(mem[node.v][IF_NONE], mem[node.v][IF_EA]);
				}
				mem[v][IF_NONE] = cntEAIfNone;
				mem[v][IF_EA] = 1 + Math.min(cntEAIfEA, cntEAIfNone);
				deque.pollLast();
			} else { // stat[v] == DONE
				deque.pollLast();
			}
		}
		
		cntEarlyAdapter = Math.min(mem[1][IF_NONE], mem[1][IF_EA]);
		
		return cntEarlyAdapter;
	}
	
}