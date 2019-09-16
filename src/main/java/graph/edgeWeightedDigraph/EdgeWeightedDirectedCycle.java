package graph.edgeWeightedDigraph;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 加权有向图中寻找有向环
 *
 * @author suchao
 * @date 2019/9/16
 * @see edu.princeton.cs.algs4.EdgeWeightedDirectedCycle
 */
public class EdgeWeightedDirectedCycle {

    /**
     * 这个顶点上是否调用过 dfs()
     */
    private boolean[] marked;

    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     * <p>
     * 其实 edgeTo[] 就是一颗由父链接表示的树
     */
    private DirectedEdge[] edgeTo;

    /**
     * 如果找到一个环，则将环中的顶点压入栈中保存
     */
    private Stack<DirectedEdge> cycle;

    /**
     * 保存递归调用期间栈上的所有顶点（一次深度优先遍历完，就恢复原值）
     */
    private boolean[] onStack;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph digraph) {
        onStack = new boolean[digraph.V()];
        edgeTo = new DirectedEdge[digraph.V()];
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            // 如果已经有环，直接返回
            if (this.hasCycle()) {
                return;
            }
            // 继续向下标记
            else if (!marked[w]) {
                edgeTo[w] = edge;
                dfs(digraph, w);
            }
            // 发现顶点已经被标记，查看是否在栈中（其实就是检测是否在现在遍历的这一条有向路径上），
            // 如果在的化，则证明找到了一个环，开始将环的顶点压入栈中
            else if (onStack[w]) {
                cycle = new Stack<DirectedEdge>();

                DirectedEdge f = edge;
                while (f.from() != w) {
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);

                return;
            }
        }
        // 遍历完这条路径了，恢复 onStack 上的值
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        // create random DAG with V vertices and E edges; then add F random edges
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++) {
            vertices[i] = i;
        }
        StdRandom.shuffle(vertices);
        for (int i = 0; i < E; i++) {
            int v, w;
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while (v >= w);
            double weight = StdRandom.uniform();
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        // add F extra edges
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform(0.0, 1.0);
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        StdOut.println(G);

        // find a directed cycle
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Cycle: ");
            for (DirectedEdge e : finder.cycle()) {
                StdOut.print(e + " ");
            }
            StdOut.println();
        }

        // or give topologial sort
        else {
            StdOut.println("No directed cycle");
        }
    }
}
