package com.hzz.server.util;

import java.security.MessageDigest;

public class MD5Util {

    public static String encrypt(String str) {
        try {
            String e = "";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes("UTF-8"));
            byte[] var8 = bytes;
            int var7 = bytes.length;

            for(int var6 = 0; var6 < var7; ++var6) {
                byte b = var8[var6];
                String hex = Integer.toHexString(b & 255).toUpperCase();
                e = e + (hex.length() == 1?"0":"") + hex;
            }

            return e;
        } catch (Exception var10) {
            throw new RuntimeException(var10);
        }
    }
}