package map;

import java.util.HashMap;
import java.util.Map;

/**
 * 深入学习 HashMap
 *
 * @author suchao
 * @date 2018/11/8
 */
public class HashMapExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("aaaaaa", "test");  // i = 10
        map.put("b", "bbbbb");  // hash("b") = 98,  n = 16,  i = (n - 1) & hash --->  i = 2
        map.put("e", "eeeee");  // hash("e") = 101, n = 16,  i = (n - 1) & hash --->  i = 5
        map.put("a", "aaaaa");  // hash("a") = 97,  n = 16,  i = (n - 1) & hash --->  i = 1
        map.put("c", "ccccc");  // hash("c") = 99,  n = 16,  i = (n - 1) & hash --->  i = 3
        map.put("d", "ddddd");  // hash("d") = 100, n = 16,  i = (n - 1) & hash --->  i = 4

        //HashMap发送冲突的键值对，链到了链表的尾上，（网上有篇文章说是链到头，应该是错的）
        // TODO: LinkedHashMap 好像是到头上，以后研究一下
        map.put("22222", "2222222"); // i = 10 ---
        map.put("33333", "33333333"); // i = 5
        map.get("22222");

        // 学习 hash() 方法  ---- return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        // Java 里一个 int 类型占 4 个字节，一个字节是 8 bit
        // 所以，h 与 h 右移 16 位就相当于高低位异或
        System.out.println(index("33333")); // i = 5;
        System.out.println(index("22222")); // i = 10;
        System.out.println(index("11111"));

        for (Map.Entry entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + " , value: " + entry.getValue());
        }

        // capacity - MUST be a power of two.
        // 因为 这样的话，在计算 i = (n - 1) & hash 的时候， (n - 1) 的二进制低位都是1，散列的好坏就取决于 hash 的好坏


        // TREEIFY_THRESHOLD 桶中的键值对超过这个值，链表转化为 树（红黑树）
        // UNTREEIFY_THRESHOLD



        // HashMap 实现了 map 接口， 又实现了 abstractMap 抽象类：
        // 原因是：好像作者的失误
        // @link: https://stackoverflow.com/questions/2165204/why-does-linkedhashsete-extend-hashsete-and-implement-sete
        // TODO: 现在网不好，以后仔细研究一下
    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static int index(Object key) {
        int n = 16;
        int hash = hash(key);
        return (n - 1) & hash;
    }
}
