package Code;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Graph { // weighted complete graph
  

    

    public int n;
    public Weight[] weights;
    public double[][] weightMap;
    public ArrayList<Edge> edges;
    public double[][] points;

    public Graph(int n, Weight[] weights, ArrayList<Edge> edges, double[][] points) {
        this.n = n;
        this.weights = weights;
        this.edges = edges;
        this.points = points;
        assert(weights.length == n*n);

        // resolve weight map
        weightMap = new double[n][n];
        for(Weight w : weights) {
            int u = w.u;
            int v = w.v;
            weightMap[u][v] = w.weight;
            weightMap[v][u] = w.weight;
        }
    }
    

    public Graph(Graph other) {
        this.n = other.n;
        this.weights = new Weight[n*n];
        this.edges = new ArrayList<Edge>();
        this.points = other.points.clone();

        for(int i = 0; i < n*n; i++) {
            Weight w = other.weights[i];
            this.weights[i] = new Weight(w.u, w.v, w.weight);
        }
        for(int i = 0; i < other.edges.size(); i++) {
            Edge e = other.edges.get(i);
            this.edges.add(new Edge(e.u, e.v));
        }

        this.weightMap = new double[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                this.weightMap[i][j] = other.weightMap[i][j];
    }

    
    
    // Random Graph Generator for Experiments (  Stackoverflow.com )
    
    public static Graph randomCompleteGraph(int n, double maxFromOrigin) {
        double[][] points = new double[n][2];

        Random rand = new Random();
        for(int i = 0; i < n; i++) {
            points[i][0] = (rand.nextDouble()*2.0-1.0) * maxFromOrigin;
            points[i][1] = (rand.nextDouble()*2.0-1.0) * maxFromOrigin;
        }

        double[][] weights = new double[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                weights[i][j] = Math.sqrt(Math.pow(points[i][0] - points[j][0],2)
                        + Math.pow(points[i][1] - points[j][1],2));

        Weight[] w = new Weight[n*n];
        for(int i = 0; i < n*n; i++) {
            int u = i / n;
            int v = i % n;
            w[i] = new Weight(u, v, weights[u][v]);
        }

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.ensureCapacity(n*n);
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                edges.add(new Edge(i, j));

        return new Graph(n, w, edges, points);
    }
    
   public  Edge getEdge(int u,int v) {
    	for (Iterator iterator = edges.iterator(); iterator.hasNext();) {
			Edge edge = (Edge) iterator.next();
			if(edge.u==u&&edge.v==v)
				return edge;
		}
    	return null;
    }
   
   public Weight getWeigt(Edge edge) {
	   		for (int i = 0; i < weights.length; i++) {
				if(weights[i].u==edge.u&&weights[i].v==edge.v)
					return weights[i];
			}
	   return null;
   }
   
   

}