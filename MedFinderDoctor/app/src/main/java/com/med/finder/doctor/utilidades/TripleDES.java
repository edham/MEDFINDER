package com.med.finder.doctor.utilidades;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {
    private String key;
    private String initializationVector;
    public TripleDES(String key, String initializationVector)
    {
        this.key = key;
        this.initializationVector = initializationVector;
    }
    public String encryptText(String plainText) throws Exception{
        byte[] plaintext = plainText.getBytes();
        byte[] tdesKeyData = key.getBytes();
// byte[] myIV = initializationVector.getBytes();
        Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(initializationVector.getBytes());
        c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
        byte[] cipherText = c3des.doFinal(plaintext);
        return Base64.encodeToString(cipherText, Base64.DEFAULT);
    }
    public String decryptText(String cipherText)throws Exception{
        byte[] encData = Base64.decode(cipherText,Base64.DEFAULT);
        Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        byte[] tdesKeyData = key.getBytes();
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(initializationVector.getBytes());
        decipher.init(Cipher.DECRYPT_MODE, myKey, ivspec);
        byte[] plainText = decipher.doFinal(encData);
        return new String(plainText);
    }
}