package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.SendWord;

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

    public SendWord.Model getSendwordData(RestTemplate restTemplate, int sid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        if(sid != -1){
            params.put("sid", sid+"");
        }
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/sendword/?" +
                "token={token}&uuid={uuid}&sid={sid}",
                SendWord.Model.class,params);
    }
}
