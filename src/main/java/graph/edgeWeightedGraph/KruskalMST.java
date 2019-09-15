package graph.edgeWeightedGraph;

import edu.princeton.cs.algs4.*;

/**
 * 最小生成树的 Kruskal 算法
 * <p>
 * args: ./src/main/resources/tinyEWG.txt
 *
 * @author suchao
 * @date 2019/9/15
 * @see edu.princeton.cs.algs4.KruskalMST
 */
public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph graph) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge edge : graph.edges()) {
            pq.insert(edge);
        }
        UF uf = new UF(graph.V());

        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);

            if (uf.connected(v, w)) {
                continue;
            }

            uf.union(v, w);
            mst.enqueue(edge);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge edge : edges()) {
            weight += edge.weight();
        }
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(graph);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
