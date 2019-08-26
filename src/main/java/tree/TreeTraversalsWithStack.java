package tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 使用栈遍历树
 *
 * @author suchao
 * @date 2019/8/26
 */
public class TreeTraversalsWithStack {

    private static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    public static void preOrder(TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode treeNode = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
    }

    public static void inOrder(TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode treeNode = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.rightChild;
            }
        }
    }

    /**
     * 非递归的后序遍历比较难
     * @param node
     */
    public static void postOrder(TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode treeNode = node;
        TreeNode lastVisit = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            treeNode = stack.peek();
            if (treeNode.rightChild == null || treeNode.rightChild == lastVisit) {
                System.out.println(treeNode.data);
                stack.pop();
                lastVisit = treeNode;
                treeNode = null;
            } else {
                treeNode = treeNode.rightChild;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(
                Arrays.asList(3, 2, 9, null, null, 10, null, null, 8, null, 4));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("preOder: ");
        preOrder(treeNode);
        System.out.println("inOrder: ");
        inOrder(treeNode);
        System.out.println("postOrder: ");
        postOrder(treeNode);
    }
}
