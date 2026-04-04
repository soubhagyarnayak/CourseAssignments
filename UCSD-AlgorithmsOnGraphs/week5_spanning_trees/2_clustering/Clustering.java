import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        int n = x.length;

        boolean[] visited = new boolean[n];
        double[] dist = new double[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;

        List<Double> mstEdges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int u = -1;

            // find min dist vertex
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }
            }

            visited[u] = true;

            if (i > 0) {
                mstEdges.add(Math.sqrt(dist[u])); // actual edge weight
            }

            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    double dx = x[u] - x[v];
                    double dy = y[u] - y[v];
                    double w = dx * dx + dy * dy;

                    if (w < dist[v]) {
                        dist[v] = w;
                        parent[v] = u;
                    }
                }
            }
        }

        // sort MST edges
        Collections.sort(mstEdges);

        // remove k-1 largest edges
        return mstEdges.get(mstEdges.size() - (k - 1));
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

