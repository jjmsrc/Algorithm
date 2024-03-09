import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	private static class Node {
		int v;
		Node next;

		Node(int v, Node next) {
			this.v = v;
			this.next = next;
		}
	}

	private static Node[] adjList;
//	private static int[] maxTime;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		adjList = new Node[N];
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 1; i < N; i++) {
			int v = Integer.parseInt(st.nextToken());
			adjList[v] = new Node(i, adjList[v]);
		}

		int ans = calcMinPropagationTime(0, 0);

		System.out.println(ans);

	}

	private static int calcMinPropagationTime(int v, int t) {
		
		if (adjList[v] == null)
			return t;

		ArrayList<Integer> timeList = new ArrayList<Integer>();
		
		for (Node node = adjList[v]; node != null; node = node.next) {
			timeList.add(calcMinPropagationTime(node.v, t));
		}
		
		Collections.sort(timeList, (t1, t2) -> t2 - t1);
		
		int maxT = -1;
		for (int i = 0; i < timeList.size(); i++) {
			int tmp = (i + 1) + timeList.get(i);
			if (maxT < tmp)
				maxT = tmp;
		}
		
		return maxT;
	}

}