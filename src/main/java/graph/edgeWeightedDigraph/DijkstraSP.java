package graph.edgeWeightedDigraph;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * 加权有向图的最短路径的 Dijkstra 算法
 *
 * @author suchao
 * @date 2019/9/16
 */
public class DijkstraSP {

    /**
     * 从起点到一个顶点的已知路径上的最短路径上的最后一条边
     * <p>
     * 其实 edgeTo[] 就是一颗由父链接表示的树
     */
    private DirectedEdge[] edgeTo;

    /**
     * 到达起点的距离 distTo[v] 为从 s 到 v 的已知最短路径的长度
     */
    private double[] distTo;

    /**
     * 保存需要被放松的顶点并确认下一个被放松的顶点
     */
    private IndexMinPQ<Double> pq;

    /**
     * 构建一个从 s 出发的最短路径树
     *
     * @param digraph 给定的加权有向图
     * @param s       起点
     */
    public DijkstraSP(EdgeWeightedDigraph digraph, int s) {
        this.edgeTo = new DirectedEdge[digraph.V()];
        this.distTo = new double[digraph.V()];
        this.pq = new IndexMinPQ<>(digraph.V());

        for (int v = 0; v < digraph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(digraph, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v) {
        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;

                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]) {
            path.push(edge);
        }
        return path;
    }

    public static void main(String[] args) {

    }
}
