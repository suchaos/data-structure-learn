package directedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图中基于深度优先搜索的顶点排序
 * <p>
 * args: ./src/main/resources/tinyDAG.txt
 *
 * @author suchao
 * @date 2019/9/12
 * @see edu.princeton.cs.algs4.DepthFirstOrder
 */
public class DepthFirstOrder {

    private boolean[] marked;

    /**
     * 所有顶点的前序排序
     */
    private Queue<Integer> pre;

    /**
     * 所有顶点的后序排序
     */
    private Queue<Integer> post;

    /**
     * 所有顶点的逆后续排序
     */
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        for (int s = 0; s < digraph.V(); s++) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        StdOut.println(digraph);

        DepthFirstOrder dfs = new DepthFirstOrder(digraph);

        StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();
    }
}
