package graph.edgeWeightedDigraph;

import edu.princeton.cs.algs4.StdOut;

/**
 * 加权有向图中的有向边
 *
 * @author suchao
 * @date 2019/9/15
 */
public class DirectedEdge {

    /**
     * 边的起点
     */
    private final int v;

    /**
     * 边的终点
     */
    private final int w;

    /**
     * 边的权重
     */
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("%d->%d (%.2f)", v, w, weight);
    }

    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        StdOut.println(e);
    }
}
