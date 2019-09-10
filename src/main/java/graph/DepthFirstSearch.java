package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 深度优先搜索
 * <p>
 * args: tinyG.txt 0
 * <p>
 * args: tinyG.txt 9
 *
 * @author suchao
 * @date 2019/9/9
 */
public class DepthFirstSearch {

    private boolean[] marked;

    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        count++;
        for (int v : graph.adj(s)) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        String prefix = "./src/main/resources/";
        Graph graph = new Graph(new In(prefix + args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(graph, s);
        for (int v = 0; v < graph.V(); v++) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();
        if (search.count != graph.V()) {
            StdOut.print("NOT ");
        }
        StdOut.println("connected");
    }
}
