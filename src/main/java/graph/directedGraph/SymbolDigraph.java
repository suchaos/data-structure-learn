package graph.directedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 顶点名为字符串的图
 * <p>
 * args: ./src/main/resources/jobs.txt "/"
 * <p>
 * args: ./src/main/resources/routes.txt " "
 *
 * @author suchao
 * @date 2019/9/12
 * @see edu.princeton.cs.algs4.SymbolDigraph
 */
public class SymbolDigraph {

    /**
     * 顶点名 ---> 索引
     */
    private ST<String, Integer> st;

    /**
     * 索引 ---> 顶点名
     */
    private String[] keys;

    /**
     * 根据索引构建的图
     */
    private Digraph digraph;

    public SymbolDigraph(String filename, String delimiter) {
        st = new ST<>();
        In in = new In(filename);
        // 第一遍遍历，开始构造索引
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            // 为每个不同的字符串关联一个索引
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        // 第二遍遍历，开始构建图
        digraph = new Digraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                digraph.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String nameOf(int v) {
        return keys[v];
    }

    public Digraph digraph() {
        return digraph;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Digraph digraph = sg.digraph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : digraph.adj(s)) {
                    StdOut.println("   " + sg.nameOf(v));
                }
            } else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}
