package search;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 性能测试用例
 * 运行方式：
 * program arguments : 1 tinyTale.txt
 * FileInputStream input = new FileInputStream(args[1]);
 * System.setIn(input);
 * <p>
 * 参数               ---   结果
 * 1 tinyTale.txt     --- it 10
 * 8 tale.txt         --- business 122
 * 10 leipzig1M.txt   --- government 24763
 *
 * @author suchao
 * @date 2018/11/19
 */
public class FrequencyCounter {

    public static void main(String[] args) throws FileNotFoundException {
        // 最小键长
        int minlen = Integer.parseInt(args[0]);
        String resourcesPath = "./data_structure_practice/src/main/resources/algs4-data/";
        FileInputStream input = new FileInputStream(resourcesPath + args[1]);
        System.setIn(input);
        ST<String, Integer> st = new ST<>();
        // 构造符号表并统计频率
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) {
                continue; // 忽略较短的单词
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }
        // 找出出现频率最高的单词
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
    }
}
