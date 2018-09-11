package com.tencent.api.ai.controller;


import com.tencent.api.ai.service.impl.ApiServiceImpl;
import com.tencent.api.ai.service.impl.HttpServiceImpl;
import com.tencent.api.ai.service.impl.SignServiceImpl;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

@RestController
@RequestMapping(value="/api")
public class AiController {
    @Autowired
    ApiServiceImpl apiService;
    @Autowired
    SignServiceImpl signService;
    @Autowired
    HttpServiceImpl httpService;

    private String APP_ID = "2108387300";

    @RequestMapping(value = "/idCard",method = RequestMethod.GET)
    public String idCard(){
        TreeMap<String,String> params = apiService.preparedParams();
        params.put("app_id",APP_ID);
        params.put("time_stamp",String.valueOf(System.currentTimeMillis()/1000));
        params.put("nonce_str","fa577ce340859f9fe");
        params.put("image",apiService.getImgBase64("classpath:image/idcard.png"));
        params.put("card_type","0");
        params.put("sign",signService.getSign(params));
        return httpService.doPost(params,"https://api.ai.qq.com/fcgi-bin/ocr/ocr_idcardocr");
    }

}
