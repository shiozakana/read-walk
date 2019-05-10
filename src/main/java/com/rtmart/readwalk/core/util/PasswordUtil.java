package com.rtmart.readwalk.core.util;

import com.rtmart.readwalk.core.exception.SystemException;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public static String EncoderByMd5(String str)  {
        //确定计算方法
        MessageDigest md5= null;
        String newstr;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr =base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
           throw new SystemException(e);
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        }
        return newstr;
    }

    public static boolean checkpassword(String newpasswd,String oldpasswd) {
        if(EncoderByMd5(newpasswd).equals(oldpasswd)){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        String password=  "123456";
        System.out.println(EncoderByMd5(password));
        String password1=  "Admin@123";
        System.out.println(EncoderByMd5(password1));

    }

}
