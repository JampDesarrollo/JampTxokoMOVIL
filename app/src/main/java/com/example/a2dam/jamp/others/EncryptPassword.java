/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.others;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Ander
 */
public class EncryptPassword {
    /**
     * Logger for the class.
     */
    private static final Logger LOGGER = Logger.getLogger("jampclientside.ui.controller");
    /**
     *
     * @param password
     * @return
     * @author ander
     */
    public static byte[] encrypt(byte[] password) {
        byte[] encodedMessage = null;
        try {
            FileInputStream fis = new FileInputStream("public.key");
            byte[] byteA = new byte[fis.available()];
            fis.read(byteA);
            fis.close();

            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec ks = new X509EncodedKeySpec(byteA);
            PublicKey publick = kf.generatePublic(ks);

            // Encode the text with the public key
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publick);
            encodedMessage = cipher.doFinal(password);

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "EncryptPassw: File not found", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "EncryptPassw: IO exception", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "EncryptPassw: No such algorithm", e.getMessage());
        } catch (InvalidKeySpecException e) {
            LOGGER.log(Level.SEVERE, "Invalid key specification", e.getMessage());
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "EncryptPassw: No such padding", e.getMessage());
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "EncryptPassw: Invalid key", e.getMessage());
        } catch (IllegalBlockSizeException e) {
            LOGGER.log(Level.SEVERE, "EncryptPassw: Illegal block size", e.getMessage());
        } catch (BadPaddingException e) {
            LOGGER.log(Level.SEVERE, "EncryptPassw: Bad padding", e.getMessage());
        }
        return encodedMessage;
    }
}
