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

        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }
            }
            visited[u] = true;
            result += Math.sqrt(dist[u]); 
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int dx = x[u] - x[v];
                    int dy = y[u] - y[v]; 
                    double w = dx * dx + dy * dy;
                    if (w < dist[v]) {
                        dist[v] = w;
                    }
                }
            }
        }
        return result;
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

