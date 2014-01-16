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
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("pkind","1");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/photo/?" +
                "token={token}&uuid={uuid}&sid={sid}&pkind={pkind}",
                Photo.Model.class, params);
    }
}
