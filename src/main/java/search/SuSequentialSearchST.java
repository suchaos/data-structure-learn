package search;

import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 无序链表中的顺序查找
 *
 * @author suchao
 * @date 2018/11/19
 */
public class SuSequentialSearchST<Key, Value> {
    /**
     * 链表首结点
     */
    private Node first;

    /**
     * number of key-value pairs
     */
    private int n;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new ArrayDeque<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.add(x.key);
        }
        return queue;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first, key);
    }

    /**
     * delete key in linked list beginning at Node x
     * TODO：就是链表删除一个节点，然后返回头节点，再写一个非递归的
     * @param x 从x开始遍历
     * @param key 要删除的节点key
     * @return first 头节点
     */
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SuSequentialSearchST<String, Integer> st = new SuSequentialSearchST<>();

        String resourcesPath = "./data_structure_practice/src/main/resources/algs4-data/";
        FileInputStream input = new FileInputStream(resourcesPath + args[0]);
        System.setIn(input);

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
