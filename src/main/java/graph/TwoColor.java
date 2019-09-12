package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 双色问题，也是二分图问题
 * <p>
 * 使用深度优先搜索，判断对于一个顶点来说，标记这个顶点的父节点与这个顶点
 * 的颜色是否相同，如果不同，则是二分图
 * <p>
 * args: ./src/main/resources/testTwoColor.txt
 * <p>
 * args: ./src/main/resources/testTwoColor2.txt
 *
 * @author suchao
 * @date 2019/9/12
 */
public class TwoColor {

    private boolean[] marked;

    private boolean[] color;

    private boolean isTwoColorable = true;

    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {
                dfs(graph, s);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(graph, w);
            } else if (color[w] == color[v]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        StdOut.println(graph);
        TwoColor twoColor = new TwoColor(graph);
        StdOut.println("isBipartite: " + twoColor.isBipartite());
    }
}
