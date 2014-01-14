package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Photo;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-14.
 */
public class PhotoService {
    private CNPClient cnpClient;

    public PhotoService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    //TODO after modify
    public Photo.Model getphotolistData(RestTemplate restTemplate){
//        cnpClient.configureRequest();
//        HashMap<String, String> params = cnpClient.getParamsforGet();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
        params.put("uuid", "104");
        params.put("sid","2");
        params.put("pkind","1");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/photo/?" +
                "token={token}&uuid={uuid}&sid={sid}&pkind={pkind}",
                Photo.Model.class, params);
    }
}
