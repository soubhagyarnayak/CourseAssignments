import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
        int[] visited = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            if (visited[i]==0) {
                if (dfs(adj, visited, i)) {
                    return 1;
                }        
            }
        }   
        return 0;
    }

    private static boolean dfs(ArrayList<Integer>[] adj, int[] visited, int v) {
        visited[v] = 1;
        for (int i : adj[v]) {
            if (visited[i]==0) {
                if (dfs(adj, visited, i)) {
                    return true;
                }
            } else if(visited[i]==1) {
                return true;
            }
        }
        visited[v] = 2;
        return false;
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
        }
        System.out.println(acyclic(adj));
    }
}

