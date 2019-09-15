package graph.edgeWeightedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 最小生成树的 Prim 算法的即时实现
 * <p>
 * args: ./src/main/resources/tinyEWG.txt
 *
 * @author suchao
 * @date 2019/9/15
 * @see edu.princeton.cs.algs4.PrimMST
 */
public class PrimMST {

    /**
     * 距离树最近的边
     */
    private Edge[] edgeTo;

    /**
     * distTo[w] = edgeTo[w].wight()
     */
    private double[] distTo;

    /**
     * 如果 v 在树中，则为 true
     */
    private boolean[] marked;

    /**
     * 有效的横切边
     */
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(graph.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(graph, pq.delMin());
        }
    }

    /**
     * 将顶点 v 添加到树中，更新数据
     *
     * @param graph 加权无向图
     * @param v     顶点 v
     */
    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adj(v)) {
            int w = edge.other(v);
            if (marked[w]) {
                continue;
            }
            // 连接 w 和树的最佳边 Edge 变为 e
            if (edge.weight() < distTo[w]) {
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge edge = edgeTo[v];
            if (edge != null) {
                mst.enqueue(edge);
            }
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(graph);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
