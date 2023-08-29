import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());
		int[] mem = new int[X + 1];
		mem[1] = 0;
		
		for (int i = 2; i <= X; i++) {
			mem[i] = mem[i - 1] + 1;
			if (i % 2 == 0 && mem[i] > mem[i / 2] + 1)
				mem[i] = mem[i / 2] + 1;
			if (i % 3 == 0 && mem[i] > mem[i / 3] + 1)
				mem[i] = mem[i / 3] + 1;
		}

		System.out.println(mem[X]);
	}
}