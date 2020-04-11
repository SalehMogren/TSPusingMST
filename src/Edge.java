
public class Edge implements Comparable<Edge>{
	int src,dest,weight;
	
	public int compareTo(Edge compareEdge) {
		return this.weight-compareEdge.weight;
	}
}


