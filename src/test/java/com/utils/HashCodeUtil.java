package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 计算字符串的hash值工具类。
 *
 * @author yuthzi<p>
 * @version 1.0 2017年10月6日
 */
public class HashCodeUtil {
    public static String getFilesha1Hash(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[1024 * 1024 * 10];

            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                digest.update(buffer, 0, len);
            }
            String sha1 = new BigInteger(1, digest.digest()).toString(16);
            int length = 40 - sha1.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    sha1 = "0" + sha1;
                }
            }
            return sha1;
        } catch (IOException e) {
            System.out.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return " ";
    }

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    /**
     * 将字节数组转换为16进制的字符串
     *
     * @param byteArray 字节数组
     * @return 16进制的字符串
     */
    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuffer sb = new StringBuffer();
        for (byte byt : byteArray) {
            sb.append(byteToHexString(byt));
        }
        return sb.toString();
    }

    /**
     * 将字节转换为16进制字符串
     *
     * @param
     * @return 16进制字符串
     */
    private static String byteToHexString(byte b) {
        return hexDigits[(b >> 4) & 0x0f] + hexDigits[b & 0x0f];
    }

    /**
     * 将摘要信息转换为相应的编码
     *
     * @param code    编码类型
     * @param message 摘要信息
     * @return 相应的编码字符串
     */
    private static String hash(String code, String message) {
        MessageDigest md;
        String encode = null;

        try {
            md = MessageDigest.getInstance(code);
            encode = byteArrayToHexString(md.digest(message.getBytes()));

            return encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 计算message的MD5编码
     *
     * @param message 摘要信息
     * @return MD5编码之后的字符串
     */
    public static String md5Hash(String message) {
        return hash("MD5", message);
    }

    /**
     * 计算message的SHA编码
     *
     * @param message 摘要信息
     * @return SHA编码之后的字符串
     */
    public static String shaHash(String message) {
        return hash("SHA", message);
    }

    /**
     * 计算message的SHA-256编码
     *
     * @param message 摘要信息
     * @return SHA-256编码之后的字符串
     */
    public static String sha256Hash(String message) {
        return hash("SHA-256", message);
    }

    /**
     * 计算message的SHA-512编码
     *
     * @param message 摘要信息
     * @return SHA-512编码之后的字符串
     */
    public static String sha512Hash(String message) {
        return hash("SHA-512", message);
    }


}
