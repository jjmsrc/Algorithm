import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	private static class MinHeap {
		private int n;
		private ArrayList<Integer> list;

		public MinHeap(int capacity) {
			this.n = 0;
			this.list = new ArrayList<Integer>(capacity);
			list.add(0);
		}

		public void offer(int element) {
			if (++n == list.size()) {
				list.add(element);
			} else {
				list.set(n, element);
			}
			for (int curr = n, parent = curr / 2; curr > 0; curr = parent, parent /= 2) {
				if (list.get(parent) <= element) {
					list.set(curr, element);
					break;
				}
				list.set(curr, list.get(parent));
			}
		}

		public int poll() {
			if (n == 0)
				return 0;
			
			if (n == 1) {
				n = 0;
				return list.get(1);
			}

			int ret = list.get(1);

			int last = list.get(n--);
			for (int curr = 1, child = 2;; curr = child, child *= 2) {
				if (child > n) {
					list.set(curr, last);
					break;
				} else {
					int offset = 0;
					if (child < n) {
						offset = list.get(child) < list.get(child + 1) ? 0 : 1;
					}
					if (last > list.get(child + offset)) {
						child += offset;
						list.set(curr, list.get(child));
					} else {
						list.set(curr, last);
						break;
					}
				}
			}

			return ret;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		MinHeap minHeap = new MinHeap(100_000);

		int nOps = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nOps; i++) {
			int op = Integer.parseInt(br.readLine());
			if (op > 0) {
				minHeap.offer(op);
			} else {
				sb.append(minHeap.poll()).append('\n');
			}
		}
		System.out.println(sb);
	}

}