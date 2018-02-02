/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author hotha
 */
public class Password {

    private static int workload = 12;
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return (hashed_password);
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;
        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }
        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);
        return (password_verified);
    }
}
