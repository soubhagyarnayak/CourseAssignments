import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clustering {
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
        int n = x.length;

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double w = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                edges.add(new Edge(i, j, w));
            }
        }
        edges.sort((e1, e2) -> Double.compare(e1.weight, e2.weight));
        UnionFind uf = new UnionFind(n);
        int numClusters = n;
        for (Edge edge : edges) {
            if (uf.find(edge.u) != uf.find(edge.v)) {   
                if (numClusters == k) {
                    return edge.weight;
                }
                uf.union(edge.u, edge.v);
                numClusters--;
            }
        }
        return -1.;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    public static class Edge {
        public int u;
        public int v;
        public double weight;

        public Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

