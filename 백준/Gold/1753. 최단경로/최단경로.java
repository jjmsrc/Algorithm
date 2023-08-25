import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main (String[] args) throws IOException {
        Dijkstra.solve();
    }
}

class Dijkstra {
    private static class Pair implements Comparable<Pair>{
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int compareTo(Pair p) {
            return this.b - p.b;
        }
    }

    static void solve() throws IOException {

        // 입력 받기
        final int MAX_WEIGHT = 10;
        int numV, numE;
        int startV;
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<ArrayList<Pair>> edges = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        numV = Integer.parseInt(st.nextToken());
        numE = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        startV = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numV + 1; i++)
            edges.add(new ArrayList<>());
        for (int i = 0; i < numE; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.get(u).add(new Pair(v, w));
        }

        // 길이 계산
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int[] dist = new int[numV + 1];
        final int MAX_DISTANCE = (numV - 1) * MAX_WEIGHT + 1;

        Arrays.fill(dist, MAX_DISTANCE);
        dist[startV] = 0;
        pq.add(new Pair(startV, 0));

        while (!pq.isEmpty()) {
            Pair vertex = pq.poll();
            int u = vertex.a;
            int uDist = vertex.b;
            if (dist[u] < uDist) continue;

            for (var edge : edges.get(u)) {
                int v = edge.a;
                int w = edge.b;
                if (v <= numV && dist[v] > dist[u] + w){
                    dist[v] = dist[u] + w;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        for (int i = 1; i < numV + 1; i++) {
            if (dist[i] < MAX_DISTANCE)
                System.out.println(dist[i]);
            else
                System.out.println("INF");
        }
    }
}
