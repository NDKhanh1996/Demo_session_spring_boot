package com.example.session.hash;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHAHash implements Hashing {
    @Override
    public String hasPassword(String password) {
        //Note this is a security hole, it should be dynamically generated a new salt every time the password is hashed
        //Then save both salt and hash password in the database
        final String salt = "Õ1X-343n42mnl3905u";
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public boolean validatePassword(String originalPassword, String storedPassword) {
        String hashed_pass = hasPassword(originalPassword);
        return storedPassword.equals(hashed_pass);
    }
}