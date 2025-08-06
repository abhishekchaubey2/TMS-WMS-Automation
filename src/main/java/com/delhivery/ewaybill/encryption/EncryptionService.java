package com.delhivery.ewaybill.encryption;

import com.delhivery.ewaybill.common.constants.EWaybillConstant;
import org.bouncycastle.util.io.pem.PemReader;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionService {
    public static String rsaEncryptV2Changed(String val) {
        PublicKey publicKey = loadPublicKey();

        try {
            Cipher cipher = Cipher.getInstance(EWaybillConstant.RSA_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedData = cipher.doFinal(val.getBytes(EWaybillConstant.DEFAULT_CHARSET));
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Failed to encrypt data", e);
        }
    }

    private static PublicKey loadPublicKey() {
        try (PemReader pemReader = new PemReader(new FileReader(EWaybillConstant.PUBLIC_KEY_PATH+"certificate_publickey_stage.pem"))) {
            byte[] keyBytes = pemReader.readPemObject().getContent();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(EWaybillConstant.DEFAULT_RSA_ALGORITHM);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load public key", e);
        }
    }


    public static String aesDecrypt(String key, String encryptedText)  {
        try {
            byte[] decodedText = Base64.getDecoder().decode(encryptedText);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(EWaybillConstant.DEFAULT_CHARSET), EWaybillConstant.AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(EWaybillConstant.TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decryptedBytes = cipher.doFinal(decodedText);
            return Base64.getEncoder().encodeToString(decryptedBytes);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static String aesEncrypt(String key, String text)  {
       try {
           byte[] decodedKey = Base64.getDecoder().decode(key);
           SecretKeySpec secretKey = new SecretKeySpec(decodedKey, EWaybillConstant.AES_ALGORITHM);
           Cipher cipher = Cipher.getInstance(EWaybillConstant.TRANSFORMATION);
           cipher.init(Cipher.ENCRYPT_MODE, secretKey);

           byte[] encryptedBytes = cipher.doFinal(text.getBytes(EWaybillConstant.DEFAULT_CHARSET));
           return Base64.getEncoder().encodeToString(encryptedBytes);
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }

    public static String decodeAndDecryptAES(String encryptedText, String key){
       try {
           byte[] decodedKey = Base64.getDecoder().decode(key);
           byte[] decodedText = Base64.getDecoder().decode(encryptedText);

           Cipher cipher = Cipher.getInstance(EWaybillConstant.TRANSFORMATION);
           SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, EWaybillConstant.AES_ALGORITHM);
           cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);


           byte[] decryptedText = cipher.doFinal(decodedText);
           return new String(decryptedText);
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }
}
