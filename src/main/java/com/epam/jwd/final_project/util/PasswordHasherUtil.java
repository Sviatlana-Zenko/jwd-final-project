package com.epam.jwd.final_project.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Final class that provides static methods for working with user password.
 */
public final class PasswordHasherUtil {

    /**
     * A constant that determines the computational complexity of the hashing.
     */
    private static final int LOG_ROUNDS = 12;

    private PasswordHasherUtil() {
    }

    /**
     * Returns hash generated from given password.
     *
     * @param password the password to hash
     * @return the hashed password
     */
    public static String generatePasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(LOG_ROUNDS));
    }

    /**
     * Check that a plaintext password matches a previously hashed one.
     *
     * @param passwordToCheck the plaintext password to verify
     * @param passwordHash the previously-hashed password
     * @return true if the passwords match, false otherwise
     */
    public static Boolean checkPassword(String passwordToCheck, String passwordHash) {
        return BCrypt.checkpw(passwordToCheck, passwordHash);
    }

}
