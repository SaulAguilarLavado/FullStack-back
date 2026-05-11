package com.ticketflow.FullStack_back.services;

import com.ticketflow.FullStack_back.models.User;
import com.ticketflow.FullStack_back.data.DataStore;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class AdminService {

    public Collection<User> getAllUsers() {
        return DataStore.getAllUsers();
    }

    public Collection<User> getAllAdmins() {
        return DataStore.getAllAdmins();
    }

    public boolean deleteUser(String email) {
        User user = DataStore.getUserByEmail(email);
        if (user != null) {
            // En una BD real, eliminarías del DB
            return true;
        }
        return false;
    }

    public User updateUser(User user) {
        DataStore.saveUser(user);
        return user;
    }
}
