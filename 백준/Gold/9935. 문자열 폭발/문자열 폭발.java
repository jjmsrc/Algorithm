import java.io.*;
import java.util.*;

// 고려 사항 - (비교를 시작한 지점을 저장해야 함, 폭발 문자열이 겹칠 경우)x
// -> 폭발 문자열은 같은 문자를 2개이상 포함하지 않는다.

public class Main {
	
	private static class Node {
		char c;
		Node next;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		String seq = br.readLine().trim();
		right.push('*');
		for (int i = seq.length() - 1; i >= 0; i--) {
			right.push(seq.charAt(i));
		}
		char[] bomb = br.readLine().trim().toCharArray();
		
		while(!right.isEmpty()) {
			char c = right.pop();
			
			if (c == bomb[0]) { // 첫 문자가 폭발 문자열과 같다면
				
				// 폭발 문자열인지 확인
				int i;
				for (i = 0; i < bomb.length; i++) {
					if (c == bomb[i]) {
						left.push(c);
						if (!right.isEmpty())
							c = right.pop();
					} else {
						break;
					}
				}
				
				// 폭발 문자열이라면
				if (i == bomb.length) {
					// 제거
					for (int j = 0; j < bomb.length; j++) {
						left.pop();
					}
					// ... c c44
					right.push(c);
					// 해당 구간에서 만들어진 폭발 문자열을 재검사, 되돌아가기
					for (int j = 0; j < bomb.length && !left.isEmpty(); j++) {
						c = left.pop();
						right.push(c);
					}
				} else { // 폭발 문자열이 아니라면
					right.push(c);
				}
			} else {
				left.push(c);
			}
		}
		
		left.pop();
		if (left.size() == 0) {
			System.out.println("FRULA");
		} else {
			char[] out = new char[left.size()];
			for (int i = left.size() - 1; i >= 0; i--) {
				out[i] = left.pop();
			}
			
			System.out.println(out);
		}

	}

}