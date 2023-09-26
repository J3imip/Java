package lab2.task2;

public class Task2 {
    public static void main(String[] args) {
        int n = 300;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        for (int i = 2; i * i <= n; i++) {
            if (arr[i - 1] > 0) {
                for (int j = i * i; j <= n; j += i) {
                    arr[j - 1] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                System.out.println(arr[i]);
            }
        }
    }
}
