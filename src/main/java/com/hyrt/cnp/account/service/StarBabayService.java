package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.StarBabay;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-14.
 * 明星宝宝
 */
public class StarBabayService {

    private CNPClient cnpClient;

    public StarBabayService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public StarBabay.Model getStarbabayData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/babay/?" +
                "token={token}&uuid={uuid}&sid={sid}",
                StarBabay.Model.class, params);
    }
}
