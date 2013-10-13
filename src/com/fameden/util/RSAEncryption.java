package com.fameden.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

/**
 * @author JavaDigest
 *
 */
public class RSAEncryption {

    private static Logger logger = LoggerFactory.getLogger(RSAEncryption.class);
    public static final String ALGORITHM = "RSA";
    public static final String PUBLIC_KEY_FILE = "public.key";

    private static byte[] encrypt(String text) {

        ObjectInputStream inputStream = null;
        byte[] cipherText = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(
                    PUBLIC_KEY_FILE));
            PublicKey key = (PublicKey) inputStream.readObject();
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if(inputStream!=null)
                    inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return cipherText;
    }

    public static String encryptText(String text) {
        return new BASE64Encoder().encode(encrypt(text));
    }
}