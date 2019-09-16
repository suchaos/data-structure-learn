package graph.edgeWeightedDigraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import graph.directedGraph.Topological;

/**
 * 无环加权有向图的最短路径算法
 * <p>
 * args: ./src/main/resources/tinyEWDAG.txt 5
 *
 * @author suchao
 * @date 2019/9/16
 * @see edu.princeton.cs.algs4.AcyclicSP
 */
public class AcyclicSP {

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

    public AcyclicSP(EdgeWeightedDigraph digraph, int s) {
        this.edgeTo = new DirectedEdge[digraph.V()];
        this.distTo = new double[digraph.V()];

        for (int v = 0; v < digraph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        Topological topological = new Topological(digraph);
        for (int v : topological.order()) {
            relax(digraph, v);
        }
    }

    private void relax(EdgeWeightedDigraph digraph, int v) {
        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
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
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // find shortest path from s to each other vertex in DAG
        AcyclicSP sp = new AcyclicSP(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, sp.distTo(v));
                for (DirectedEdge e : sp.pathTo(v)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, v);
            }
        }
    }
}
