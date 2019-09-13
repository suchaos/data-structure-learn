package graph.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Graph
 *
 * @author suchao
 * @date 2019/9/2
 */
public class Graph {
    /**
     * 顶点数目
     */
    private final int V;

    /**
     * 边的数目
     */
    private int E;

    /**
     * 邻接表
     */
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        this.E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        int degree = 0;
        for (int w : this.adj(v)) {
            degree++;
        }
        return degree;
    }

    public int maxDegree() {
        int max = 0;
        int degree;
        for (int v = 0; v < this.V(); v++) {
            degree = this.degree(v);
            if (degree > max) {
                max = degree;
            }
        }
        return max;
    }

    public double avgDegree() {
        return 2.0 * this.E() / this.V();
    }

    /**
     * 计算自环的个数
     *
     * @return 自环的个数
     */
    public int numberOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < this.V(); v++) {
            for (int w : this.adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        // 因为在无向图中每条边被记过两次
        return count / 2;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder().append(V).append(" vertices, ").
                append(E).append(" edges").append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        String prefix = "./src/main/resources/";
        String fileName = "tinyG.txt";
        In in = new In(prefix + fileName);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}
