package mk.ukim.finki.bnks.securedrive.Utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Utils {
    public static String encrypt (String dataBase64, String keyBase64) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] keyBytes = Arrays.copyOf(keyBase64.getBytes(), 16);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] data = dataBase64.getBytes();
        byte[] cipherText = new byte[cipher.getOutputSize(data.length)];
        int offset = 0;
        int length = 16;
        int destOffset = offset;

        int temp = 0;

        for (int i=0; true; i++) {
            //offset = i*length;
            offset = offset + temp;
            destOffset = offset;

            if (offset+length>=data.length) {
                break;
            }

            temp = cipher.update(data, offset, length, cipherText, destOffset);    //  throws ShortBufferException
        }
        length = data.length - offset;
        temp = cipher.doFinal(data, offset, length, cipherText, destOffset);

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt (String dataBase64, String keyBase64) throws ShortBufferException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] keyBytes = Arrays.copyOf(keyBase64.getBytes(), 16);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedData = dataBase64.getBytes();
        int totalLength = cipher.getOutputSize(encryptedData.length);
        byte[] plainText = new byte[totalLength];
        int offset = 0;
        int length = cipher.getBlockSize();
        int destOffset = offset;

        int temp = 0;

        for (int i=0; true; i++) {
            //offset = i*length;
            offset = offset + temp;
            destOffset = offset;

            if (offset+length>encryptedData.length) {
                break;
            }

            temp = cipher.update(encryptedData, offset, length, plainText, destOffset);    //  throws ShortBufferException
        }

        length = encryptedData.length - offset;

        int temp2 = cipher.getOutputSize(encryptedData.length);

        if (temp2 > totalLength) {
            //  extend plainText array
            byte[] tempArr = plainText.clone();
            plainText = new byte[temp2];
            System.arraycopy(tempArr, 0, plainText, 0, tempArr.length);
        }

        temp = cipher.doFinal(encryptedData, offset, length, plainText, destOffset);   //  throws IllegalBlockSizeException, BadPaddingException

        return Base64.getDecoder().decode(plainText).toString();
    }
}
