package com.fameden.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;

/**
 * @author JavaDigest
 *
 */
public class RSAEncryption {

    public static final String ALGORITHM = "RSA";
    public static final String PUBLIC_KEY_FILE = "public.key";

    public static boolean areKeysPresent() {
        File publicKey = new File(PUBLIC_KEY_FILE);

        if (publicKey.exists()) {
            return true;
        }
        return false;
    }

    private static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;

        try {

            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            Logger.getLogger(RSAEncryption.class.getName()).log(Level.SEVERE, null, e);
        }
        return cipherText;
    }

    public static String encrypt(String text) {
        String data = null;
        try {
            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(
                    PUBLIC_KEY_FILE));
            PublicKey key = (PublicKey) inputStream.readObject();
            data = new BASE64Encoder().encode(encrypt(text, key));
            return data;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RSAEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}