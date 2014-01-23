package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Dynamic;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-16.
 */
public class DynamicService {

    private CNPClient cnpClient;

    public DynamicService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    //TODO after modify  album
    public Dynamic.Model getBabayDynamicData(RestTemplate restTemplate,String uid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
//        params.put("uuid", "104");
        params.put("uid","222");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/dynamic_user/?" +
                "token={token}&uuid={uuid}&uid={uid}",
                Dynamic.Model.class, params);
    }

    //TODO after modify  album
    public Dynamic.Model getBabaywordData(RestTemplate restTemplate,String uid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
//        params.put("uuid", "104");
        params.put("uid","222");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/dynamic_my?" +
                "token={token}&uuid={uuid}&uid={uid}",
                Dynamic.Model.class, params);
    }
}
