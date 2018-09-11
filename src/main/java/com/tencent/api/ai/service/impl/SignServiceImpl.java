package com.tencent.api.ai.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

@Service
public class SignServiceImpl {
    private String appKey = "hxgCKZO22dDHlgzV";

    public String getSign(TreeMap<String,String> params){
        StringBuilder sb = new StringBuilder();

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if(!StringUtils.isEmpty(entry.getValue())){
                    sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),"UTF-8")).append("&");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        sb.append("app_key=").append(appKey);

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(sb.toString().getBytes("UTF-8"));
            byte[] md5Array = md5.digest();
            return bytesToHex1(md5Array).toUpperCase();
        } catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private  String bytesToHex1(byte[] md5Array) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < md5Array.length; i++) {
            int temp = 0xff & md5Array[i];//TODO:此处为什么添加 0xff & ？
            String hexString = Integer.toHexString(temp);
            if (hexString.length() == 1) {//如果是十六进制的0f，默认只显示f，此时要补上0
                strBuilder.append("0").append(hexString);
            } else {
                strBuilder.append(hexString);
            }
        }
        return strBuilder.toString();
    }



}
