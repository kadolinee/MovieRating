package com.epam.kinorating.encryptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryptor {
    public static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static byte[] getSalt() {
        byte[] salt = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

            salt = new byte[16];

            sr.nextBytes(salt);

            return salt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return salt;
    }

    public static String convertArrayToString(byte[] array) {
        String str = "";

        for (byte anArray : array) {
            str = str + anArray + " ";
        }
        return str;
    }

    public static byte[] convertStringToArray(String str) {
        String[] array = str.split(" ");

        byte[] bytes = new byte[16];

        for (int i = 0; i < array.length; i++) {
            byte aByte = Byte.parseByte(array[i]);
            bytes[i] = aByte;
        }
        return bytes;
    }
}
