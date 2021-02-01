package com.epam.jwd.final_project.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordHasherUtil {

    private static final int LOG_ROUNDS = 12;

    private PasswordHasherUtil() {
    }

    public static String generatePasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(LOG_ROUNDS));
    }

    public static Boolean checkPassword(String passwordToCheck, String passwordHash) {
        return BCrypt.checkpw(passwordToCheck, passwordHash);
    }

}
