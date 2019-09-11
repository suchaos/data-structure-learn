package directedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import graph.DepthFirstPaths;
import graph.Graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 有向图的寻路
 * <p>
 * 单点有向路径 P368
 * <p>
 * Data files:
 * http://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 * http://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 * http://algs4.cs.princeton.edu/42digraph/largeDG.txt
 * <p>
 * args: ./src/main/resources/tinyDG.txt 3
 *
 * @author suchao
 * @date 2019/9/11
 * @see DepthFirstPaths
 */
public class DepthFirstDirectedPaths {

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

    public DepthFirstDirectedPaths(Digraph digraph, int s) {
        this.s = s;
        this.marked = new boolean[digraph.V()];
        this.edgeTo = new int[digraph.V()];
        dfs(digraph, s);
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
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
        Digraph digraph = new Digraph(new In(args[0]));
        StdOut.println(digraph);
        int s = Integer.parseInt(args[1]);

        DepthFirstDirectedPaths paths = new DepthFirstDirectedPaths(digraph, s);
        for (int v = 0; v < digraph.V(); v++) {
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
