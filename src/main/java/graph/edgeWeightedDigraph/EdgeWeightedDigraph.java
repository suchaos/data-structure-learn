package graph.edgeWeightedDigraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 加权有向图的数据类型
 * <p>
 * args: ./src/main/resources/tinyEWD.txt
 *
 * @author suchao
 * @date 2019/9/15
 * @see edu.princeton.cs.algs4.EdgeWeightedDigraph
 */
public class EdgeWeightedDigraph {

    /**
     * 顶点总数
     */
    private final int V;

    /**
     * 边的总数
     */
    private int E;

    /**
     * 邻接表
     */
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<DirectedEdge>[]) new Bag[V];

        for (int v = 0; v < V; v++) {
            this.adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int edges = in.readInt();
        for (int i = 0; i < edges; i++) {
            DirectedEdge edge = new DirectedEdge(in.readInt(), in.readInt(), in.readDouble());
            this.addEdge(edge);
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(DirectedEdge edge) {
        this.adj[edge.from()].add(edge);
        this.E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return this.adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < this.V; v++) {
            for (DirectedEdge edge : this.adj(v)) {
                bag.add(edge);
            }
        }
        return bag;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.V).append(" ").append(this.E).append("\n");
        for (int v = 0; v < this.V; v++) {
            s.append(v).append(": ");
            for (DirectedEdge e : this.adj[v]) {
                s.append(e).append("  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
        StdOut.println(graph);
    }
}
