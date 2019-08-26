package tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 递归遍历树
 *
 * @author suchao
 * @date 2019/8/26
 */
public class TreeTraversals {

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
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.leftChild);
        System.out.println(node.data);
        inOrder(node.rightChild);
    }

    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.println(node.data);
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
