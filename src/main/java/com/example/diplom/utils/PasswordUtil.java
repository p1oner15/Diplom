package com.example.diplom.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Метод для хэширования пароля
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Метод для проверки пароля
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
