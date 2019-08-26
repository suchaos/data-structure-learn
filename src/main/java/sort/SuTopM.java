package sort;

import edu.princeton.cs.algs4.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * top m
 * 启动时参数: 5 tinyBatch.txt
 *
 * @author suchao
 * @date 2018/12/5
 */
public class SuTopM {
    public static void main(String[] args) throws FileNotFoundException {
        // 打印输入流中最大的 m 行
        System.out.println(Integer.parseInt(args[0]));
        int m = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<>(m + 1);

        String resourcesPath = "./data_structure_practice/src/main/resources/algs4-data/";
        InputStream input = new FileInputStream(resourcesPath + args[1]);
        System.setIn(input);

        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > m) {
                pq.delMin();
            }
        }
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMin());
        }
        for (Transaction t : stack) {
            StdOut.println(t);
        }
    }
}
