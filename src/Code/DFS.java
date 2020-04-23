package Code;


import java.util.*;


public class DFS {
    public static  ArrayList<Integer> dfs(Graph g) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(g.edges.isEmpty())
            return res;

        // build adjacency list
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new LinkedList[g.n];
        for(int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<Integer>();

        for(Edge e : g.edges) {
            adj[e.u].add(e.v);
            adj[e.v].add(e.u);
        }

        boolean[] visited = new boolean[g.n];
        dfs_helper(g.edges.get(0).u, adj, visited, res);

        return res;
    }

    private static  void dfs_helper(int vertex, List<Integer>[] adj,
                                   boolean visited[], ArrayList<Integer> res) {
        res.add(vertex);
        visited[vertex] = true;

        List<Integer> lst = adj[vertex];
        for(Integer u : lst) {
            if(visited[u])
                continue;

            dfs_helper(u, adj, visited, res);
            res.add(vertex);
        }
    }

    public static  ArrayList<Integer> shortcut(ArrayList<Integer> tour) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(tour.isEmpty())
            return res;
        //To remove duplicates
        Set<Integer> set = new HashSet<Integer>();

        for(Integer v : tour) {
            if(set.contains(v))
                continue;

            res.add(v);
            set.add(v);
        }

        res.add(tour.get(0));
        return res;
    }
}