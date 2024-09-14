import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	private static class Jewel {
		int m;
		int v;

		Jewel(int m, int v) {
			super();
			this.m = m;
			this.v = v;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int nJewels = Integer.parseInt(st.nextToken());
		int nBags = Integer.parseInt(st.nextToken());

		Jewel[] jewels = new Jewel[nJewels];
		int[] bags = new int[nBags];

		for (int i = 0; i < jewels.length; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewels[i] = new Jewel(m, v);
		}

		for (int i = 0; i < bags.length; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}

		long ans = solve(jewels, bags);

		System.out.println(ans);
	}

	private static long solve(Jewel[] jewels, int[] bags) {

		long sum = 0;

		Arrays.sort(jewels, (a, b) -> b.v - a.v == 0 ? a.m - b.m : b.v - a.v);
		TreeMap<Integer, Integer> bagTreeMap = new TreeMap<>();

		for (int bag : bags) {
			Integer cnt = bagTreeMap.get(bag);
			bagTreeMap.put(bag, cnt == null ? 1 : cnt + 1);
		}
		
		for (Jewel jewel : jewels) {
			Entry<Integer, Integer> higherBag = bagTreeMap.ceilingEntry(jewel.m);
			if (higherBag == null)
				continue;
			else {
				if (higherBag.getValue() == 1) {
					bagTreeMap.remove(higherBag.getKey());
				} else {
					bagTreeMap.put(higherBag.getKey(), higherBag.getValue() - 1);
				}
				sum += jewel.v;
			}
		}

		return sum;
	}

}