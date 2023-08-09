import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		List<Character> str = new LinkedList<>();
		for (char c : input.toCharArray()) {
			str.add(c);
		}
		
		int nCommand = Integer.parseInt(br.readLine());
		
		ListIterator<Character> iter = str.listIterator();
		
		while(iter.hasNext())
			iter.next();
		
		for (int i = 0; i < nCommand; i++) {
			String command = br.readLine();
			switch (command.charAt(0)) {
			case 'L':
				if (iter.hasPrevious())
					iter.previous();
				break;
			case 'D':
				if (iter.hasNext())
					iter.next();
				break;
			case 'B':
				if (iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
				break;
			case 'P':
				iter.add(command.charAt(2));
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Character c : str) {
			sb.append(c);
		}
		
		System.out.println(sb);

	}
}