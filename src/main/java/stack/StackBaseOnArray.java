package stack;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 基于数组实现的栈，顺序栈
 *
 * @author suchao
 * @date 2019/6/26
 */
public class StackBaseOnArray<T> {
    private Object[] items;
    /**
     * 栈中元素个数
     */
    private int count;
    /**
     * 栈的大小
     */
    private int n;

    public StackBaseOnArray(int n) {
        this.items = new Object[n];
        this.n = n;
        this.count = 0;
    }

    public boolean push(T item) {
        Objects.requireNonNull(item);
        if (count >= n) {
            return false;
        }
        items[count++] = item;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (count == 0) {
            return null;
        }
        return (T) items[--count];
    }

    public static void printStack(StackBaseOnArray stack) {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < stack.count; i++) {
            joiner.add(stack.items[i].toString());
        }
        System.out.println(joiner);
        System.out.println("-------");
    }

    public static void main(String[] args) {
        StackBaseOnArray<Integer> stack = new StackBaseOnArray<>(10);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        printStack(stack);
        for (int i = 0; i < 10; i++) {
            stack.pop();
            printStack(stack);
        }
    }
}
