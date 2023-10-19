import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int n;
        char cmd;
        Node parent;

        public Node(int n, char cmd, Node parent) {
            super();
            this.n = n;
            this.cmd = cmd;
            this.parent = parent;
        }

        int execute(char cmd) {
        	if (cmd == 'D') {
        		return (n * 2) % 10000;
        	} else if (cmd == 'S') {
        		return n > 0 ? n - 1 : 9999;
        	} else if (cmd == 'L') {
        		return (n * 10) % 10000 + n / 1000;
        	} else {
        		return n / 10 + (n % 10) * 1000;
        	}
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            String ans = solve(a, b);
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static String solve(int start, int ans) {
    	
    	boolean[] mem = new boolean[10000];
    	char[] cmds = {'D', 'S', 'L', 'R'};
    	
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, '*', null));
        mem[start] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (int j = 0; j < 4; j++) {
					char cmd = cmds[j];
					int a = node.execute(cmd);
					if (mem[a])
						continue;
					mem[a] = true;
					Node nNode = new Node(a, cmd, node);
					if (a == ans)
						return readCmd(nNode).toString();
					queue.offer(nNode);
				}
            }
        }

        return null;
    }

	private static StringBuilder readCmd(Node nNode) {
		if (nNode.parent == null) {
			return new StringBuilder();
		}
		return readCmd(nNode.parent).append(nNode.cmd);
	}
}