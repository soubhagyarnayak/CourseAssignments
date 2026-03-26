import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
      //write your code here
      int n = adj.length;
      distance[s] = 0;
      for (int i = 0; i < n - 1; i++) {
          for (int u = 0; u < n; u++) {
              for (int j = 0; j < adj[u].size(); j++) {
                  int v = adj[u].get(j);
                  long w = cost[u].get(j);
                  if (distance[u] != Long.MAX_VALUE && distance[u] + w < distance[v]) {
                      distance[v] = distance[u] + w;
                      reachable[v] = 1;
                  }
              }
          }
      }

      Queue<Integer> queue = new LinkedList<>();
      boolean[] enqueued = new boolean[n];

      for (int u = 0; u < n; u++) {
        for (int j = 0; j < adj[u].size(); j++) {
            int v = adj[u].get(j);
            long w = cost[u].get(j);
            if (distance[u] != Long.MAX_VALUE && distance[u] + w < distance[v]) {
                queue.add(v);
                enqueued[v] = true;
            }
        }
      }

      while(!queue.isEmpty()) {
          int node = queue.poll();
          shortest[node] = 0;
          for (int neighbor : adj[node]) {
              if (!enqueued[neighbor]) {
                  queue.add(neighbor);
                  enqueued[neighbor] = true;
              }
          }
      }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

