package INEC.Encriptar;


import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public  class encriptar {
    public  String key = "PruebaInec2018.AbAbAb23";
    public  byte[] key_Array = decodeBase64(key);

    public String Encrypt(String text, String key)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        //BASE64Encoder encoder = new BASE64Encoder();
        //Base64 bs = new Base64();
        return new String(Base64.encode(results, Base64.DEFAULT));
    }
    public String encrypt(String strToEncrypt)
    {
        try
        {
            //Cipher _Cipher = Cipher.getInstance("AES");
            //Cipher _Cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            Cipher _Cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");

            // Initialization vector.
            // It could be any value or generated using a random number generator.
            byte[] iv = { 1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1, 7, 7, 7, 7 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Key SecretKey = new SecretKeySpec(key_Array, "AES");
            _Cipher.init(Cipher.ENCRYPT_MODE, SecretKey, ivspec);

            return  Base64.encodeToString(_Cipher.doFinal(strToEncrypt.getBytes()), Base64.DEFAULT);// encodeBase64String(_Cipher.doFinal(strToEncrypt.getBytes()));
        }
        catch (Exception e)
        {
            System.out.println("[Exception]:"+e.getMessage());
        }
        return null;
    }

    public  String decrypt(String EncryptedMessage)
    {
        try
        {
            //Cipher _Cipher = Cipher.getInstance("AES");
            //Cipher _Cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            Cipher _Cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            // Initialization vector.
            // It could be any value or generated using a random number generator.
            byte[] iv = { 1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1, 7, 7, 7, 7 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Key SecretKey = new SecretKeySpec(key_Array, "AES");
            _Cipher.init(Cipher.DECRYPT_MODE, SecretKey, ivspec);

            byte DecodedMessage[] = decodeBase64(EncryptedMessage);
            return new String(_Cipher.doFinal(DecodedMessage));

        }
        catch (Exception e)
        {
            System.out.println("[Exception]:"+e.getMessage());

        }
        return null;
    }

    private byte[] decodeBase64(String dataToDecode)
    {
        byte[] dataDecoded = Base64.decode(dataToDecode, Base64.DEFAULT);
        return dataDecoded;
    }

    //enconde data in base 64
    private byte[] encodeBase64(byte[] dataToEncode)
    {
        byte[] dataEncoded = Base64.encode(dataToEncode, Base64.DEFAULT);
        return dataEncoded;
    }
}
