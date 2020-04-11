import java.util.Arrays;

public class MST {
	// A utility function to find set of an element i
	// (uses path compression technique)
	static int find(subset subsets[], int i) {
		// find root and make root as parent of i (path compression)
		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);

		return subsets[i].parent;
	}

	// A function that does union of two sets of x and y
	// (uses union by rank)
	static void Union(subset subsets[], int x, int y) {
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
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	// The main function to construct MST using Kruskal's algorithm
public	static Edge[] KruskalMST(Graph g) {
		Edge result[] = new Edge[g.V]; // This will store the resultant MST
		int e = 0; // An index variable, used for result[]
		int i = 0; // An index variable, used for sorted edges
		for (i = 0; i < g.V; ++i)
			result[i] = new Edge();

		// Step 1: Sort all the edges in non-decreasing order of their
		// weight.
		Edge[] g_e = g.edge;
		Arrays.sort(g_e);

		// Allocate memory for creating V ssubsets
		subset subsets[] = new subset[g.V];
		for (i = 0; i < g.V; ++i)
			subsets[i] = new subset();

		// Create V subsets with single elements
		for (int v = 0; v < g.V; ++v) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}

		i = 0; // Index used to pick next edge

		// Number of edges to be taken is equal to V-1
		while (e < g.V - 1) {
			// Step 2: Pick the smallest edge. And increment
			// the index for next iteration
			Edge next_edge = new Edge();
			next_edge = g_e[i++];

			int x = find(subsets, next_edge.src);
			int y = find(subsets, next_edge.dest);

			// If including this edge does't cause cycle,
			// include it in result and increment the index
			// of result for next edge
			if (x != y) {
				result[e++] = next_edge;
				Union(subsets, x, y);
			}
			// Else discard the next_edge
		}
		return result;
	}

	public static void main(String[] args) {

		// create graph

		/*
		 * Let us create following weighted graph 10 0--------1 | \ | 6| 5\ |15 | \ |
		 * 2--------3 4
		 */
		int V = 4; // Number of vertices in graph
		int E = 5; // Number of edges in graph
		Graph graph = new Graph(V, E);

// add edge 0-1 
		graph.edge[0].src = 0;
		graph.edge[0].dest = 1;
		graph.edge[0].weight = 10;

// add edge 0-2 
		graph.edge[1].src = 0;
		graph.edge[1].dest = 2;
		graph.edge[1].weight = 6;

// add edge 0-3 
		graph.edge[2].src = 0;
		graph.edge[2].dest = 3;
		graph.edge[2].weight = 5;

// add edge 1-3 
		graph.edge[3].src = 1;
		graph.edge[3].dest = 3;
		graph.edge[3].weight = 15;

// add edge 2-3 
		graph.edge[4].src = 2;
		graph.edge[4].dest = 3;
		graph.edge[4].weight = 4;
		
		Edge[] mst = KruskalMST(graph);
		 for (int i = 0; i < mst.length; ++i) 
	            System.out.println(mst[i].src+" -- " +  
	            		mst[i].dest+" == " + mst[i].weight); 
	    } 
	
	}

