import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		
		boolean containsUnderbar = line.contains("_");
		boolean containsUpperCase = line.matches(".*[A-Z].*");
		boolean startsWithLowerCase = Character.isLowerCase(line.charAt(0));
		boolean endsWithUnderbar = line.endsWith("_");
		boolean containsDoubleUnderbar = line.matches(".*_{2,}.*");
		
		if (!startsWithLowerCase || endsWithUnderbar || containsDoubleUnderbar) {
			sb.append("Error!");
		}else if (containsUnderbar && !containsUpperCase) { // C to Java
			String[] words = line.split("_");
			sb.append(words[0]);
			for (int i = 1; i < words.length; i++) {
				String word = words[i];
				sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
			}
		} else if (!containsUnderbar) { // Java to C
			int preIdx = 0;
			Matcher matcher = Pattern.compile("[A-Z]").matcher(line);
			while (matcher.find()) {
				int idx = matcher.start();
				sb.append(Character.toLowerCase(line.charAt(preIdx)))
					.append(line.substring(preIdx + 1, idx))
					.append("_");
				preIdx = idx;
			}
			sb.append(Character.toLowerCase(line.charAt(preIdx)))
				.append(line.substring(preIdx + 1, line.length()));
		} else {
			sb.append("Error!");
		}
		
		System.out.println(sb);
		
	}

}