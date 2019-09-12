package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用深度优先搜索来检测环
 * <p>
 * 本质上就是使用深度优先搜索，一路向下标记顶点，如果发现下一个顶点已经被标记了，
 * 而且不是由自己这个顶点标记的，那么肯定是存在环的情况了
 * <p>
 * 如果没有环的情况，那么会一路标记下去，而且所有顶点都是由上一个顶点标记的
 * <p>
 * 假设不存在自环或者平行边
 * <p>
 * <p>
 * <p>
 * TODO: 看一下 edu.princeton.cs.algs4.Cycle 的实现，输出了环的路径，而且解决了自环或者平行边
 * <p>
 * args: ./src/main/resources/tinyG.txt
 * <p>
 * args: ./src/main/resources/testCycle.txt
 *
 * @author suchao
 * @date 2019/9/12
 * @see edu.princeton.cs.algs4.Cycle
 */
public class Cycle {

    private boolean[] marked;

    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); s++) {
            if (!marked[s]) {
                dfs(graph, s, s);
            }
        }
    }

    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            // 若 w 没被标记过，那么从 w 继续递归深搜，把 w 的父节点作为第二参数
            if (!marked[w]) {
                dfs(graph, w, v);
            }
            // 若 w 被标记过，那么若无环，w 必然和父节点相同，否则就是有环
            else if (w != u) {
                StdOut.println("cycle point: " + w);
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return this.hasCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new In(args[0]));
        StdOut.println(graph);
        Cycle cycle = new Cycle(graph);
        StdOut.println("has cycle: " + cycle.hasCycle());
    }
}
