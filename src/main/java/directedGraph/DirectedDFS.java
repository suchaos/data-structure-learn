package directedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import graph.DepthFirstSearch;

/**
 * 有向图的可达性
 * <p>
 * args:  ./src/main/resources/tinyDG.txt 1
 * output: 1
 * <p>
 * args:  ./src/main/resources/tinyDG.txt 2
 * output: 0 1 2 3 4 5
 * <p>
 * args:  ./src/main/resources/tinyDG.txt 1 2 6
 * output: 0 1 2 3 4 5 6 8 9 10 11 12
 *
 * @author suchao
 * @date 2019/9/9
 * @see DepthFirstSearch
 */
public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        dfs(digraph, s);
    }

    public DirectedDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.V()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));
        StdOut.println(digraph);

        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS reachable = new DirectedDFS(digraph, sources);

        for (int v = 0; v < digraph.V(); v++) {
            if (reachable.marked(v)) {
                StdOut.print(v + " ");
            }
            StdOut.print();
        }
    }
}
