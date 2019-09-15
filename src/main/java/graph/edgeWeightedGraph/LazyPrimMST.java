package graph.edgeWeightedGraph;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树的 Prim 算法的延时实现
 *
 * @author suchao
 * @date 2019/9/14
 * @see edu.princeton.cs.algs4.LazyPrimMST
 */

public class LazyPrimMST {

    /**
     * 最小生成树的顶点
     */
    private boolean[] marked;

    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;

    /**
     * 最小堆：横切边（包括失效的边）
     */
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        pq = new MinPQ<>();
        marked = new boolean[graph.V()];
        mst = new Queue<>();

        // 假设 graph 是连通的
        visit(graph, 0);
        while (!pq.isEmpty()) {
            // 从 pq 中得到权重最小的边
            Edge edge = pq.delMin();

            int v = edge.either();
            int w = edge.other(v);

            // 跳过失效的边
            if (marked[v] && marked[w]) {
                continue;
            }

            // 将边添加到树中
            mst.enqueue(edge);

            // 将顶点 v, 或者 w 添加到树中
            if (!marked[v]) {
                visit(graph, v);
            }
            if (!marked[w]) {
                visit(graph, w);
            }
        }
    }

    /**
     * 标记顶点 v，并将所有连接 v 和未被标记顶点的边加入优先队列 pq 中去
     *
     * @param graph 加权无向图
     * @param v     顶点 v
     */
    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adj(v)) {
            if (!marked[edge.other(v)]) {
                pq.insert(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge edge : edges()) {
            weight += edge.weight();
        }
        return weight;
    }
}
