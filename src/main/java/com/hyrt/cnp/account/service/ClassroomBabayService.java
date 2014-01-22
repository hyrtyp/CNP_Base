package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.ClassRoomBabay;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-17.
 */
public class ClassroomBabayService{

    private CNPClient cnpClient;

    public ClassroomBabayService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public ClassRoomBabay.Model getclassroombabayData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
//        params.put("uuid", "104");
//        params.put("cid","117");

        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/babylist/?" +
                "token={token}&uuid={uuid}&cid={cid}",
                ClassRoomBabay.Model.class, params);
    }
}
