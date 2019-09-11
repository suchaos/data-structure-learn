package graph;

import directedGraph.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 使用深度优先遍历查找图中的路径
 * <p>
 * 只是找到给定顶点与图中所有顶点的联通的其中一条路径
 * <p>
 * 深度优先遍历可以解决以下两个问题：
 * <li>
 * 1. 连通性：给定一幅图，可以解决两个顶点是否能够联通
 * </li>
 * <li>
 * 2. 单点路径：给定一幅图和一个起点 s,可以解决从 s 到给定目的顶点 v是否存在一条路径
 * </li>
 * <p>
 * 注意：但是无法解决找到最短路径的问题（使用广度优先遍历来解决）
 * <p>
 * args: ./src/main/resources/tinyCG.txt 0
 * <p>
 * args: ./src/main/resources/tinyG.txt 0
 * <p>
 * ./src/main/resources/mediumG.txt 0
 *
 * @author suchao
 * @date 2019/9/2
 * @see DepthFirstDirectedPaths
 */
public class DepthFirstPaths {

    /**
     * 这个顶点上是否调用过 dfs()
     */
    private boolean[] marked;

    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     * <p>
     * 其实 edgeTo[] 就是一颗由父链接表示的树
     */
    private int[] edgeTo;

    /**
     * 起点
     */
    private final int s;

    public DepthFirstPaths(Graph graph, int s) {
        this.s = s;
        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        // 当作 stack 使用
        Deque<Integer> stack = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        StdOut.println(graph);
        int s = Integer.parseInt(args[1]);

        DepthFirstPaths paths = new DepthFirstPaths(graph, s);
        for (int v = 0; v < graph.V(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (paths.hasPathTo(v)) {
                for (int x : paths.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
            } else {
                StdOut.print("not connected");
            }
            StdOut.println();
        }
    }
}
