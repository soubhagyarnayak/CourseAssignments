import java.util.PriorityQueue;
import java.util.Scanner;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        int n = x.length;
        boolean[] visited = new boolean[n];
        double[] dist = new double[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Double.MAX_VALUE;
        }
        dist[0] = 0.0;
        PriorityQueue<PQNode> pq = new PriorityQueue<>();
        pq.add(new PQNode(0.0, 0));

        while (!pq.isEmpty()) {
            PQNode node = pq.poll();
            int u = node.vertex;
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            result += node.dist;
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    double w = Math.sqrt(Math.pow(x[u] - x[v], 2) + Math.pow(y[u] - y[v], 2));
                    if (w < dist[v]) {
                        dist[v] = w;
                        pq.add(new PQNode(w, v));
                    }
                }
            }
        }  
        return result;
    }

    public static class PQNode implements Comparable<PQNode> {
        public double dist;
        public int vertex;

        public PQNode(double dist, int vertex) {
            this.dist = dist;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(PQNode other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}

