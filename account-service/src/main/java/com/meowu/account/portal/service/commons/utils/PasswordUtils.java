package com.meowu.account.portal.service.commons.utils;

import com.meowu.commons.utils.utils.AssertUtils;
import com.meowu.commons.utils.utils.Base64Utils;
import com.meowu.commons.utils.utils.RSAUtils;
import com.meowu.commons.utils.utils.SpellUtils;

import java.security.PrivateKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtils{

    //由至少1个数字和1个字母组成且长度为6~20个字符,其中可包含特殊字符_-+=`~!@#$%^&*().,?
    private static final String REGEX = "^(?=[a-zA-Z_\\-+=`~!@#$%^&*()\\.,?']*?[0-9][a-zA-Z_\\-+=`~!@#$%^&*()\\.,?']*)(?=[0-9_\\-+=`~!@#$%^&*()\\.,?']*?[a-zA-Z][0-9_\\-+=`~!@#$%^&*()\\.,?']*)[a-zA-Z0-9_\\-+=`~!@#$%^&*()\\.,?']{6,20}$";

    public static boolean verify(String sourcePassword, String targetPassword, String privateKey){
        //decode
        String sourcePlainText = decode(sourcePassword, privateKey);
        String targetPlainText = decode(targetPassword, privateKey);

        return sourcePlainText.equals(targetPlainText);
    }

    public static boolean brokenRule(String password, String privateKey){
        //decode password
        String plainText = decode(password, privateKey);

        //match the rule
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(plainText);

        return !matcher.matches();
    }

    public static String decode(String password, String privateKey){
        AssertUtils.hasText(password, "password must not be null");
        AssertUtils.hasText(privateKey, "private key must not be null");

        //rsa key
        PrivateKey key = RSAUtils.generatePrivateKey(Base64Utils.decode(privateKey.getBytes()));

        //password plain text
        byte[] plainBytes = RSAUtils.decryptBy2048(Base64Utils.decode(password.getBytes()), key);

        return SpellUtils.toString(plainBytes);
    }
}
