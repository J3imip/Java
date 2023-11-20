package lab.task9;

class BinaryTree {
    // Клас для представлення вузла
    public static class Node {
        int    key;
        String value;
        Node   leftChild;
        Node   rightChild;
        Node(int key, String name) {
            this.key = key;
            this.value = name;
        }
        @Override
        public String toString() {
            return "Key: " + key + " Value:" + value;
        }
    }

    private Node root;

    public void addNode(int key, String value) {
        // Створюємо новий вузол:
        Node newNode = new Node(key, value);
        if (root == null) { // перший доданий вузол
            root = newNode;
        }
        else {
            // Починаємо обхід:
            Node currentNode = root;
            Node parent;
            while (true) {
                parent = currentNode;
                // Перевіряємо ключі:
                if (key < currentNode.key) {
                    currentNode = currentNode.leftChild;
                    if (currentNode == null) {
                        // Розміщуємо вузол в потрібному місці:
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else {
                    currentNode = currentNode.rightChild;
                    if (currentNode == null) {
                        // Розміщуємо вузол в потрібному місці:
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // Обхід вузлів в порядку зростання ключів
    public void traverseTree(Node currentNode) {
        if (currentNode != null) {
            traverseTree(currentNode.leftChild);
            System.out.println(currentNode);
            traverseTree(currentNode.rightChild);
        }
    }

    public void traverseTree() {
        traverseTree(root);
    }

    // Пошук вузла за ключем
    public Node findNode(int key) {
        Node focusNode = root;
        while (focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            }
            else {
                focusNode = focusNode.rightChild;
            }
            // Не знайшли:
            if (focusNode == null) {
                return null;
            }
        }
        return focusNode;
    }

    public void deleteNode(int key) {
        Node currentNode = root;
        Node parent = root;
        boolean isLeftChild = true;

        // Пошук вузла за ключем
        while (currentNode.key != key) {
            parent = currentNode;
            if (key < currentNode.key) {
                isLeftChild = true;
                currentNode = currentNode.leftChild;
            } else {
                isLeftChild = false;
                currentNode = currentNode.rightChild;
            }
            if (currentNode == null) {
                return;
            }
        }

        // Якщо вузол не має дочірніх вузлів
        if (currentNode.leftChild == null && currentNode.rightChild == null) {
            if (currentNode == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        }
        // Якщо у вузла є тільки один дочірній вузол
        else if (currentNode.rightChild == null) {
            if (currentNode == root) {
                root = currentNode.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = currentNode.leftChild;
            } else {
                parent.rightChild = currentNode.leftChild;
            }
        } else if (currentNode.leftChild == null) {
            if (currentNode == root) {
                root = currentNode.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = currentNode.rightChild;
            } else {
                parent.rightChild = currentNode.rightChild;
            }
        }
        // Якщо у вузла є два дочірніх вузли
        else {
            Node replacement = getReplacementNode(currentNode);

            if (currentNode == root) {
                root = replacement;
            } else if (isLeftChild) {
                parent.leftChild = replacement;
            } else {
                parent.rightChild = replacement;
            }

            replacement.leftChild = currentNode.leftChild;
        }

    }

    private Node getReplacementNode(Node replacedNode) {
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node currentNode = replacedNode.rightChild;

        while (currentNode != null) {
            replacementParent = replacement;
            replacement = currentNode;
            currentNode = currentNode.leftChild;
        }

        if (replacement != replacedNode.rightChild) {
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }

        return replacement;
    }

    // Тест:
    public static void main(String[] args) {
        BinaryTree continents = new BinaryTree();
        continents.addNode(1, "Європа");
        continents.addNode(3, "Африка");
        continents.addNode(5, "Австралія");
        continents.addNode(4, "Америка");
        continents.addNode(2, "Азія");
        continents.addNode(6, "Антарктида");
        continents.traverseTree();
        System.out.println("\nКонтинент з ключем 4:");
        System.out.println(continents.findNode(4));
        System.out.println("\nВидалення вузла з ключем 4:");
        continents.deleteNode(4);
        continents.traverseTree();
    }
}