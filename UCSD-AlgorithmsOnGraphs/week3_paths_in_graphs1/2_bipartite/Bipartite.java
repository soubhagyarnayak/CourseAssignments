import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        int[] color = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            if (color[i] == 0) {
                if (!bfs(adj, color, i)) {
                    return 0;
                }
            }
        }
        return 1;
    }

    private static boolean bfs(ArrayList<Integer>[] adj, int[] color, int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        color[s] = 1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj[node]) {
                if (color[neighbor] == 0) {
                    color[neighbor] = -color[node];
                    queue.add(neighbor);
                } else if (color[neighbor] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

