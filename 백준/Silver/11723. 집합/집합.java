import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int nOps = Integer.parseInt(br.readLine());
		
		boolean[] set = new boolean[21];
		
		for (int i = 0; i < nOps; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int e = 0;
			switch(cmd) {
			case "add":
				e = Integer.parseInt(st.nextToken());
				set[e] = true;
				break;
			case "remove":
				e = Integer.parseInt(st.nextToken());
				set[e] = false;
				break;
			case "check":
				e = Integer.parseInt(st.nextToken());
				sb.append(set[e] ? 1 : 0).append('\n');
				break;
			case "toggle":
				e = Integer.parseInt(st.nextToken());
				set[e] = !set[e];
				break;
			case "all":
				Arrays.fill(set, true);
				break;
			case "empty":
				Arrays.fill(set, false);
				break;
			}
		}
		
		System.out.println(sb);
		
	}

}