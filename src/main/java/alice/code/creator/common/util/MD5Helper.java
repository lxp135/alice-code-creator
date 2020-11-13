package alice.code.creator.common.util;

import java.security.MessageDigest;

/**
 * 描述：MD5加解密工具
 * 创建时间：2018/7/17 15:15
 * @author contact@liuxp.me
 */
public class MD5Helper {

    /**
     * 加密-32位小写
     * @param encryptStr 待加密的字符串
     */
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     * 加密-16位小写
     * @param encryptStr 待加密的字符串
     */
    public static String encrypt16(String encryptStr) {
        return encrypt32(encryptStr).substring(8, 24);
    }

    public static void main(String[] args) {
        String encryptStr = "22222222222,./.,./.,./!@#$%^&*()";
        System.out.println(MD5Helper.encrypt32(encryptStr));
        System.out.println(MD5Helper.encrypt16(encryptStr));
    }

}
