package Code;

public class Edge {
    public int u,v;
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
	@Override
	public String toString() {
		return "Edge [from=" + u + ", to=" + v + "]";
	}
}