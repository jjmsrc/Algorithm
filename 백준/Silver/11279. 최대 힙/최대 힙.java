import java.util.Scanner;

public class Main {

	static class Heap {
		int[] items;
		int size = 0;

		public Heap() {
			items = new int[100];
		}

		public Heap(int cap) {
			items = new int[cap];
		}

		public int offer(int e) {
			items[++size] = e;
			for (int i = size, ni = size / 2; ni >= 1; i /= 2, ni /= 2) {
				if (comp(items[i], items[ni]) < 0)
					break;
				int tmp = items[i];
				items[i] = items[ni];
				items[ni] = tmp;
			}
			return e;
		}

		public int peak() {
			return items[size];
		}

		public int poll() {
			if (size == 0)
				return Integer.MIN_VALUE;
			int a = items[1];
			items[1] = items[size--];
			int i = 1, ni = 2;
			while (ni <= size) {

				if (ni == size && comp(items[i], items[ni]) < 0) {
					int tmp = items[i];
					items[i] = items[ni];
					items[ni] = tmp;
					break;
				}

				ni = comp(items[ni], items[ni + 1]) > 0 ? ni : ni + 1;

				if (comp(items[i], items[ni]) > 0)
					break;

				int tmp = items[i];
				items[i] = items[ni];
				items[ni] = tmp;

				i = ni;
				ni = i * 2;
			}
			return a;
		}

		private int comp(int x, int y) {
			return x - y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Heap heap = new Heap(n + 1);

		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			if (a == 0) {
				int b = heap.poll();
				System.out.println(b == Integer.MIN_VALUE ? 0 : b);
			} else {
				heap.offer(a);
			}
		}
	}
}