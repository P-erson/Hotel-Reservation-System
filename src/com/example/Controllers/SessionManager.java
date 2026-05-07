package com.example.Controllers;

import com.example.Classes.User;
import com.example.Classes.Guest;
import com.example.Classes.Admin;
import com.example.Classes.Receptionist;

public class SessionManager {

    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Guest getCurrentGuest() {
        if (currentUser instanceof Guest) {
            return (Guest) currentUser;
        }
        return null;
    }

    public static Admin getCurrentAdmin() {
        if (currentUser instanceof Admin) {
            return (Admin) currentUser;
        }
        return null;
    }

    public static Receptionist getCurrentReceptionist() {
        if (currentUser instanceof Receptionist) {
            return (Receptionist) currentUser;
        }
        return null;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
