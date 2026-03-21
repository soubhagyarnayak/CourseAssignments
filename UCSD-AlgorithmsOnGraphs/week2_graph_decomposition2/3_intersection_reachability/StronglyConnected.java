import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
        ArrayList<Integer>[] adjRev = (ArrayList<Integer>[])new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            adjRev[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < adj.length; i++) {
            for (int j : adj[i]) {
                adjRev[j].add(i);
            }
        }
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        for (int i = 0; i < adj.length; i++) {
            if (used[i] == 0) {
                dfs(adj, used, order, i);
            }
        }
        Collections.reverse(order);
        Arrays.fill(used, 0);
        int count = 0;
        for (int i : order) {
            if (used[i] == 0) {
                dfs(adjRev, used, new ArrayList<Integer>(), i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        //write your code here
        used[s] = 1;
        for (int i : adj[s]) {
            if (used[i] == 0) {
                dfs(adj, used, order, i);
            }
        }
        order.add(s);
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

