package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 顶点名为字符串的图
 * <p>
 * args: ./src/main/resources/routes.txt " "
 * <p>
 * input: JFK
 * input: LAX
 * <p>
 * args: ./src/main/resources/movies.txt "/"
 * <p>
 * input: Tin Men (1987)
 * input: Bacon, Kevin
 *
 * @author suchao
 * @date 2019/9/11
 */
public class SymbolGraph {

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
    private Graph graph;

    public SymbolGraph(String filename, String delimiter) {
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
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                graph.addEdge(v, st.get(a[i]));
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

    public Graph graph() {
        return graph;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.nameOf(v));
                }
            } else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}
