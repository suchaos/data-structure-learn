package graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 使用深度优先遍历查找图中的路径
 *
 * @author suchao
 * @date 2019/9/2
 */
public class DepthFirstPaths {

    private boolean[] marked;

    private int[] edgeTo;

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
        Deque<Integer> stack = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
