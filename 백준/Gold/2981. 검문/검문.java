import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Set<Integer> ans = new HashSet<>();
		
		int n = Integer.parseInt(br.readLine());
		List<Integer> nums = new ArrayList<>(n);

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			nums.add(num);
		}
		
		Collections.sort(nums, (a, b) -> b - a);
		
		int maxCD = nums.get(0) - nums.get(1);
		for (int i = 0, ei = nums.size() - 1; i < ei; i++) {
			for (int j = i + 1, ej = nums.size(); j < ej; j++) {
				int diff = nums.get(i) - nums.get(j);
				maxCD = GCD(maxCD, diff);
			} 
		}
		
		findCommonDivisors(ans, maxCD);
		
		StringBuilder sb = ans.stream()
			.sorted()
			.collect(StringBuilder::new, (a, b) -> a.append(b).append(' '), (a, b) -> {});
		
		System.out.println(sb);

	}
	
	private static int GCD(int a, int b) {
		
		int ma = Math.max(a, b);
		int mb = Math.min(a, b);
		
		int r = 0;
		
		while((r = ma % mb) != 0) {
			ma = mb;
			mb = r;
		}
		
		return mb;
	}
	
	private static void findCommonDivisors(Set<Integer> ans, int n) {
		
		for (int i = 1, en = (int)Math.sqrt(n) + 1; i < en; i++) {
			if (n % i != 0) continue;
			ans.add(i);
			ans.add(n / i);
		}
		
		ans.remove(1);
		
	}

}