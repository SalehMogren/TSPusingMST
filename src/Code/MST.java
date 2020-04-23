package Code;
import java.util.Arrays;

public class MST {
    public static Graph kruskal(Graph completeGraph) {
        Graph ret = new Graph(completeGraph); // create a copy
        ret.edges.clear();

        int n = ret.n;

        Arrays.sort(ret.weights);

        Subset subsets[] = new Subset[n];
        for(int i = 0; i < n; i++)  {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        for(int i = 0; i < ret.weights.length && ret.edges.size() < n-1; i++) {
            Weight w1 = ret.weights[i];
            if(w1.weight == 0.0)
                continue;

            int x = find(subsets, w1.u);
            int y = find(subsets, w1.v);

            if(x != y) {
                union(subsets, x, y);
                Edge e1 = new Edge(w1.u, w1.v);
                ret.edges.add(e1);
            }
        }

        return ret;
    }


    // geeksforgeeks.com
    
    static class Subset
    {
        public int parent, rank;
    };

    // A utility function to find set of an element i
    static int find(Subset subsets[], int i)
    {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y
    // (uses union by rank)
    static void union(Subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are same, then make one as root and increment
            // its rank by one
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
}