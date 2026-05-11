package com.ticketflow.FullStack_back.data;

import com.ticketflow.FullStack_back.models.User;
import java.util.*;

public class DataStore {
    private static final Map<String, User> users = new HashMap<>();
    private static final Map<String, User> admins = new HashMap<>();

    static {
        // Usuario por defecto
        User defaultUser = new User(
                "user_1",
                "Juan",
                "Pérez",
                "usuario@test.com",
                "3001234567",
                "123456",
                "USER"
        );
        users.put("usuario@test.com", defaultUser);

        // Admin por defecto
        User defaultAdmin = new User(
                "admin_1",
                "Admin",
                "Sistema",
                "admin@test.com",
                "3009999999",
                "admin123",
                "ADMIN"
        );
        admins.put("admin@test.com", defaultAdmin);
    }

    public static User getUserByEmail(String email) {
        return users.get(email);
    }

    public static User getAdminByEmail(String email) {
        return admins.get(email);
    }

    public static void saveUser(User user) {
        users.put(user.getEmail(), user);
    }

    public static Collection<User> getAllUsers() {
        return users.values();
    }

    public static Collection<User> getAllAdmins() {
        return admins.values();
    }
}
