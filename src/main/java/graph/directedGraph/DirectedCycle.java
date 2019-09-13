package graph.directedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 寻找有向环
 * <p>
 * DAG：有向无环图就是一幅不含有有向环的有向图
 * <p>
 * args: ./src/main/resources/testDirectedCycleError.txt
 * <p>
 * args: ./src/main/resources/tinyDG.txt
 *
 * @author suchao
 * @date 2019/9/9
 * @see edu.princeton.cs.algs4.DirectedCycle
 */
public class DirectedCycle {

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
     * 如果找到一个环，则将环中的顶点压入栈中保存
     */
    private Stack<Integer> cycle;

    /**
     * 保存递归调用期间栈上的所有顶点（一次深度优先遍历完，就恢复原值）
     */
    private boolean[] onStack;

    public DirectedCycle(Digraph digraph) {
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            // 如果已经有环，直接返回
            if (this.hasCycle()) {
                return;
            }
            // 继续向下标记
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            }
            // 发现顶点已经被标记，查看是否在栈中（其实就是检测是否在现在遍历的这一条有向路径上），
            // 如果在的化，则证明找到了一个环，开始将环的顶点压入栈中
            else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        // 遍历完这条路径了，恢复 onStack 上的值
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        StdOut.println(digraph);

        DirectedCycle cycle = new DirectedCycle(digraph);

        if (cycle.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : cycle.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}
