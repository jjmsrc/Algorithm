import java.io.*;
import java.util.*;

// 고려 사항 - (비교를 시작한 지점을 저장해야 함, 폭발 문자열이 겹칠 경우)x
// -> 폭발 문자열은 같은 문자를 2개이상 포함하지 않는다.

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		List<Character> seq = new LinkedList<>();

		String line = br.readLine().trim();
		for (char c : line.toCharArray()) {
			seq.add(c);
		}
        seq.add('*');

		line = br.readLine().trim();
		char[] bomb = line.toCharArray();

		ListIterator<Character> it = seq.listIterator();

		while (it.hasNext()) {
			char c = it.next();
			if (c == bomb[0]) { // 첫 문자가 같다면
				// 문자열 길이만큼 체크
				int i;
				for (i = 0; i < bomb.length && c == bomb[i] && it.hasNext(); i++, c = it.next())
					;
				it.previous();
				// 맨 끝 문자열일 경우 처리
				if (i == bomb.length - 1 && c == bomb[i]) {
					i = bomb.length;
					it.next();
				}
				// 만약 폭발문자열이 맞다면
				if (i == bomb.length) {
					// 문자열 제거
					for (int j = 0; j < bomb.length; j++) {
						it.previous();
						it.remove();
					}
					// 합쳐진 경우 고려해서 되돌아가기
					for (int j = 0; j < bomb.length && it.hasPrevious(); j++) { 
						it.previous();
					}
				}
			}
		}

		seq.remove(seq.size() - 1);
		if (seq.size() == 0) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (char c : seq) {
				sb.append(c);
			}
			System.out.println(sb);
		}

	}

}