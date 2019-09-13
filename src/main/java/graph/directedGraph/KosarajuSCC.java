package graph.directedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 计算强连通分量的 Kosaraju 算法
 * <p>
 * 第一步：拿到当前图 G 的反向图
 * <p>
 * 第二步：对反向图进行深度优先遍历(DFS)，拿到图的拓扑排序 reversePost
 * <p>
 * 第三步：根据拓扑排序 reversePost 中节点的顺序，对当前图G进行深度优先遍历（DFS），即可得到强连通分量的数量
 * <p>
 * 使用深度优先搜索找出图中的所有连通分量
 * <p>
 * args: ./src/main/resources/tinyDG.txt
 *
 * @author suchao
 * @date 2019/9/12
 */
public class KosarajuSCC {

    private boolean[] marked;

    private int[] id;

    private int count;

    public KosarajuSCC(Digraph digraph) {
        marked = new boolean[digraph.V()];
        id = new int[digraph.V()];

        DepthFirstOrder order = new DepthFirstOrder(digraph.reverse());

        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(digraph, s);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        StdOut.println(digraph);
        KosarajuSCC scc = new KosarajuSCC(digraph);

        int m = scc.count();
        StdOut.println(m + " components");

        Queue<Integer>[] components = (Queue<Integer>[]) new LinkedList[m];
        for (int i = 0; i < m; i++) {
            components[i] = new LinkedList<>();
        }
        for (int v = 0; v < digraph.V(); v++) {
            components[scc.id(v)].offer(v);
        }
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
