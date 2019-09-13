package graph.directedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的数据类型
 * <p>
 * args: ./src/main/resources/tinyDG.txt
 *
 * @author suchao
 * @date 2019/9/9
 */
public class Digraph {

    private final int V;

    private int E;

    private Bag<Integer>[] adj;

    /**
     * 创建一幅含有 V 个顶点但没有边的有向图
     *
     * @param V 顶点个数
     */
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        //List<Integer>[] adjArray = (List<Integer>[])new List[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    /**
     * 从输入流 in 中读取一幅有向图
     *
     * @param in 输入流
     */
    public Digraph(In in) {
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
        this.E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
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
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);
        StdOut.println(digraph);
    }
}
