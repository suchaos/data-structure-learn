package graph.directedGraph;

import edu.princeton.cs.algs4.StdOut;
import graph.edgeWeightedDigraph.EdgeWeightedDigraph;
import graph.edgeWeightedDigraph.EdgeWeightedDirectedCycle;

/**
 * 拓扑排序
 * <p>
 * 有向无环图的逆后序排序是拓扑排序
 * <p>
 * args: ./src/main/resources/jobs.txt "/"
 *
 * @author suchao
 * @date 2019/9/12
 * @see
 */
public class Topological {

    /**
     * 顶点的拓扑排序
     */
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectedCycle cyclefinder = new DirectedCycle(digraph);
        // 拓扑排序的前提是没有有向环
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(digraph);
            order = dfs.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph digraph) {
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(digraph);
        // 拓扑排序的前提是没有有向环
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(digraph);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, separator);

        Topological topological = new Topological(sg.digraph());

        for (int v : topological.order()) {
            StdOut.println(v + " " + sg.nameOf(v));
        }
    }
}
