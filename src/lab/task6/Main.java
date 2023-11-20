package lab.task6;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> users = new HashMap<>();

        users.put("user1", "password123");
        users.put("user2", "securepass");
        users.put("user3", "verystrongpassword");
        users.put("user4", "weak");

        System.out.println("Users with password length > 6:");
        for (Map.Entry<String, String> entry : users.entrySet()) {
            String username = entry.getKey();
            String password = entry.getValue();
            if (password.length() > 6) {
                System.out.println("User: " + username + ", Password: " + password);
            }
        }
    }
}

