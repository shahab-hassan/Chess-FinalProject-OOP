package com.example.finalproject;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {
    static final String ENCRYPTION_ALGORITHM = "AES";
    static final String ENCRYPTION_KEY = "UzA6rs2MZf+mX83YBmPF1w==";
    static String encryptObject(Object object) throws Exception{
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        SecretKey secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(object);
            byte[] encryptedBytes = cipher.doFinal(outputStream.toByteArray());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
    }
    static Object decryptObject(String encryptedData) throws Exception{
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        SecretKey secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(decryptedBytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return objectInputStream.readObject();
        }
    }
}
