package graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用广度优先遍历查找图中的路径
 *
 * @author suchao
 * @date 2019/9/2
 */
public class BreadthFirstPaths {

    private boolean[] marked;

    private int[] edgeTo;

    private final int s;

    public BreadthFirstPaths(Graph graph, int s) {
        this.s = s;
        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.adj(v)) {
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
        Deque<Integer> stack = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
