package com.tencent.api.ai.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.TreeMap;

@Service
public class ApiServiceImpl {

    public TreeMap<String,String> preparedParams(){
        TreeMap<String,String> map = new TreeMap<>(new Comparator<String>(){
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }

        });
        return map;
    }

    public String getImgBase64(String imgFile){
        InputStream in = null;
        byte[] data = null;

        try {
            File file = ResourceUtils.getFile(imgFile);
            in = new FileInputStream(file.getAbsolutePath());
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(Base64.encodeBase64(data));
    }


}
