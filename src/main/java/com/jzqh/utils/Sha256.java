package com.jzqh.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class Sha256 {
    private String salt = ""; // 盐

    /**
     * SHA256加密
     *
     * @param pwd
     * @return
     */
    public String getPwd(String pwd) {
        MessageDigest messageDigest;
        String encodeStr = "";
        String saltAndPwd = mergePasswordAndSalt(pwd);
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            encodeStr = byte2Hex(messageDigest.digest(saltAndPwd.getBytes("UTF-8")));
        } catch (Exception e) {
            log.debug("encode failed", e);
        }
        return encodeStr;
    }

    /**
     * 判断所输入密码与用户密码是否匹配
     *
     * @param pwd    用户密码
     * @param pwd_in 输入密码
     * @return
     */
    public boolean isPwd(String pwd, String pwd_in) {
        if (getPwd(pwd_in).equals(pwd)) {
            return true;
        }
        return false;
    }

    /**
     * 字符串合并
     *
     * @param pwd
     * @return
     */
    private String mergePasswordAndSalt(String pwd) {
        if (pwd == null) {
            pwd = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return pwd;
        } else {
            return pwd + "{" + salt + "}";
        }
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes
     * @return
     */
    private String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

}
