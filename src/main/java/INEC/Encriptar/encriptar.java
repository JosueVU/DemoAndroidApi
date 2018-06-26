package INEC.Encriptar;


import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public  class encriptar {
    public  String key = "PruebaInec2018.AbAbAb23";

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

   public  String Decrypt(String text, String key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes= new byte[16];
        byte[] b= key.getBytes("UTF-8");
        int len= b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);

        //BASE64Decoder decoder = new BASE64Decoder();
       /* byte [] results = cipher.doFinal(text.getBytes("UTF-8"));
       Base64.getDecoder()
        return new String(Base64.decode(results, Base64.DEFAULT));*/

       //Decoder decoder = new Decoder();
       byte [] results = cipher.doFinal(Base64.decode(text, Base64.DEFAULT));
       return new String(results);
    }
}
