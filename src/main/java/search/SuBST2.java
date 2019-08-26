package search;

/**
 * 基于二叉查找树的符号表 --- 非递归实现
 * <p>
 * TODO： 思考非递归实现时，size的解决方法
 *
 * @author suchao
 * @date 2018/11/20
 */
public class SuBST2<Key extends Comparable<Key>, Value> {

    /**
     * 二叉查找树的根结点
     */
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        /**
         * 以该结点为根的子树中的结点总数
         */
        /*private int size;*/
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 在以 node 为根结点的子树中查找并返回 key 所对应的值
     *
     * @param node 根结点
     * @param key  key
     * @return 返回 key 所对应的值, 或者 null
     */
    private Value get(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("called get() with a null key");
        }
        if (node == null) {
            return null;
        }
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.val;
            }
        }
        return null;
    }

    /**
     * 查找key, 找到则更新它的值， 否则为它创建一个新的结点
     *
     * @param key   the key
     * @param value the value
     */
    public void put(Key key, Value value) {
        put(root, key, value);
    }

    private void put(Node node, Key key, Value value) {
        if (node == null) {
            root = new Node(key, value);
            return;
        }
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    node.left = new Node(key, value);
                    return;
                }
            } else if (cmp > 0) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    node.right = new Node(key, value);
                }
            } else {
                node.val = value;
            }
        }
    }

    // max(), min(), floor(). ceiling()

    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        while (true) {
            int cmp = key.compareTo(key);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                if (node.left == null) {
                    return node;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    return node;
                }
                node = node.right;
            }
        }
    }

    // delete(), deleteMin(), deleteMax()

    public void deleteMin() {
        if (root == null) {
            return;
        }
        if (root.left == null) {
            root = root.right;
            return;
        }
        Node node = root;
        while (node.left.left != null) {
            node = node.left;
        }
        node.left = node.left.right;
    }

    public void deleteMax() {
        if (root == null) {
            return;
        }
        if (root.right == null) {
            root = root.left;
            return;
        }
        Node node = root;
        while (node.right.right != null) {
            node = node.right;
        }
        node.right = node.right.left;
    }

    public void delete(Key key) {
        // 1. 找到要删除的结点及其父节点
        Node p = root;
        Node pp = null;
        while (p != null && p.key.compareTo(key) != 0) {
            pp = p;
            if (p.key.compareTo(key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }
        // 2. 要删除的节点有两个子节点(将后继结点的值赋给p,然后将问题转化为删除后继结点，进入3)
        if (p.left != null && p.right != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.key = minP.key;
            p.val = minP.val;
            p = minP;
            pp = minPP;
        }
        // 3. 删除节点是叶子节点或者仅有一个子节点
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }
        if (root == pp) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

    // TODO: 三种遍历
}
