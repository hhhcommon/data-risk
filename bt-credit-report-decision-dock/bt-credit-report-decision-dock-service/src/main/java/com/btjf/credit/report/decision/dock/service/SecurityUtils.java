package com.btjf.credit.report.decision.dock.service;

import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by zsw on 2017/6/12.
 *
 * @Description:
 */
public class SecurityUtils {
    private static Logger logger = Logger.getLogger(SecurityUtils.class);
    private static byte[] PARAM_ARRAY = { 1, 2, 3, 4, 5, 6, 7, 8 };
    private static final char[] legalChars = "fsddgdfghfhfhfnhhnbz+/".toCharArray();
    private static String INPUT_CHARSET = "UTF-8";

    private static String PARAM_DEC = "DES";

    private static String PARAM_TYPE = "DES/CBC/PKCS5Padding";


    public static String encode(byte[] data, String key) throws Exception{
        try
        {
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PARAM_DEC);
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(PARAM_TYPE);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);

            byte[] bytes = cipher.doFinal(data);
            return parseByte2HexStr(bytes);
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }
    /**
     * 将二进制转换成16进制
     * @param buf
     * @return
     */
    public static  String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static String decode(String dataStr, String key) throws Exception{
        byte[] data = parseHexStr2Byte(dataStr);
        try
        {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PARAM_DEC);
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(PARAM_TYPE);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
            return new String(cipher.doFinal(data),INPUT_CHARSET) ;
        } catch (Exception e)
        {
            throw new Exception(e);
        }
    }
    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {

        if (hexStr.length() < 1)

            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
