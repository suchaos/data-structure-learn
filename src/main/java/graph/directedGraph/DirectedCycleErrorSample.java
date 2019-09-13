package graph.directedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 直接把无向图中检测环的代码拿过来是错误的，记录一下
 * <p>
 * testDirectedCycleError.txt 中的有向图是无环的，但是使用这段代码检测有环
 * <p>
 * 这是由于有向图与无向图的边的方向性而产生的差异，注意理解
 * <p>
 * args: ./src/main/resources/testDirectedCycleError.txt
 *
 * @author suchao
 * @date 2019/9/12
 * @see edu.princeton.cs.algs4.Cycle
 */
public class DirectedCycleErrorSample {

    private boolean[] marked;

    private boolean hasCycle;

    public DirectedCycleErrorSample(Digraph digraph) {
        marked = new boolean[digraph.V()];
        for (int s = 0; s < digraph.V(); s++) {
            if (!marked[s]) {
                dfs(digraph, s, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v, int u) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            // 若 w 没被标记过，那么从 w 继续递归深搜，把 w 的父节点作为第二参数
            if (!marked[w]) {
                dfs(digraph, w, v);
            }
            // 若 w 被标记过，那么若无环，w 必然和父节点相同，否则就是有环
            else if (w != u) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return this.hasCycle;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        StdOut.println(digraph);
        DirectedCycleErrorSample cycle = new DirectedCycleErrorSample(digraph);
        // 这个例子是个反例，这样是无法检测有向图中的环的
        StdOut.println("has cycle: " + cycle.hasCycle());
    }
}
