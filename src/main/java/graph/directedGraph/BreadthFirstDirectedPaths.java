package graph.directedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import graph.graph.BreadthFirstPaths;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用广度优先遍历查找图中的路径
 * <p>
 * 可以解决单点最短路径的问题
 *
 * <p>
 * args: ./src/main/resources/tinyDG.txt 3
 *
 * @author suchao
 * @date 2019/9/11
 * @see BreadthFirstPaths
 */
public class BreadthFirstDirectedPaths {

    /**
     * 到达该顶点的最短路径是否已知
     */
    private boolean[] marked;

    /**
     * 到达该顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;

    /**
     * 起点
     */
    private final int s;

    public BreadthFirstDirectedPaths(Digraph digraph, int s) {
        this.s = s;
        this.marked = new boolean[digraph.V()];
        this.edgeTo = new int[digraph.V()];
        bfs(digraph, s);
    }

    private void bfs(Digraph digraph, int s) {
        // 使用一个队列来达到一层层的遍历
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : digraph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);
                }
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
        // use as s stack
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

        BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(digraph, s);
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
