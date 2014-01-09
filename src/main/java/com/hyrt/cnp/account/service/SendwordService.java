package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.SendWord;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-8.
 */
public class SendwordService {
    private CNPClient cnpClient;

    public SendwordService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    //TODO after modify
    public SendWord.Model getModelData(RestTemplate restTemplate){
//        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
//        params.put("uuid", "104");
        params.put("sid","2");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/sendword?" +
                "token={token}&uuid={uuid}&sid={sid}",
                SendWord.Model.class,params);
    }
}
