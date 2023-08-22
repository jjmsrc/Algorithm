import java.io.*;
import java.util.*;

public class Main {

	static int L, C;

	static boolean np(int[] p) {

		int N = p.length;
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			i--;

		if (i == 0)
			return false;

		int j = N - 1;
		while (p[i - 1] >= p[j])
			j--;

		int tmp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = tmp;

		int k = N - 1;
		while (i < k) {
			tmp = p[i];
			p[i] = p[k];
			p[k] = tmp;
			i++;
			k--;
		}

		return true;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		List<Character> vowels = new ArrayList<Character>(); // 모음
		List<Character> consonants = new ArrayList<Character>(); // 자음
		List<String> result = new ArrayList<String>();
		
		String line = br.readLine();
		for (int i = 0; i < C; i++) {
			char c = line.charAt(i << 1);
			switch (c) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				vowels.add(c);
				break;
			default:
				consonants.add(c);
			}
		}

		int nv = vowels.size();
		int nc = consonants.size();
		int minv = Math.max(1, L - nc);
		int minc = Math.max(2, L - nv);

		int[] pv = new int[nv];
		int[] pc = new int[nc];
		for (int rv = minv; rv <= L - minc; rv++) {
			int rc = L - rv;

			for (int i = 0; i < nv; i++) {
				pv[nv - 1 - i] = i < rv ? 1 : 0;
			}
			do {
				for (int i = 0; i < nc; i++) {
					pc[nc - 1 - i] = i < rc ? 1 : 0;
				}
				do {
					int cnt = 0;
					int iv = 0, ic = 0;
					char[] code = new char[L];
					while(cnt < rv) {
						if (pv[iv] == 1)
							code[cnt++] = vowels.get(iv);
						iv++;
					}
					while(cnt < L) {
						if (pc[ic] == 1)
							code[cnt++] = consonants.get(ic);
						ic++;
					}
					Arrays.sort(code);
					result.add(new String(code));
				} while (np(pc));
			} while (np(pv));
		}
		
		Collections.sort(result);
		for (String res : result) {
			sb.append(res).append("\n");
		}
		System.out.print(sb);

	}

}