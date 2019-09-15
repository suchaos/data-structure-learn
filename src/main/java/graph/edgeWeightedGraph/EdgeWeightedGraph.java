package graph.edgeWeightedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 加权无向图的数据类型
 * <p>
 * args: ./src/main/resources/tinyEWG.txt
 *
 * @author suchao
 * @date 2019/9/13
 * @see edu.princeton.cs.algs4.EdgeWeightedGraph
 */
public class EdgeWeightedGraph {

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
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Edge>[]) new Bag[this.V];
        for (int v = 0; v < this.V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int v = 0; v < E; v++) {
            Edge edge = new Edge(in.readInt(), in.readInt(), in.readDouble());
            this.addEdge(edge);
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);

        this.adj[v].add(edge);
        this.adj[w].add(edge);
        this.E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<>();
        for (int v = 0; v < this.V; v++) {
            for (Edge edge : this.adj(v)) {
                if (edge.other(v) > v) {
                    bag.add(edge);
                }
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
            for (Edge e : this.adj[v]) {
                s.append(e).append("  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(in);
        StdOut.println(edgeWeightedGraph);
    }
}
