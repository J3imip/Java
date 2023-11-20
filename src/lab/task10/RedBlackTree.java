package lab.task10;

class Node {
    int key;
    String value;
    Node left, right;
    boolean isRed;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.isRed = true; // Початково вузли - червоні
    }
}

// Основний клас червоно-чорного дерева
public class RedBlackTree {
    private Node root;

    // Метод для вставки ключа та значення в дерево
    public void insert(int key, String value) {
        root = insertRecursive(root, key, value);
        root.isRed = false; // Корінь завжди чорний
    }

    // Рекурсивний метод для вставки вузла в дерево
    private Node insertRecursive(Node root, int key, String value) {
        if (root == null) {
            return new Node(key, value);
        }

        switch (Integer.compare(key, root.key)) {
            case -1:
                root.left = insertRecursive(root.left, key, value);
                break;
            case 1:
                root.right = insertRecursive(root.right, key, value);
                break;
            case 0:
                // Якщо ключ вже існує, оновити значення
                root.value = value;
                break;
        }

        // Балансування дерева після вставки
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }

        return root;
    }

    // Методи для операцій балансування дерева (rotateLeft, rotateRight, flipColors)

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.isRed;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        rightChild.isRed = node.isRed;
        node.isRed = true;
        return rightChild;
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        leftChild.isRed = node.isRed;
        node.isRed = true;
        return leftChild;
    }

    private void flipColors(Node node) {
        node.isRed = !node.isRed;
        node.left.isRed = !node.left.isRed;
        node.right.isRed = !node.right.isRed;
    }

    private Node searchRecursive(Node root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        switch (Integer.compare(key, root.key)) {
            case -1:
                return searchRecursive(root.left, key);
            case 1:
                return searchRecursive(root.right, key);
        }

        if (key < root.key) {
            return searchRecursive(root.left, key);
        }

        return searchRecursive(root.right, key);
    }

    public String search(int key) {
        Node node = searchRecursive(root, key);
        return node == null ? null : node.value;
    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private Node findMin(Node root) {
        if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }

    private Node deleteRecursive(Node root, int key) {
        if (root == null) {
            return null;
        }

        switch (Integer.compare(key, root.key)) {
            case -1:
                root.left = deleteRecursive(root.left, key);
                break;
            case 1:
                root.right = deleteRecursive(root.right, key);
                break;
            default:
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.right == null) {
                    return root.left;
                } else if (root.left == null) {
                    return root.right;
                } else {
                    Node minNode = findMin(root.right);
                    root.key = minNode.key;
                    root.value = minNode.value;
                    root.right = deleteRecursive(root.right, root.key);
                }
        }

        // Балансування дерева після видалення
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }

        return root;
    }
}
