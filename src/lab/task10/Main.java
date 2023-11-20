package lab.task10;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // Тест вставки ключів та значень
        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(5, "C");
        tree.insert(30, "D");
        tree.insert(15, "E");

        // Приклад операції пошуку
        int keyToSearch = 20;
        String value = tree.search(keyToSearch);
        if (value != null) {
            System.out.println("Значення для ключа " + keyToSearch + ": " + value);
        } else {
            System.out.println("Ключ " + keyToSearch + " не знайдено");
        }

        tree.delete(20);
        value = tree.search(20);
        if (value != null) {
            System.out.println("Значення для ключа 20: " + value);
        } else {
            System.out.println("Ключ 20 не знайдено після видалення");
        }
    }
}
