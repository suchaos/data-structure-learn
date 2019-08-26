package linkedList;

import java.util.Objects;
import java.util.Scanner;

/**
 * 基于单链表LRU算法
 * <p>
 * 头结点不存储数据
 *
 * @author suchao
 * @date 2019/6/24
 */
public class LRUBaseLinkedList<T> {
    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    /**
     * 添加缓存
     *
     * @param data 增加的数据
     */
    public void add(T data) {
        // 如果此数据之前被缓存在链表中了，我们遍历得到这个数据对应的结点，
        // 并将其从原来的位置删除，然后在插入到链表的头部
        SNode<T> preNode = findPreNode(data);
        if (preNode != null) {
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        } else {
            // 如果此时缓存已满，则链表尾结点删除，将新的数据结点插入到链表头部
            if (this.length >= this.capacity) {
                deletelEleAtEnd();
            }
            // 如果此时缓存未满，则将此结点直接插入到链表的头部
            intsertElemAtBegin(data);
        }

    }

    /**
     * 删除尾结点
     */
    private void deletelEleAtEnd() {
        SNode<T> preNode = headNode;
        if (preNode.getNext() == null) {
            return;
        }
        while (preNode.getNext().getNext() != null) {
            preNode = preNode.getNext();
        }
        preNode.setNext(null);
        length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void intsertElemAtBegin(T data) {
        headNode.setNext(new SNode<>(data, headNode.getNext()));
        length++;
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(SNode<T> preNode) {
        preNode.setNext(preNode.getNext().getNext());
        length--;
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @param data
     * @return
     */
    private SNode<T> findPreNode(T data) {
        Objects.requireNonNull(data);
        SNode<T> sNode = headNode;
        SNode<T> preNode = null;
        while (sNode.getNext() != null) {
            if (data.equals(sNode.getNext().getElement())) {
                preNode = sNode;
                break;
            } else {
                sNode = sNode.getNext();
            }
        }
        return preNode;
    }

    private void printAll() {
        SNode<T> node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }
}


class SNode<T> {

    private T element;

    private SNode<T> next;

    public SNode(T element) {
        this.element = element;
    }

    public SNode(T element, SNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public SNode() {
        this.next = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public SNode<T> getNext() {
        return next;
    }

    public void setNext(SNode<T> next) {
        this.next = next;
    }
}

