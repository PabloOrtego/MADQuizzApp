package com.example.quizzapp;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    private static final Map<String, String[]> users = new HashMap<>();

    static {
        users.put("user1@example.com", new String[]{"user1", "1"});
        users.put("user2@example.com", new String[]{"user2", "password2"});
        users.put("user3@example.com", new String[]{"user3", "password3"});
        users.put("user4@example.com", new String[]{"user4", "password4"});
        users.put("user5@example.com", new String[]{"user5", "password5"});
    }

    public static boolean validateLogin(String email, String password) {
    if (users.containsKey(email)) {
        return users.get(email)[1].equals(password);
    }
    return false;
}

    public static void addUser(String name, String email, String password) {
        users.put(email, new String[]{name, password});
    }
}
