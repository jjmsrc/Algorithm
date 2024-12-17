import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

	private static class Node {
		int idx;
		Node next;

		Node(int idx, Node next) {
			this.idx = idx;
			this.next = next;
		}

		@Override
		public boolean equals(Object obj) {
			Node n = (Node) obj;
			return idx == n.idx;
		}

	}
	
	private static final int HASH_MOD = 99991;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int len = Integer.parseInt(br.readLine());
		char[] seq = br.readLine().strip().toCharArray();

		int ans = solve(seq);
		
		System.out.println(ans);

	}

	private static int solve(char[] seq) {
		HashMap<Integer, Node> hashMap = new HashMap<>();

		int lo = 0;
		int hi = seq.length - 1;
		int ret = 0;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			
			if (findSubSeq(seq, hashMap, mid)) {
				ret = Math.max(ret, mid);
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}

			hashMap.clear();
		}

		return ret;
	}

	private static boolean findSubSeq(char[] seq, HashMap<Integer, Node> hashMap, int len) {
		int hashVal = seq[0];
		int c = 1;

		for (int i = 1; i < len; i++) {
			hashVal = hashVal << 1;
			hashVal = (hashVal + seq[i]) % HASH_MOD;
			c = (c << 1) % HASH_MOD;
		}

		hashMap.put(hashVal, new Node(0, null));

		for (int i = len; i < seq.length; i++) {

			hashVal = (hashVal + HASH_MOD - (seq[i - len] * c)) % HASH_MOD;
			hashVal = ((hashVal << 1) + seq[i]) % HASH_MOD;
			Node node = hashMap.get(hashVal);

			int stIdx = i - len + 1;
			Node pNode = node;
			while(pNode != null) {
				if (compare(seq, pNode.idx, stIdx, len)) {
					return true;
				}
				pNode = pNode.next;
			}
			node = new Node(stIdx, node);
			hashMap.put(hashVal, node);
		}

		return false;
	}

	private static boolean compare(char[] seq, int ai, int bi, int len) {
		for (int i = 0; i < len; i++) {
			if (seq[ai + i] != seq[bi + i])
				return false;
		}
		return true;
	}

}